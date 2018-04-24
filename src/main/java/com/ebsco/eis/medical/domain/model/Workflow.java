package com.ebsco.eis.medical.domain.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.ebsco.eis.medical.ApplicationContextProvider;
import com.ebsco.eis.medical.core.Worker;
import com.ebsco.eis.medical.core.enrichers.Enricher;
import com.ebsco.eis.medical.core.loggers.Logger;
import com.ebsco.eis.medical.core.renderers.Renderer;
import com.ebsco.eis.medical.core.searchers.Searcher;
import com.ebsco.eis.medical.core.validators.Validator;

@ConfigurationProperties
public class Workflow {
		
	private enum TYPE {VALIDATORS, ENRICHERS, SEARCHERS, RENDERERS, LOGGERS}
	private EnumMap<TYPE, List<? extends Worker>> workflows = new EnumMap<>(TYPE.class);
	private String name;	
	private Map<String, List<String>> workers = new HashMap<>();

	private void init() {
		
		// Set the validators
		List<Validator> validators = new ArrayList<>();
		for(String worker : workers.get(TYPE.VALIDATORS.toString().toLowerCase())) {
			Validator bean = ApplicationContextProvider.getBean(worker, Validator.class);
			if (bean != null) {
				validators.add(bean);
			}
		}
		
		// Set the enrichers
		List<Enricher> enrichers = new ArrayList<>();
		for(String worker : workers.get(TYPE.ENRICHERS.toString().toLowerCase())) {
			Enricher bean = ApplicationContextProvider.getBean(worker, Enricher.class);
			if (bean != null) {
				enrichers.add(bean);
			}
		}

		// Set the searchers
		List<Searcher> searchers = new ArrayList<>();
		for(String worker : workers.get(TYPE.SEARCHERS.toString().toLowerCase())) {
			Searcher bean = ApplicationContextProvider.getBean(worker, Searcher.class);
			if (bean != null) {
				searchers.add(bean);
			}
		}
		
		// Set the renderers
		List<Renderer> renderers = new ArrayList<>();
		for(String worker : workers.get(TYPE.RENDERERS.toString().toLowerCase())) {
			Renderer bean = ApplicationContextProvider.getBean(worker, Renderer.class);
			if (bean != null) {
				renderers.add(bean);
			}
		}
		
		// Set the loggers
		List<Logger> loggers = new ArrayList<>();
		for(String worker : workers.get(TYPE.LOGGERS.toString().toLowerCase())) {
			Logger bean = ApplicationContextProvider.getBean(worker, Logger.class);
			if (bean != null) {
				loggers.add(bean);
			}
		}
		
		workflows.put(TYPE.VALIDATORS, validators);
		workflows.put(TYPE.ENRICHERS, enrichers);
		workflows.put(TYPE.SEARCHERS, searchers);
		workflows.put(TYPE.RENDERERS, renderers);
		workflows.put(TYPE.LOGGERS, loggers);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@SuppressWarnings("unchecked")
	public List<Validator> getValidators() {
		loadWorkflows();
		return (List<Validator>) workflows.get(TYPE.VALIDATORS);
	}
	
	@SuppressWarnings("unchecked")
	public List<Searcher> getSearchers() {
		loadWorkflows();
		return (List<Searcher>) workflows.get(TYPE.SEARCHERS);
	}
	
	@SuppressWarnings("unchecked")
	public List<Enricher> getEnrichers() {
		loadWorkflows();
		return (List<Enricher>) workflows.get(TYPE.ENRICHERS);
	}
	
	@SuppressWarnings("unchecked")
	public List<Renderer> getRenderers() {
		loadWorkflows();
		return (List<Renderer>) workflows.get(TYPE.RENDERERS);
	}
	
	@SuppressWarnings("unchecked")
	public List<Logger> getLoggers() {
		loadWorkflows();
		return (List<Logger>) workflows.get(TYPE.LOGGERS);
	}
	
	private void loadWorkflows() {
		if (workflows.isEmpty()) {
			init();
		}
	}
	
	public Map<String, List<String>> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, List<String>> workers) {
		this.workers = workers;
	}
}