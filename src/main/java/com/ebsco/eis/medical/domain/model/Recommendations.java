package com.ebsco.eis.medical.domain.model;

import java.util.Map;
import java.util.Set;

public class Recommendations {
	
	private String enrichedSearchCriteria;
	private Map<String, Map<String,Float>> searchVectors;
	private Set<String> surfaceForms;
	private String bestPermutation;
	
	public String getEnrichedSearchCriteria() {
		return enrichedSearchCriteria;
	}
	public void setEnrichedSearchCriteria(String enrichedSearchCriteria) {
		this.enrichedSearchCriteria = enrichedSearchCriteria;
	}
	public Map<String, Map<String, Float>> getSearchVectors() {
		return searchVectors;
	}
	public void setSearchVectors(Map<String, Map<String, Float>> searchVectors) {
		this.searchVectors = searchVectors;
	}
	public Set<String> getSurfaceForms() {
		return surfaceForms;
	}
	public void setSurfaceForms(Set<String> surfaceForms) {
		this.surfaceForms = surfaceForms;
	}
	public String getBestPermutation() {
		return bestPermutation;
	}
	public void setBestPermutation(String bestPermutation) {
		this.bestPermutation = bestPermutation;
	}
}
