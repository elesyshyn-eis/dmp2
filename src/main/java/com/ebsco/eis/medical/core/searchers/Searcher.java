package com.ebsco.eis.medical.core.searchers;

import com.ebsco.eis.medical.core.Worker;
import com.ebsco.eis.medical.domain.model.ApplicationRequest;

public interface Searcher extends Worker  {
	
	public ApplicationRequest search(ApplicationRequest request);

}
