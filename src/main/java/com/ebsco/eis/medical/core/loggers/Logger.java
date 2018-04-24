package com.ebsco.eis.medical.core.loggers;

import com.ebsco.eis.medical.core.Worker;
import com.ebsco.eis.medical.domain.model.ApplicationRequest;
import com.ebsco.eis.medical.domain.model.ApplicationResponse;

public interface Logger extends Worker  {
	
	public void log(ApplicationRequest request, ApplicationResponse response);

}
