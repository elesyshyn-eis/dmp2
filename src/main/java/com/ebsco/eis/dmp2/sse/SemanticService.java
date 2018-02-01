package com.ebsco.eis.dmp2.sse;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SemanticService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SemanticService.class);
	private static String restEndpoint = "http://semsearch4101.epnet.com:9080/sse/rest/map?extended=true&q=";
	
	public Map<String[], String> getMappedConcepts(String query) {
		
		 // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        
        // Add the query parameters
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("q", query);
        
        // Execute the request
        String result = restTemplate.getForObject(restEndpoint + "{q}", String.class, vars);
        
        // TODO: US330317 - Extract the concept IDs from the response and return to the caller.
        LOGGER.debug(result);
	
		return null;
	}
	
}
