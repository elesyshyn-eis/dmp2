package com.ebsco.eis.medical.domain.model;


import java.util.HashMap;
import java.util.Map;

import com.ebsco.eis.medical.domain.api.ApiRequest;

public class ApplicationRequest {
	
	private ApiRequest apiRequest;
	private Map<String, Object> context = new HashMap<>();

	public ApiRequest getApiRequest() {
		return apiRequest;
	}
	public void setApiRequest(ApiRequest apiRequest) {
		this.apiRequest = apiRequest;
	}
	public Map<String, Object> getContext() {
		return context;
	}
	
	public void addContext(String key, Object value) {
		context.put(key, value);
	}
	
	public Object getContext(String key) {
		return context.get(key);
	}
	
	public <T> T getContext(String key, Class<T> type) {
		
		try {
			return type.cast(context.get(key));
		}
		catch (Exception ex) {
			return null;
		}
	}
}