package com.ebsco.eis.dmp2.controller;


import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.eis.dmp2.elasticsearch.ElasticRestClient;
import com.ebsco.eis.dynamichealth.elasticsearch.ElasticResult;

@RestController
public class SearchController {
    
	
	private ElasticRestClient client = new ElasticRestClient();    

	// TODO #1: Search input needs to be added here (hint: @RequestParam String query)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/search")
    public ResponseEntity<String> search() throws IOException {
        
        // TODO #2: Build the search request string (see SearchRequestBuilder.build(String criteria)
    	// String searchRequest = SearchRequestBuilder.build(criteria);
        
        // TODO #3: Pass the search request to this method instead of the empty "{}"
        ElasticResult result = client.executeRequest("GET", "/dmp2/_search", "{}");
        // ElasticResult result = client.executeRequest("GET", "/dmp2/_search", searchRequest);
       
        // return the response & status to the caller
        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/mapping")
    public ResponseEntity<String> mapping() throws IOException {

    	// Default status code
        HttpStatus httpStatus = HttpStatus.OK;
        
        // Execute mapping request to ES
        ElasticResult result = client.executeRequest("GET", "/dmp2/_mappings", "{}");
       
        // return the response & status to the caller
        return new ResponseEntity<>(result.getPayload(), httpStatus);
    }
}
