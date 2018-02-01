package com.ebsco.eis.dmp2.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.eis.dmp2.elasticsearch.ElasticRestClient;
import com.ebsco.eis.dmp2.elasticsearch.SearchRequestBuilder;
import com.ebsco.eis.dmp2.sse.SemanticService;
import com.ebsco.eis.dynamichealth.elasticsearch.ElasticResult;

@RestController
public class SearchController {

    @Autowired
    private ElasticRestClient esClient;
    
    @Autowired
    private SearchRequestBuilder searchRequestBuilder;
    
    @Autowired
    private SemanticService semanticService;

    // expose a search input field in our rest client
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/search")
    public ResponseEntity<String> search(@RequestParam(value = "searchinput", required = true) String searchinput)
            throws IOException {
    	
    	// Pass the query to SSE to get semantic concepts
    	Map<String[], String> concepts = semanticService.getMappedConcepts(searchinput);
    	
    	// TODO: US330317 - Update the search input to reflect the mixture of concepts & keywords

        // pass the search input to our search request builder
        String searchRequest = searchRequestBuilder.build(searchinput);

        // pass the search request to the ES client
        ElasticResult result = esClient.executeRequest("GET", "/dmp2/_search", searchRequest);

        // return the response & status to the caller
        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
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
}
