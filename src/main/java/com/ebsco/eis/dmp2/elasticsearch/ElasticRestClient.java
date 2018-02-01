package com.ebsco.eis.dmp2.elasticsearch;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ebsco.eis.dynamichealth.elasticsearch.ElasticResult;

@Component
public class ElasticRestClient implements AutoCloseable {

    private String elasticSearchEndpoint = "search-esmedloada-medicaldiscover-d-hni6gso7xwouu4breodpdppfdu.us-east-1.es.amazonaws.com";
    private String elasticSearchClusterName = "872344130825:eis-devqa-discovery-shared";
    private int elasticSearchClusterPort = 80;
    private int connectionRequestTimeout = 1000;
    private int connectTimeout = 1000;
    private int socketTimeout = 1000;
    private RestClient client;
    private HttpHost elasticHost;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticRestClient.class);
    
    // public facing method that executes requests against elasticsearch
    public ElasticResult executeRequest(final String httpVerb, final String requestPath, final String jsonQuery) throws IOException {

		LOGGER.info(String.format("Elastic request:%n%1$s", jsonQuery));

		// create the json query object
		final HttpEntity payload = new NStringEntity(jsonQuery, ContentType.APPLICATION_JSON);

		// execute the search request
		final Response response = performRequest(httpVerb, requestPath, payload);

		// return the converted results to the caller
		return getResult(response);
	}

    // converts the raw response from elasticsearch into something more readable 
    private ElasticResult getResult(Response response) throws IOException {

        final ElasticResult result = new ElasticResult();

        // copy HTTP status into result
        if (response.getStatusLine() != null) {
            result.setHttpStatus(response.getStatusLine().getReasonPhrase());
            result.setHttpStatusCode(response.getStatusLine().getStatusCode());
        }

        // copy payload into result
        if (response.getEntity() != null) {
            try (InputStream s = response.getEntity().getContent()) {
                final String responseString = IOUtils.toString(s, "UTF-8");
                result.setPayload(responseString);
            }
        }

        return result;
    }

    // executes the request
	private Response performRequest(String httpVerb, String resourcePath, HttpEntity payload) throws IOException {

        Header[] headers = new Header[0];

        return getClient().performRequest(httpVerb,
            resourcePath,
            Collections.emptyMap(),
            payload, headers);
    }
  
    
  
    // TODO  (see DHDP - SearchRequestBuilder, DHDP - QueryFactory)
    /* 1. Need a method here to build the search request (output is a string)
    	 A. See public QueryBuilder getMatchQuery(String field, Object criteria, Float boost, boolean allowFuzziness) method
    	    This method builds the match clause for you. Remember that it checks if the field is nested or not. Your field IS nested
    	    So make sure code knows it is nested.
    	 B. Attach this match query to a bool query (in the should clause)
    	 
    	 	EXAMPLE:
    	 	        
        	QueryBuilder matchQuery = queryFactory.getMatchQuery("sections.content", "mycriteria", false);
        	BoolQueryBuilder booleanQuery = queryFactory.getBooleanQuery();
        	booleanQuery.should(matchQuery);
    
       	2. Need a method here that can perform a search (send the string to ES)
     	
     */

    protected HttpHost getElasticHost() {
        if (elasticHost == null) {
            elasticHost = new HttpHost(elasticSearchEndpoint, elasticSearchClusterPort, "http");
        }
        return elasticHost;
    }

    /**
     * Instantiate the ES connection, using a lazy/on-demand approach. This could also be done in @PostConstruct, to be
     * available as soon as the bean gets instantiated, and then would "fail early" if configuration is wrong.
     */
    private void init() {

        try {
            // https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/_initialization.html
            client = RestClient.builder(getElasticHost()).setRequestConfigCallback(new RequestConfigCallback() {

                @Override
                public Builder customizeRequestConfig(Builder requestConfigBuilder) {

                    requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
                    requestConfigBuilder.setConnectTimeout(connectTimeout);
                    requestConfigBuilder.setSocketTimeout(socketTimeout);

                    return requestConfigBuilder;
                }

            }).build();

            // can't use the sniffer for AWS as there's no way to sign the
            // requests
            // https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/_usage.html
            // sniffer = Sniffer.builder(client).build();

        } catch (Exception x) {
            LOGGER.error(
                    String.format("Error while trying to configure EC %1$s at %2$s on port %3$s - check configuration",
                            elasticSearchClusterName, elasticSearchEndpoint, elasticSearchClusterPort),
                    x);
            throw x;
        }
    }

    @Override
    public void close() throws Exception {

        if (client != null) {
            try {
                client.close();
            } catch (Exception x) {
                LOGGER.warn("Error while shutting down ElasticSearch REST client (ignored)", x);
            }
            client = null;
        }
    }

    // TODO: We need a better way to enforce that this is initialized
    protected RestClient getClient() throws IOException {
        if (client == null) {
            init();
        }
        return client;
    }

}