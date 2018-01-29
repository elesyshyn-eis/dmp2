package com.ebsco.eis.dmp2.elasticsearch;

import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SearchRequestBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRequestBuilder.class);
    
    private QueryFactory queryFactory = new QueryFactory();
 

    // TODO #1: Build this method out. See "com.ebsco.eis.dhdp.domain.elasticsearch.SearchRequestBuilder" in DHDP
    public String build(String criteria) {
     
        // create the builder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        
        // TODO: Build the query, see the QueryFactory for ideas... 
        
        // convert the query into a json string that elasticsearch  knows how to process
        String searchRequest = searchSourceToString(searchSourceBuilder);
        
        LOGGER.debug(searchRequest);
        
        // Return the json string to the caller
        return searchRequest;
    }

    
    private static String searchSourceToString(SearchSourceBuilder ssb) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            ssb.toXContent(builder, ToXContent.EMPTY_PARAMS);
            return builder.string();
        } catch (Exception x) {
            throw new RuntimeException(x);
        }

    }
}