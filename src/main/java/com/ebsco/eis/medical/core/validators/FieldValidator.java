package com.ebsco.eis.medical.core.validators;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.ebsco.eis.medical.domain.api.ApiRequest;
import com.ebsco.eis.medical.domain.api.CallStatus;
import com.ebsco.eis.medical.domain.api.FieldSpec;
import com.ebsco.eis.medical.domain.api.SearchRequest;

public abstract class FieldValidator implements Validator {

    @Override
	public CallStatus validate(ApiRequest apiRequest) {
    	
		SearchRequest request = apiRequest.getRequest(SearchRequest.class);

		CallStatus status = new CallStatus();
		status.setStatus(200);
		
		if (request.getRecordSpec() == null || request.getRecordSpec().getFields() == null)
		{
			 // If no fields are specified
			status.setStatus(400);
			status.setMessage("No fields were specified in the request.");
		}
		
		for (FieldSpec field : request.getRecordSpec().getFields()) {
			
			if (!getKnownFields().contains(field.getName())) {
				status.setStatus(400);
				status.setMessage("Invalid field specified in the request:" + field.getName());
			}
		}
		return status;
	}
    
    protected abstract Set<String> getKnownFields();
}