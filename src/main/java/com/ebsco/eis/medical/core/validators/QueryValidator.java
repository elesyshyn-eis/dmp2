package com.ebsco.eis.medical.core.validators;

import org.springframework.stereotype.Component;

import com.ebsco.eis.medical.domain.api.ApiRequest;
import com.ebsco.eis.medical.domain.api.CallStatus;
import com.ebsco.eis.medical.domain.api.SearchRequest;

@Component
public class QueryValidator implements Validator {

    @Override
	public CallStatus validate(ApiRequest apiRequest) {
    	
    	SearchRequest request = (SearchRequest) apiRequest;

		CallStatus status = new CallStatus();
		status.setStatus(200);
		
		if (request.getSearchSpec().getQuery() == null)
		{
			 // If no query is specified, it represents a "browse" of the records.
		}
		return status;
	}
}