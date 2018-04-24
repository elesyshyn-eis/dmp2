package com.ebsco.eis.medical.core.loggers;

import org.springframework.stereotype.Component;

import com.ebsco.eis.medical.domain.model.ApplicationRequest;
import com.ebsco.eis.medical.domain.model.ApplicationResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TransactionLogger implements com.ebsco.eis.medical.core.loggers.Logger {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionLogger.class);

	@Override
	public void log(ApplicationRequest request, ApplicationResponse response) {
		
		LOGGER.info("Logging transaction data...");
		
	}
	
}
