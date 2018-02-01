package com.ebsco.eis.dmp2.elasticsearch;

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

    @Autowired
    private QueryFactory queryFactory;

    //a method to build a search request to send to ES
    public String build(String criteria) {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        
        //create a match query
        QueryBuilder matchQuery = queryFactory.getMatchQuery("sections.content", criteria, true);
     
        //create a bool query
        BoolQueryBuilder bool = queryFactory.getBooleanQuery();
        
        //add the match query to the bool query
        bool.should(matchQuery);
        
        //wrap the bool in a query
        searchSourceBuilder.query(bool);
        
        // TODO: Add inner_hits & highlighting
        
        // convert the query into a json string that elasticsearch knows how to process
        String searchRequest = searchSourceToString(searchSourceBuilder);
        LOGGER.debug(searchRequest);

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

//NestedQueryBuilder nestedBuilder = null;
//
//
//if (myLittlePony instanceof NestedQueryBuilder) {
//  nestedBuilder = (NestedQueryBuilder)myLittlePony;
//  InnerHitBuilder innerHit = nestedBuilder.innerHit();
//  HighlightBuilder highlight = innerHit.getHighlightBuilder();
//  nestedBuilder.innerHit().getHighlightBuilder().field("sections.content");
//  
// 
//  
//  List<String> fieldsToFetch = new ArrayList<>();
//  fieldsToFetch.add("sections.breadcrumb");
//  fieldsToFetch.add("sections.heading");
//
//
//  nestedBuilder.innerHit().setStoredFieldNames(fieldsToFetch);
//  
//}
