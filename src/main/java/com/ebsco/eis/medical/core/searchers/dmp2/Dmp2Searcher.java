package com.ebsco.eis.medical.core.searchers.dmp2;


import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebsco.eis.dynamichealth.elasticsearch.ElasticResult;
import com.ebsco.eis.medical.ApplicationConstants;
import com.ebsco.eis.medical.core.searchers.Searcher;
import com.ebsco.eis.medical.domain.model.ApplicationRequest;
import com.ebsco.eis.medical.domain.model.Recommendations;
import com.ebsco.eis.medical.services.elasticsearch.ElasticRestClient;
import com.ebsco.eis.medical.services.elasticsearch.QueryFactory;


@Component
public class Dmp2Searcher implements Searcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchRequestBuilder.class);
	private static String[] fieldsToHighlight = new String[]{"sections.content"};
	
	@Autowired
	private QueryFactory queryFactory;
	
    @Autowired
    private ElasticRestClient esClient;

	@Override
	public ApplicationRequest search(ApplicationRequest request) {

		try {
			// Extract the recommendations from the context map
			Recommendations recommendations = request.getContext(ApplicationConstants.CONTEXT_QI_RECOMMENDATIONS, Recommendations.class);
			
			// Build the search request
			String semanticCriteria = recommendations.getBestPermutation();
			Map<String, Map<String,Float>> searchVectors = recommendations.getSearchVectors();
	    	String searchRequest = build(semanticCriteria, searchVectors);
	    	
			// Execute the search
	        ElasticResult result = esClient.executeRequest("GET", "/dmp2/_search", searchRequest);
	        
		    // Set the results into the context
	        request.addContext(ApplicationConstants.CONTEXT_SEARCH_RESULTS, result);
	        
		} catch (Exception ex) {
			LOGGER.error("Unable to execute search:" + ex);
		}
		
    	return request;
	}

	// Builds the query from the incoming criteria
    private String build(String criteria, Map<String, Map<String,Float>> decayVectors) {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        
        ///////////// Create the criteria for the title fields /////////////////
        QueryBuilder disMaxTitleQueries = null;
        
        // #1. Build a match query against the title surface forms field
        QueryBuilder titleSurfaceFormsQuery = queryFactory.getMatchQuery("titleSurfaceForms", criteria, 1f, false, "100%");
        
        // #2. Build a match query against the title field
        QueryBuilder titleQuery = queryFactory.getPhraseQuery("title", criteria, 1, 3);
        
        // #3. Build a match query against the title concepts field:
        // (c1*w1 || c2*w2) && (c3*w3) && (c4*w4 || c5*w5 || c6*w6)
    	BoolQueryBuilder andConditionBuilder = queryFactory.getBooleanQuery(); // AND condition
    	
    	// For each concept in the search vector... 
    	for(Entry<String, Map<String, Float>> searchVector: decayVectors.entrySet()) {
    		
    		// OR the concepts together
    		BoolQueryBuilder orConditionBuilder = queryFactory.getBooleanQuery();
    		for(Entry<String, Float> entrySet : searchVector.getValue().entrySet()) {
        		
    			QueryBuilder vector = queryFactory.getMatchQuery("titleConcepts", entrySet.getKey(), Float.valueOf(entrySet.getValue()), false, "100%");
    			orConditionBuilder.should(vector);        		
        	} 
    		
    		// Place the OR conditions inside the AND conditions
    		andConditionBuilder.must(orConditionBuilder);
    	}
         
    	// #4. Add all the queries to a dis-max query. This will pick the best match out of the three queries
    	// 02/21/18: Reduced the title weight to 1
        disMaxTitleQueries = queryFactory.getDisMaxQuery(1f, 0.0f, titleSurfaceFormsQuery, titleQuery, andConditionBuilder);
        
        ///////////// Create the criteria for the section fields /////////////////
       	// 02/21/18: Reduced the section factor to 0.1
        QueryBuilder disMaxSectionQueries = queryFactory.getNestedMatchQuery("sections.content", "sections.weight", 0.1f, ScoreMode.Max, fieldsToHighlight, criteria, decayVectors);
            
        ///////////// Combine the title and section queries together /////////////////
        BoolQueryBuilder bool = queryFactory.getBooleanQuery();
        bool.should(disMaxTitleQueries);
        bool.should(disMaxSectionQueries);
        searchSourceBuilder.query(bool);
        
        // Indicate which fields to return
        String[] fieldsToReturn = new String[]{"title", "titleConcepts", "titleSurfaceForms", "type"};
        searchSourceBuilder.fetchSource(fieldsToReturn, null);

        // convert the query into a json string that elasticsearch knows how to process
        String searchRequest = searchSourceToString(searchSourceBuilder);
        LOGGER.info(searchRequest);

        // Return the json string to the caller
        return searchRequest;
    }

    private static String searchSourceToString(SearchSourceBuilder searchSourceBuilder) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            searchSourceBuilder.toXContent(builder, ToXContent.EMPTY_PARAMS);
            return builder.string();
        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }
}
