package com.ebsco.eis.medical.domain.model;

import com.ebsco.eis.medical.domain.api.ApiResponse;

public class ApplicationResponse {
	
	private ApiResponse apiResponse;

	public ApiResponse getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(ApiResponse apiResponse) {
		this.apiResponse = apiResponse;
	}
}