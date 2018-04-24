package com.ebsco.eis.medical.domain.api;

public interface ApiRequest {
	
	default <T> T getRequest(Class<T> type) {
		try {
			return type.cast(this);
		}
		catch (Exception ex) {
			return null;
		}
	}
}