package com.ebsco.eis.medical.core.enrichers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebsco.eis.medical.ApplicationConstants;
import com.ebsco.eis.medical.domain.api.SearchRequest;
import com.ebsco.eis.medical.domain.model.ApplicationRequest;
import com.ebsco.eis.medical.domain.model.Recommendations;
import com.ebsco.eis.medical.services.sse.SemanticService;

@Component
public class SemanticEnricher implements Enricher {
	
	@Autowired
	SemanticService semanticService;
	
	@Override
	public ApplicationRequest enrich(ApplicationRequest request) {
		
		SearchRequest search = (SearchRequest)request.getApiRequest();		
		Recommendations recommendations = semanticService.getRecommendations(search.getSearchSpec().getQuery().getKeyphrase());
		request.addContext(ApplicationConstants.CONTEXT_QI_RECOMMENDATIONS, recommendations);
		return request;
	}

}
