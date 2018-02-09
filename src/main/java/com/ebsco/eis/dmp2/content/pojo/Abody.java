
package com.ebsco.eis.dmp2.content.pojo;

import java.io.Serializable;
import java.util.HashMap;
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
    "filename",
    "hidden",
    "firstNode",
    "lastNode",
    "title",
    "type",
    "documentRoot"
})
public class Abody implements Serializable
{

    @JsonProperty("filename")
    private String filename;
    @JsonProperty("hidden")
    private Boolean hidden;
    @JsonProperty("firstNode")
    private Integer firstNode;
    @JsonProperty("lastNode")
    private Integer lastNode;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("documentRoot")
    private DocumentRoot documentRoot;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4248917152489390649L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Abody() {
    }

    /**
     * 
     * @param title
     * @param hidden
     * @param firstNode
     * @param filename
     * @param lastNode
     * @param documentRoot
     * @param type
     */
    public Abody(String filename, Boolean hidden, Integer firstNode, Integer lastNode, String title, String type, DocumentRoot documentRoot) {
        super();
        this.filename = filename;
        this.hidden = hidden;
        this.firstNode = firstNode;
        this.lastNode = lastNode;
        this.title = title;
        this.type = type;
        this.documentRoot = documentRoot;
    }

    @JsonProperty("filename")
    public String getFilename() {
        return filename;
    }

    @JsonProperty("filename")
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Abody withFilename(String filename) {
        this.filename = filename;
        return this;
    }

    @JsonProperty("hidden")
    public Boolean getHidden() {
        return hidden;
    }

    @JsonProperty("hidden")
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Abody withHidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    @JsonProperty("firstNode")
    public Integer getFirstNode() {
        return firstNode;
    }

    @JsonProperty("firstNode")
    public void setFirstNode(Integer firstNode) {
        this.firstNode = firstNode;
    }

    public Abody withFirstNode(Integer firstNode) {
        this.firstNode = firstNode;
        return this;
    }

    @JsonProperty("lastNode")
    public Integer getLastNode() {
        return lastNode;
    }

    @JsonProperty("lastNode")
    public void setLastNode(Integer lastNode) {
        this.lastNode = lastNode;
    }

    public Abody withLastNode(Integer lastNode) {
        this.lastNode = lastNode;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Abody withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Abody withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("documentRoot")
    public DocumentRoot getDocumentRoot() {
        return documentRoot;
    }

    @JsonProperty("documentRoot")
    public void setDocumentRoot(DocumentRoot documentRoot) {
        this.documentRoot = documentRoot;
    }

    public Abody withDocumentRoot(DocumentRoot documentRoot) {
        this.documentRoot = documentRoot;
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

    public Abody withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("filename", filename).append("hidden", hidden).append("firstNode", firstNode).append("lastNode", lastNode).append("title", title).append("type", type).append("documentRoot", documentRoot).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(additionalProperties).append(hidden).append(firstNode).append(filename).append(lastNode).append(documentRoot).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Abody) == false) {
            return false;
        }
        Abody rhs = ((Abody) other);
        return new EqualsBuilder().append(title, rhs.title).append(additionalProperties, rhs.additionalProperties).append(hidden, rhs.hidden).append(firstNode, rhs.firstNode).append(filename, rhs.filename).append(lastNode, rhs.lastNode).append(documentRoot, rhs.documentRoot).append(type, rhs.type).isEquals();
    }

}
