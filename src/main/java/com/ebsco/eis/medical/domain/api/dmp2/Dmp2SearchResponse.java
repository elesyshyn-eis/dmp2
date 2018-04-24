package com.ebsco.eis.medical.domain.api.dmp2;

import com.ebsco.eis.medical.domain.api.SearchResponse;

// TODO: This is a temporary hack since no object mapping has been implemented for DMP2.
public class Dmp2SearchResponse extends SearchResponse {
	
	private String response;
	
	public String getResponse() {
		return response;
	}
	
	public Dmp2SearchResponse(String response) {
		this.response = response;
	}
}
