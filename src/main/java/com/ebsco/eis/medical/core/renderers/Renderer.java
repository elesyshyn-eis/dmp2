package com.ebsco.eis.medical.core.renderers;

import com.ebsco.eis.medical.core.Worker;
import com.ebsco.eis.medical.domain.model.ApplicationRequest;
import com.ebsco.eis.medical.domain.model.ApplicationResponse;

public interface Renderer extends Worker  {
	
	public ApplicationResponse render(ApplicationRequest request, ApplicationResponse response);

}
