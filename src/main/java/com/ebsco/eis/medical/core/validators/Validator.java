package com.ebsco.eis.medical.core.validators;

import com.ebsco.eis.medical.core.Worker;
import com.ebsco.eis.medical.domain.api.ApiRequest;
import com.ebsco.eis.medical.domain.api.CallStatus;

public interface Validator extends Worker {	
	
	public CallStatus validate(ApiRequest request);
	
}
