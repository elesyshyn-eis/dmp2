
package com.ebsco.eis.dmp2.content.pojo;

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
    "nodeId",
    "location",
    "isTopicOrSection",
    "anchor",
    "content",
    "parent",
    "isConclusion",
    "children"
})
public class Child implements Serializable
{

    @JsonProperty("nodeId")
    private Integer nodeId;
    @JsonProperty("location")
    private String location;
    @JsonProperty("isTopicOrSection")
    private Boolean isTopicOrSection;
    @JsonProperty("anchor")
    private String anchor;
    @JsonProperty("content")
    private String content;
    @JsonProperty("parent")
    private Integer parent;
    @JsonProperty("isConclusion")
    private Boolean isConclusion;
    @JsonProperty("children")
    private List<Child> children = new ArrayList<Child>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -579541978825427308L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Child() {
    }

    /**
     * 
     * @param content
     * @param nodeId
     * @param location
     * @param isTopicOrSection
     * @param children
     * @param parent
     * @param isConclusion
     * @param anchor
     */
    public Child(Integer nodeId, String location, Boolean isTopicOrSection, String anchor, String content, Integer parent, Boolean isConclusion, List<Child> children) {
        super();
        this.nodeId = nodeId;
        this.location = location;
        this.isTopicOrSection = isTopicOrSection;
        this.anchor = anchor;
        this.content = content;
        this.parent = parent;
        this.isConclusion = isConclusion;
        this.children = children;
    }

    @JsonProperty("nodeId")
    public Integer getNodeId() {
        return nodeId;
    }

    @JsonProperty("nodeId")
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Child withNodeId(Integer nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    public Child withLocation(String location) {
        this.location = location;
        return this;
    }

    @JsonProperty("isTopicOrSection")
    public Boolean getIsTopicOrSection() {
        return isTopicOrSection;
    }

    @JsonProperty("isTopicOrSection")
    public void setIsTopicOrSection(Boolean isTopicOrSection) {
        this.isTopicOrSection = isTopicOrSection;
    }

    public Child withIsTopicOrSection(Boolean isTopicOrSection) {
        this.isTopicOrSection = isTopicOrSection;
        return this;
    }

    @JsonProperty("anchor")
    public String getAnchor() {
        return anchor;
    }

    @JsonProperty("anchor")
    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public Child withAnchor(String anchor) {
        this.anchor = anchor;
        return this;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    public Child withContent(String content) {
        this.content = content;
        return this;
    }

    @JsonProperty("parent")
    public Integer getParent() {
        return parent;
    }

    @JsonProperty("parent")
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Child withParent(Integer parent) {
        this.parent = parent;
        return this;
    }

    @JsonProperty("isConclusion")
    public Boolean getIsConclusion() {
        return isConclusion;
    }

    @JsonProperty("isConclusion")
    public void setIsConclusion(Boolean isConclusion) {
        this.isConclusion = isConclusion;
    }

    public Child withIsConclusion(Boolean isConclusion) {
        this.isConclusion = isConclusion;
        return this;
    }

    @JsonProperty("children")
    public List<Child> getChildren() {
        return children;
    }

    @JsonProperty("children")
    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public Child withChildren(List<Child> children) {
        this.children = children;
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

    public Child withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("nodeId", nodeId).append("location", location).append("isTopicOrSection", isTopicOrSection).append("anchor", anchor).append("content", content).append("parent", parent).append("isConclusion", isConclusion).append("children", children).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(content).append(nodeId).append(location).append(additionalProperties).append(isTopicOrSection).append(children).append(parent).append(isConclusion).append(anchor).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Child) == false) {
            return false;
        }
        Child rhs = ((Child) other);
        return new EqualsBuilder().append(content, rhs.content).append(nodeId, rhs.nodeId).append(location, rhs.location).append(additionalProperties, rhs.additionalProperties).append(isTopicOrSection, rhs.isTopicOrSection).append(children, rhs.children).append(parent, rhs.parent).append(isConclusion, rhs.isConclusion).append(anchor, rhs.anchor).isEquals();
    }

}
