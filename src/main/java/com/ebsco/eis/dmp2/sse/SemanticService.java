package com.ebsco.eis.dmp2.sse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.iterators.PermutationIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ebsco.eis.dmp2.sse.pojo.Concept;
import com.ebsco.eis.dmp2.sse.pojo.Mapping;
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
	
	
	public String enrichQuery(String originalContent) {
		
		StringBuilder enrichedquery = new StringBuilder();
		
		for(Mapping mapping : getMappedConcepts(originalContent).getMappings()) {
			
			// If the mapping doesn't contain any concept ids... 
			if(mapping.getConcepts().isEmpty() && !mapping.getTokens().isEmpty()) {
				
				// retain the original keyword that we couldn't map
				for(String keyword : mapping.getTokens()) {
					enrichedquery.append(keyword + " ");
				}
			// Otherwise, extract the concept ids...	
			} else if (!mapping.getConcepts().isEmpty()) {
				for(Concept concept : mapping.getConcepts()) {
					enrichedquery.append(concept.getCid() + " ");
				}
			}
		}

		return enrichedquery.toString().trim();
	}
	
	public String enrichContent(String originalContent) {
		
		StringBuilder enrichedContent = new StringBuilder();
		
		try {

			for(Mapping mapping : getMappedConcepts(originalContent).getMappings()) {
				
				// If the mapping doesn't contain any concept ids... 
				if(mapping.getConcepts().isEmpty() && !mapping.getTokens().isEmpty()) {
					
					// retain the original keyword that we couldn't map
					for(String keyword : mapping.getTokens()) {
						enrichedContent.append(keyword + " ");
					}
					
				// Otherwise, wrap the concept ids around the tokens...	
				} else if (!mapping.getConcepts().isEmpty()) {
					
					// Build the <c tag
					enrichedContent.append("<c ");
					for(int i = 0; i < mapping.getConcepts().size(); i++) {
						
						enrichedContent.append(mapping.getConcepts().get(i).getCid());
						if(i + 1 < mapping.getConcepts().size()) {
							enrichedContent.append(" ");
						}
					}
					enrichedContent.append(">");
					
					// Build the keywords inside the <c tag
					for(int i = 0; i < mapping.getTokens().size(); i++) {
						
						enrichedContent.append(mapping.getTokens().get(i));
						
						if(i + 1 < mapping.getTokens().size()) {
							enrichedContent.append(" ");
						}					
					}
	
					// Close the <c tag
					enrichedContent.append("</c>");
				}
			}
		
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return enrichedContent.toString().trim();
	}
	
	public List<String> getSurfaceForms(String originalContent) {
		
		List<String> surfaceForms = new ArrayList<String>();
		
		try {
			for(Mapping mapping : getMappedConcepts(originalContent).getMappings()) {
				for(Concept concept: mapping.getConcepts()) {
					surfaceForms.addAll(concept.getSurfaceForms());
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return surfaceForms;
	}
	
	public String getQIRecommendations(String originalContent) {
		
		String recommendation = enrichQuery(originalContent);
		int numOfConcepts = recommendation.split(" ").length;
		
		PermutationIterator<String> iterator = new PermutationIterator<String>(Arrays.asList(originalContent.split(" ")));
		
		while (iterator.hasNext()) {
			
			// Build the permutation
			StringBuilder permutation = new StringBuilder();
			for (Object term : iterator.next()) {
				permutation.append(term);
				permutation.append(" ");
			}
			
			// Submit the permutation to SSE
			String query = permutation.toString().trim();
			String concepts = enrichQuery(query).trim();
			int count = concepts.split(" ").length;
			
			// If we have less concepts than our original recommendation...
			if (count < numOfConcepts) {
				recommendation = concepts;
				numOfConcepts = count;
			}
		}
		
		return recommendation;
	}
}
