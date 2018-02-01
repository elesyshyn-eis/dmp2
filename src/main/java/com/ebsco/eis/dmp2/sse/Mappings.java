
package com.ebsco.eis.dmp2.sse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "processingTime",
    "version",
    "isComplete",
    "mappings"
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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7948451533369056038L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Mappings() {
    }

    /**
     * 
     * @param isComplete
     * @param mappings
     * @param processingTime
     * @param type
     * @param version
     */
    public Mappings(String type, Integer processingTime, Integer version, Boolean isComplete, List<Mapping> mappings) {
        super();
        this.type = type;
        this.processingTime = processingTime;
        this.version = version;
        this.isComplete = isComplete;
        this.mappings = mappings;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Mappings withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("type", type).append("processingTime", processingTime).append("version", version).append("isComplete", isComplete).append("mappings", mappings).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isComplete).append(additionalProperties).append(mappings).append(processingTime).append(type).append(version).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Mappings) == false) {
            return false;
        }
        Mappings rhs = ((Mappings) other);
        return new EqualsBuilder().append(isComplete, rhs.isComplete).append(additionalProperties, rhs.additionalProperties).append(mappings, rhs.mappings).append(processingTime, rhs.processingTime).append(type, rhs.type).append(version, rhs.version).isEquals();
    }

}
