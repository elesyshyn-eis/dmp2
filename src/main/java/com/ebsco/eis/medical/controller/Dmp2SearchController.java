package com.ebsco.eis.medical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.eis.medical.ApplicationConstants;
import com.ebsco.eis.medical.core.WorkflowExecutor;
import com.ebsco.eis.medical.domain.api.ApiResponse;
import com.ebsco.eis.medical.domain.api.SearchRequest;
import com.ebsco.eis.medical.domain.api.SearchResponse;
import com.ebsco.eis.medical.domain.api.dmp2.Dmp2SearchResponse;
import com.ebsco.eis.medical.domain.model.ApplicationRequest;
import com.ebsco.eis.medical.domain.model.ApplicationResponse;

import io.swagger.annotations.ApiParam;

@RestController
public class Dmp2SearchController {
	
	@Autowired
	private WorkflowExecutor executor;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, path = "/dmp2/search")
	ResponseEntity<String> searchPost(@ApiParam(value = "Search request object.", required = true)
                                                  @RequestBody SearchRequest searchRequest) {

		String responseMessage = null;
		HttpStatus responseCode = HttpStatus.OK;
		
		// Create the application request object
		ApplicationRequest appRequest = new ApplicationRequest();
		appRequest.setApiRequest(searchRequest);
		appRequest.addContext(ApplicationConstants.WORKFLOW_ID, "DMP2");
				
		
		ApplicationResponse appResponse = executor.execute(appRequest);
		
		ApiResponse response = appResponse.getApiResponse();
		
		if (response instanceof Dmp2SearchResponse) {
			responseMessage = ((Dmp2SearchResponse)appResponse.getApiResponse()).getResponse();
		} else {
			responseMessage = "{ \"message\": \"" + ((SearchResponse)response).getCallStatus().getMessage() + "\"}";
			responseCode = HttpStatus.BAD_REQUEST;
		}
	
		return new ResponseEntity<>(responseMessage, responseCode);
	}
}
