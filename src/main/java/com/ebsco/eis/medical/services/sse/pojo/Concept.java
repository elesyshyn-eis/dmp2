
package com.ebsco.eis.medical.services.sse.pojo;

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
    "cid",
    "name",
    "surfaceForms",
    "hypernyms"
})
public class Concept implements Serializable
{

    @JsonProperty("cid")
    private String cid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surfaceForms")
    private List<String> surfaceForms = new ArrayList<String>();
    @JsonProperty("hypernyms")
    private List<String> hypernyms = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4651274768547231622L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Concept() {
    }

    /**
     * 
     * @param name
     * @param hypernyms
     * @param cid
     * @param surfaceForms
     */
    public Concept(String cid, String name, List<String> surfaceForms, List<String> hypernyms) {
        super();
        this.cid = cid;
        this.name = name;
        this.surfaceForms = surfaceForms;
        this.hypernyms = hypernyms;
    }

    @JsonProperty("cid")
    public String getCid() {
        return cid;
    }

    @JsonProperty("cid")
    public void setCid(String cid) {
        this.cid = cid;
    }

    public Concept withCid(String cid) {
        this.cid = cid;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Concept withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("surfaceForms")
    public List<String> getSurfaceForms() {
        return surfaceForms;
    }

    @JsonProperty("surfaceForms")
    public void setSurfaceForms(List<String> surfaceForms) {
        this.surfaceForms = surfaceForms;
    }

    public Concept withSurfaceForms(List<String> surfaceForms) {
        this.surfaceForms = surfaceForms;
        return this;
    }

    @JsonProperty("hypernyms")
    public List<String> getHypernyms() {
        return hypernyms;
    }

    @JsonProperty("hypernyms")
    public void setHypernyms(List<String> hypernyms) {
        this.hypernyms = hypernyms;
    }

    public Concept withHypernyms(List<String> hypernyms) {
        this.hypernyms = hypernyms;
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

    public Concept withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("cid", cid).append("name", name).append("surfaceForms", surfaceForms).append("hypernyms", hypernyms).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(name).append(hypernyms).append(cid).append(surfaceForms).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Concept) == false) {
            return false;
        }
        Concept rhs = ((Concept) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(name, rhs.name).append(hypernyms, rhs.hypernyms).append(cid, rhs.cid).append(surfaceForms, rhs.surfaceForms).isEquals();
    }

}
