package com.ebsco.eis.medical.domain.model;


import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class Workflows {
	
	private Map<String, Workflow> workflows;

	public Workflow getWorkflow(String key) {
		return this.workflows.get(key);
	}

	public Map<String, Workflow> getWorkflows() {
		return workflows;
	}

	public void setWorkflows(Map<String, Workflow> workflows) {
		this.workflows = workflows;
	}
}