
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
    "tokens",
    "tbId",
    "tbKeys",
    "concepts"
})
public class Mapping implements Serializable
{

    @JsonProperty("tokens")
    private List<String> tokens = new ArrayList<String>();
    @JsonProperty("tbId")
    private String tbId;
    @JsonProperty("tbKeys")
    private List<Integer> tbKeys = new ArrayList<Integer>();
    @JsonProperty("concepts")
    private List<Concept> concepts = new ArrayList<Concept>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -9172764116943589610L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Mapping() {
    }

    /**
     * 
     * @param tbId
     * @param tbKeys
     * @param tokens
     * @param concepts
     */
    public Mapping(List<String> tokens, String tbId, List<Integer> tbKeys, List<Concept> concepts) {
        super();
        this.tokens = tokens;
        this.tbId = tbId;
        this.tbKeys = tbKeys;
        this.concepts = concepts;
    }

    @JsonProperty("tokens")
    public List<String> getTokens() {
        return tokens;
    }

    @JsonProperty("tokens")
    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public Mapping withTokens(List<String> tokens) {
        this.tokens = tokens;
        return this;
    }

    @JsonProperty("tbId")
    public String getTbId() {
        return tbId;
    }

    @JsonProperty("tbId")
    public void setTbId(String tbId) {
        this.tbId = tbId;
    }

    public Mapping withTbId(String tbId) {
        this.tbId = tbId;
        return this;
    }

    @JsonProperty("tbKeys")
    public List<Integer> getTbKeys() {
        return tbKeys;
    }

    @JsonProperty("tbKeys")
    public void setTbKeys(List<Integer> tbKeys) {
        this.tbKeys = tbKeys;
    }

    public Mapping withTbKeys(List<Integer> tbKeys) {
        this.tbKeys = tbKeys;
        return this;
    }

    @JsonProperty("concepts")
    public List<Concept> getConcepts() {
        return concepts;
    }

    @JsonProperty("concepts")
    public void setConcepts(List<Concept> concepts) {
        this.concepts = concepts;
    }

    public Mapping withConcepts(List<Concept> concepts) {
        this.concepts = concepts;
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

    public Mapping withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("tokens", tokens).append("tbId", tbId).append("tbKeys", tbKeys).append("concepts", concepts).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tbId).append(tbKeys).append(additionalProperties).append(tokens).append(concepts).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Mapping) == false) {
            return false;
        }
        Mapping rhs = ((Mapping) other);
        return new EqualsBuilder().append(tbId, rhs.tbId).append(tbKeys, rhs.tbKeys).append(additionalProperties, rhs.additionalProperties).append(tokens, rhs.tokens).append(concepts, rhs.concepts).isEquals();
    }

}
