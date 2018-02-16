
package com.ebsco.eis.dmp2.sse.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "processingTime",
    "version",
    "isComplete",
    "mappings",
    "searchVector"
})
public class Mappings implements Serializable
{

    @JsonProperty("type")
    private String type;
    @JsonProperty("processingTime")
    private Integer processingTime;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("isComplete")
    private Boolean isComplete;
    @JsonProperty("mappings")
    private List<Mapping> mappings = new ArrayList<Mapping>();
    @JsonProperty("searchVector")
    private String searchVector;
    private final static long serialVersionUID = 7948451533369056038L;

    public Mappings() {
    }

    public Mappings(String type, Integer processingTime, Integer version, Boolean isComplete, List<Mapping> mappings, String searchVector) {
        super();
        this.type = type;
        this.processingTime = processingTime;
        this.version = version;
        this.isComplete = isComplete;
        this.mappings = mappings;
        this.searchVector = searchVector;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Mappings withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("processingTime")
    public Integer getProcessingTime() {
        return processingTime;
    }

    @JsonProperty("processingTime")
    public void setProcessingTime(Integer processingTime) {
        this.processingTime = processingTime;
    }

    public Mappings withProcessingTime(Integer processingTime) {
        this.processingTime = processingTime;
        return this;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    public Mappings withVersion(Integer version) {
        this.version = version;
        return this;
    }

    @JsonProperty("isComplete")
    public Boolean getIsComplete() {
        return isComplete;
    }

    @JsonProperty("isComplete")
    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Mappings withIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    @JsonProperty("mappings")
    public List<Mapping> getMappings() {
        return mappings;
    }

    @JsonProperty("mappings")
    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
    }

    public Mappings withMappings(List<Mapping> mappings) {
        this.mappings = mappings;
        return this;
    }

    @JsonProperty("searchVector")
    public String getSearchVector() {
        return searchVector;
    }

    @JsonProperty("searchVector")
    public void setSearchVector(String searchVector) {
        this.searchVector = searchVector;
    }

	@Override
	public String toString() {
		return "Mappings [type=" + type + ", processingTime=" + processingTime + ", version=" + version
				+ ", isComplete=" + isComplete + ", mappings=" + mappings + ", searchVector=" + searchVector + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isComplete == null) ? 0 : isComplete.hashCode());
		result = prime * result + ((mappings == null) ? 0 : mappings.hashCode());
		result = prime * result + ((processingTime == null) ? 0 : processingTime.hashCode());
		result = prime * result + ((searchVector == null) ? 0 : searchVector.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		Mappings other = (Mappings) obj;
		if (isComplete == null) {
			if (other.isComplete != null)
				return false;
		} else if (!isComplete.equals(other.isComplete))
			return false;
		if (mappings == null) {
			if (other.mappings != null)
				return false;
		} else if (!mappings.equals(other.mappings))
			return false;
		if (processingTime == null) {
			if (other.processingTime != null)
				return false;
		} else if (!processingTime.equals(other.processingTime))
			return false;
		if (searchVector == null) {
			if (other.searchVector != null)
				return false;
		} else if (!searchVector.equals(other.searchVector))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
}
