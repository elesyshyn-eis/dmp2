package com.ebsco.eis.dmp2.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.eis.dmp2.content.loader.Dmp2Loader;
import com.ebsco.eis.dmp2.elasticsearch.ElasticRestClient;
import com.ebsco.eis.dmp2.elasticsearch.SearchRequestBuilder;
import com.ebsco.eis.dmp2.sse.SemanticService;
import com.ebsco.eis.dmp2.sse.pojo.Mappings;
import com.ebsco.eis.dynamichealth.elasticsearch.ElasticResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SearchController {

    @Autowired
    private ElasticRestClient esClient;
    
    @Autowired
    private SearchRequestBuilder searchRequestBuilder;
    
    @Autowired
    private SemanticService semanticService;
    
    @Autowired
    private Dmp2Loader dmp2Loader;

    // expose a search input field in our rest client
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/search")
    public ResponseEntity<String> search(@RequestParam(value = "q", required = true) String searchinput, @RequestParam(value = "enableQi", required = false) boolean enableQi)
            throws IOException {
    	
    	// Enrich the query
    	String enrichedSearch = null;
    	if (enableQi) {
    		enrichedSearch = semanticService.getQIRecommendations(searchinput);
    	} else {
    		enrichedSearch = semanticService.enrichQuery(searchinput);
    	}
    	// Build the search request
    	String searchRequest = searchRequestBuilder.build(searchinput, enrichedSearch);
    	
        // pass the search request to the ES client
        ElasticResult result = esClient.executeRequest("GET", "/dmp2/_search", searchRequest);
        
        StringBuilder response = new StringBuilder();
        response.append("{ \"original query\": ");
        response.append("\"");
        response.append(searchinput);
        response.append("\",\"enriched query\": ");
        response.append("\"");
        response.append(enrichedSearch);
        response.append("\",\"response\":");
        response.append(result.getPayload());
        response.append("}");
        
        System.out.println(response.toString());
      
        // return the response & status to the caller
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/mapping")
    public ResponseEntity<String> mapping() throws IOException {

        // Default status code
        HttpStatus httpStatus = HttpStatus.OK;

        // Execute mapping request to ES
        ElasticResult result = esClient.executeRequest("GET", "/dmp2/_mappings", "{}");

        // return the response & status to the caller
        return new ResponseEntity<>(result.getPayload(), httpStatus);
    }
    
    // Expose the query to concept mapping
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/concepts")
    public ResponseEntity<String> concepts(@RequestParam(value = "concepts", required = true) String searchinput)
            throws IOException {
    	
    	// Pass the query to SSE to get semantic concepts
    	Mappings concepts = semanticService.getMappedConcepts(searchinput);

        ObjectMapper mapper = new ObjectMapper();

        return new ResponseEntity<>(mapper.writeValueAsString(concepts), HttpStatus.OK);
    }
    
    // Expose the ability to load content into the sample index
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, path = "/load")
    public ResponseEntity<String> load(@RequestParam(value = "indexName", required = true) String indexName, @RequestParam(value = "rollup", required = true) boolean rollup) throws IOException {
    	
    	int recordsLoaded = dmp2Loader.loadData(indexName, rollup);
    	
        return new ResponseEntity<>("{\"Records Loaded\":" + recordsLoaded + "}", HttpStatus.OK);
    }

}
