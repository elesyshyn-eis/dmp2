package com.ebsco.eis.dmp2.elasticsearch;

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

@Component
public class SearchRequestBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRequestBuilder.class);
    private static String[] fieldsToHighlight = new String[]{"sections.content"};


    @Autowired
    private QueryFactory queryFactory;

    // Builds the query from the incoming criteria
    public String build(String criteria, String semanticCriteria, Map<String,Float> decayVectors) {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        
        ///////////// Create the criteria for the title fields /////////////////
        QueryBuilder disMaxTitleQueries = null;
        
        // #1. Build a match query against the title concepts field
        QueryBuilder titleConceptQuery = queryFactory.getMatchQuery("titleConcepts", semanticCriteria, 1f, false, "75%");
        
        // #2. Build a match query against the title surface forms field
        QueryBuilder titleSurfaceFormsQuery = queryFactory.getMatchQuery("titleSurfaceForms", criteria, 1f, false, "100%");
        
        // #3. Build a match query against the title field
        QueryBuilder titleQuery = queryFactory.getPhraseQuery("title", criteria, 1, 3);
        
        // #4: Build the decay vector search aginst the title field
        BoolQueryBuilder decay = queryFactory.getBooleanQuery();
        if (!decayVectors.isEmpty()) {
        	for(Entry<String, Float> entrySet : decayVectors.entrySet()) {
        		QueryBuilder vector = queryFactory.getMatchQuery("titleConcepts", entrySet.getKey(), Float.valueOf(entrySet.getValue()), false, "100%");
        		decay.should(vector);        		
        	}    
        	
        	// Add all the queries to a dis-max query. This will pick the best match out of the three queries
        	disMaxTitleQueries = queryFactory.getDisMaxQuery(1.4f, 0.01f, titleConceptQuery, titleSurfaceFormsQuery, titleQuery, decay);
        }
        else {
        	// Add all the queries to a dis-max query. This will pick the best match out of the three queries
        	disMaxTitleQueries = queryFactory.getDisMaxQuery(1.4f, 0.01f, titleConceptQuery, titleSurfaceFormsQuery, titleQuery);
        }
        
        ///////////// Create the criteria for the section fields /////////////////
        QueryBuilder matchSectionsQuery = queryFactory.getNestedMatchQuery("sections.content", "sections.weight", 0.01f, semanticCriteria, ScoreMode.Max, fieldsToHighlight);
        
     
        ///////////// Combine the title and section queries together /////////////////
        BoolQueryBuilder bool = queryFactory.getBooleanQuery();
        bool.should(disMaxTitleQueries);
        bool.should(matchSectionsQuery);
        searchSourceBuilder.query(bool);
        
        // Indicate which fields to return
        String[] fieldsToReturn = new String[]{"title", "titleConcepts", "titleSurfaceForms"};
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