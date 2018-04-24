package com.ebsco.eis.medical.core.renderers;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ebsco.eis.dynamichealth.elasticsearch.ElasticResult;
import com.ebsco.eis.medical.ApplicationConstants;
import com.ebsco.eis.medical.domain.api.SearchRequest;
import com.ebsco.eis.medical.domain.api.dmp2.Dmp2SearchResponse;
import com.ebsco.eis.medical.domain.model.ApplicationRequest;
import com.ebsco.eis.medical.domain.model.ApplicationResponse;
import com.ebsco.eis.medical.domain.model.Recommendations;

@Component
public class Dmp2Renderer implements Renderer {
	
	public ApplicationResponse render(ApplicationRequest request, ApplicationResponse response) {
		
		// Get the search results from the context
		ElasticResult results = request.getContext(ApplicationConstants.CONTEXT_SEARCH_RESULTS, ElasticResult.class);
		
		// Get the original search criteria
		String searchinput = ((SearchRequest)request.getApiRequest()).getSearchSpec().getQuery().getKeyphrase();
		
		// Get the recommendations
		Recommendations recommendations = (Recommendations)request.getContext(ApplicationConstants.CONTEXT_QI_RECOMMENDATIONS);
		String enrichedSearch = recommendations.getEnrichedSearchCriteria();
		Map<String, Map<String,Float>> searchVectors = recommendations.getSearchVectors();
		
		StringBuilder stringResponse = new StringBuilder();
        stringResponse.append("{ \"originalQuery\": ");
        stringResponse.append("\"");
        stringResponse.append(searchinput);
        stringResponse.append("\",\"qiRecommendation\": ");
        stringResponse.append("\"");
        stringResponse.append(enrichedSearch);
        stringResponse.append("\",\"searchVectors\": ");
        stringResponse.append("\"");
        stringResponse.append(searchVectors);
        stringResponse.append("\",\"response\":");
        stringResponse.append(results.getPayload());
        stringResponse.append("}");
	        
        response.setApiResponse(new Dmp2SearchResponse(stringResponse.toString()));
        
        return response;
	}	
}
