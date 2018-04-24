package com.ebsco.eis.medical.core.enrichers;

import com.ebsco.eis.medical.core.Worker;
import com.ebsco.eis.medical.domain.model.ApplicationRequest;

public interface Enricher extends Worker  {
	
	public ApplicationRequest enrich(ApplicationRequest request);

}
