package com.ebsco.eis.medical.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebsco.eis.medical.ApplicationConstants;
import com.ebsco.eis.medical.core.enrichers.Enricher;
import com.ebsco.eis.medical.core.renderers.Renderer;
import com.ebsco.eis.medical.core.searchers.Searcher;
import com.ebsco.eis.medical.core.validators.Validator;
import com.ebsco.eis.medical.domain.api.CallStatus;
import com.ebsco.eis.medical.domain.api.SearchResponse;
import com.ebsco.eis.medical.domain.model.ApplicationRequest;
import com.ebsco.eis.medical.domain.model.ApplicationResponse;
import com.ebsco.eis.medical.domain.model.Workflow;
import com.ebsco.eis.medical.domain.model.Workflows;

@Component
public class WorkflowExecutor {
	
	@Autowired
	private Workflows workflows;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowExecutor.class);

	public ApplicationResponse execute(ApplicationRequest request) {
		
		ApplicationResponse response = new ApplicationResponse();
		
		try {
			// Fetch the workflow
			String workflowId = getWorkflowId(request);
			Workflow workflow = workflows.getWorkflow(workflowId);
			LOGGER.info("Executing workflow: " + workflow.getName());

			// Process the validators...
			CallStatus status = executeValidators(request, workflow);
			response.setApiResponse(new SearchResponse().callStatus(status));
			
			if (!isValid(status)) {
				return response;
			}
			
			// Process the enrichers & searchers
			request = executeEnrichers(request, workflow);
			request = executeSearchers(request, workflow);
			
			// Transform the result using the renderers
			response = executeRenderers(request, response, workflow);
			
			// Execute the loggers
			executeLoggers(request, response, workflow);
		} 
		catch (Exception  ex) {
			CallStatus status = new CallStatus();
			status.message("Server Error");
			status.setStatus(500);
			response.setApiResponse(new SearchResponse().callStatus(status));
		}
		return response;	
	}
	
	private void executeLoggers(ApplicationRequest request, ApplicationResponse response, Workflow workflow) {
		
		for (com.ebsco.eis.medical.core.loggers.Logger logger : workflow.getLoggers()) {
			LOGGER.info("Executing logger: " + logger.getClass().getName());
			logger.log(request, response);
		}
	}
	
	private ApplicationResponse executeRenderers(ApplicationRequest request, ApplicationResponse response, Workflow workflow) {
				
		for (Renderer renderer : workflow.getRenderers()) {
			LOGGER.info("Executing renderers: " + renderer.getClass().getName());
			response = renderer.render(request, response);
		}
		return response;
	}
	
	private ApplicationRequest executeSearchers(ApplicationRequest request, Workflow workflow) throws Exception {
		
		for (Searcher searcher : workflow.getSearchers()) {
			try {
				LOGGER.info("Executing searcher: " + searcher.getClass().getName());
				request = searcher.search(request);
			}
			catch (Exception ex) {
				LOGGER.error("Exception while executing searcher: " + searcher.getClass().getName(), ex);
				throw ex;
			}
		}
		return request;
	}
	
	private ApplicationRequest executeEnrichers(ApplicationRequest request, Workflow workflow) throws Exception {
		
		for (Enricher enricher : workflow.getEnrichers()) {
			try {
				LOGGER.info("Executing enricher: " + enricher.getClass().getName());
				request = enricher.enrich(request);
			}
			catch (Exception ex) {
				LOGGER.error("Exception while executing enricher: " + enricher.getClass().getName(), ex);
				throw ex;
			}
		}
		return request;
	}
	
	private CallStatus executeValidators(ApplicationRequest request, Workflow workflow) {
		
		CallStatus status = null;
		
		// Execute each validator...
		for (Validator validator : workflow.getValidators()) {
			
			LOGGER.info("Executing validator: " + validator.getClass().getName());
						
			// Examine the result of the validation...
			status =  validator.validate(request.getApiRequest());
			
			// Stop processing if we encountered an invalid state
			if (!isValid(status)) {
				break;
			}
		}
		return status;	
	}
	
	private String getWorkflowId(ApplicationRequest request) {
		
		// Extract the workflow from the application request object
		Object key = request.getContext(ApplicationConstants.WORKFLOW_ID);
		
		if (key != null && key instanceof String) {
			return (String)key;
		}
		else return ApplicationConstants.DEFAULT_WORKFLOW_ID;
	}
	
	private boolean isValid(CallStatus status) {
		return (status != null && status.getStatus() == 200);
	}
}