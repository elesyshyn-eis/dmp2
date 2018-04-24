package com.ebsco.eis.medical.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.eis.dynamichealth.elasticsearch.ElasticResult;
import com.ebsco.eis.medical.core.searchers.dmp2.SearchRequestBuilder;
import com.ebsco.eis.medical.services.elasticsearch.ElasticRestClient;
import com.ebsco.eis.medical.services.sse.SemanticService;
import com.ebsco.eis.medical.services.sse.pojo.Mappings;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public ResponseEntity<String> search(@RequestParam(value = "q", required = true) String searchinput, @RequestParam(value = "enableQi", required = false, defaultValue="true") boolean enableQi)
            throws IOException {
    	
    	// Enrich the query
    	Map<String, Map<String,Float>> decayVectors = new HashMap<>();
    	String enrichedSearch = null;
//    	if (enableQi) {
//    		enrichedSearch = semanticService.getBestPermutation(searchinput);
//    		decayVectors = semanticService.getSearchVectors(enrichedSearch);
//    	} else {
//    		enrichedSearch = semanticService.getMappedConceptsAsString(searchinput);
//    	}
    	
    	// Build the search request
    	String searchRequest = searchRequestBuilder.build(searchinput, enrichedSearch, decayVectors);
    	
        // pass the search request to the ES client
        ElasticResult result = esClient.executeRequest("GET", "/dmp2/_search", searchRequest);
        
        StringBuilder response = new StringBuilder();
        response.append("{ \"originalQuery\": ");
        response.append("\"");
        response.append(searchinput);
        response.append("\",\"qiRecommendation\": ");
        response.append("\"");
        response.append(enrichedSearch);
        response.append("\",\"searchVectors\": ");
        response.append("\"");
        response.append(decayVectors);
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
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/concepts")
//    public ResponseEntity<String> concepts(@RequestParam(value = "concepts", required = true) String searchinput)
//            throws IOException {
//    	
//    	// Pass the query to SSE to get semantic concepts
//    	Mappings concepts = semanticService.getMappedConcepts(searchinput);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        return new ResponseEntity<>(mapper.writeValueAsString(concepts), HttpStatus.OK);
//    }
    
  

}
