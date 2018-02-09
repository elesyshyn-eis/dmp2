package com.ebsco.eis.dmp2.sse;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ebsco.eis.dmp2.sse.pojo.Mappings;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SemanticService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SemanticService.class);
	private static String restEndpoint = "http://semsearch4101.epnet.com:9080/sse/rest/map?extended=true&q=";
	
	public Mappings getMappedConcepts(String query) {
		
		 // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        
        // Add the query parameters
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("q", query);
        
        // Execute the request
        String result = restTemplate.getForObject(restEndpoint + "{q}", String.class, vars);
        
        LOGGER.info(result);
        
        ObjectMapper mapper = new ObjectMapper();
        Mappings mappedConcepts = null;
        
        try {
        	mappedConcepts = mapper.readValue(result, Mappings.class);
        } catch (Exception oops) {
        	LOGGER.error("Unable to map response from SSE" + oops);
        }
        
		return mappedConcepts;
	}
}
