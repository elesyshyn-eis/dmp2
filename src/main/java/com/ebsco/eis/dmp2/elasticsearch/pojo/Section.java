
package com.ebsco.eis.dmp2.elasticsearch.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "anchor",
    "content",
    "location",
    "weight"
})
public class Section implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5048324660972521977L;
	@JsonProperty("anchor")
	private String anchor;
	@JsonProperty("content")
	private String content;
	@JsonProperty("location")
	private String location;
	@JsonProperty("weight")
    private Double weight;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Section() {
    }

    public Section(String anchor, String content, String location) {
		super();
		this.anchor = anchor;
		this.content = content;
		this.location = location;
	}

	@JsonProperty("anchor")
	public String getAnchor() {
		return anchor;
	}

    @JsonProperty("anchor")
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

    @JsonProperty("content")
	public String getContent() {
		return content;
	}

    @JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
	}

    @JsonProperty("location")
	public String getLocation() {
		return location;
	}

    @JsonProperty("location")
	public void setLocation(String location) {
		this.location = location;
	}

    @JsonProperty("weight")
	public Double getWeight() {
		return weight;
	}

    @JsonProperty("weight")
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Section [anchor=" + anchor + ", content=" + content + ", location=" + location + ", weight=" + weight
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anchor == null) ? 0 : anchor.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Section other = (Section) obj;
		if (anchor == null) {
			if (other.anchor != null)
				return false;
		} else if (!anchor.equals(other.anchor))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	} 
}
