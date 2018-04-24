
package com.ebsco.eis.medical.core.searchers.dmp2.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "filename",
    "title",
    "titleConcepts",
    "titleSurfaceForms",
    "type",
    "sections"
})
public class Dmp2 implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 9045035971572994507L;
	@JsonProperty("filename")
    private String filename;
    @JsonProperty("title")
    private String title;
    @JsonProperty("titleConcepts")
    private String titleConcepts;
    @JsonProperty("titleSurfaceForms")
    private Set<String> titleSurfaceForms;
    @JsonProperty("type")
    private String type;
    @JsonProperty("sections")
    private List<Section> sections = new ArrayList<Section>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("filename")
	public String getFilename() {
		return filename;
	}
    
    @JsonProperty("filename")
	public void setFilename(String filename) {
		this.filename = filename;
	}
    
    @JsonProperty("title")
	public String getTitle() {
		return title;
	}
    
    @JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}
    
    @JsonProperty("titleConcepts")
	public String getTitleConcepts() {
		return titleConcepts;
	}
    
    @JsonProperty("titleConcepts")
	public void setTitleConcepts(String titleConcepts) {
		this.titleConcepts = titleConcepts;
	}
    
    @JsonProperty("titleSurfaceForms")
	public Set<String> getTitleSurfaceForms() {
		return titleSurfaceForms;
	}
    
    @JsonProperty("titleSurfaceForms")
	public void setTitleSurfaceForms(Set<String> titleSurfaceForms) {
		this.titleSurfaceForms = titleSurfaceForms;
	}

    @JsonProperty("type")
	public String getType() {
		return type;
	}
    
    @JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}
    
    @JsonProperty("sections")
	public List<Section> getSections() {
		return sections;
	}
    
    @JsonProperty("sections")
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
    
    public void addSection(Section section) {
    	this.sections.add(section);
    }
    
    @JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
    
    @JsonAnySetter
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "Dmp2 [filename=" + filename + ", title=" + title + ", titleConcepts=" + titleConcepts
				+ ", titleSurfaceForms=" + titleSurfaceForms + ", type=" + type + ", sections=" + sections
				+ ", additionalProperties=" + additionalProperties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((sections == null) ? 0 : sections.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((titleConcepts == null) ? 0 : titleConcepts.hashCode());
		result = prime * result + ((titleSurfaceForms == null) ? 0 : titleSurfaceForms.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dmp2 other = (Dmp2) obj;
		if (additionalProperties == null) {
			if (other.additionalProperties != null)
				return false;
		} else if (!additionalProperties.equals(other.additionalProperties))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (sections == null) {
			if (other.sections != null)
				return false;
		} else if (!sections.equals(other.sections))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (titleConcepts == null) {
			if (other.titleConcepts != null)
				return false;
		} else if (!titleConcepts.equals(other.titleConcepts))
			return false;
		if (titleSurfaceForms == null) {
			if (other.titleSurfaceForms != null)
				return false;
		} else if (!titleSurfaceForms.equals(other.titleSurfaceForms))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
