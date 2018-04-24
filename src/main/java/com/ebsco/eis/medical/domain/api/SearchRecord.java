package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * SearchRecord.
 */

public class SearchRecord implements Comparable<SearchRecord> {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("rrScore")
    private Integer rrScore = null;

    @JsonProperty("content")
    private Object content = null;

    @JsonProperty("_explanation")
    private Object explain;

    @JsonProperty("snippets")
    private Object snippets;

    public SearchRecord id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Unique record ID - a structured accession number (AN).
     *
     * @return id
     **/
    @ApiModelProperty(required = true, value = "Unique record ID - a structured accession number (AN).")
    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SearchRecord rrScore(Integer rrScore) {
        this.rrScore = rrScore;
        return this;
    }

    /**
     * Relvance ranking score.
     *
     * @return rrScore
     **/
    @ApiModelProperty(required = true, value = "Relvance ranking score.")
    @NotNull
    public Integer getRrScore() {
        return rrScore;
    }

    public void setRrScore(Integer rrScore) {
        this.rrScore = rrScore;
    }

    public SearchRecord content(Object content) {
        this.content = content;
        return this;
    }

    /**
     * A JSON object containing the fields specified in the searchRequest.recordSpec.fields. If no fields were
     * specified, an empty object \"{}\" is returned.
     *
     * @return content
     **/
    @ApiModelProperty(required = true, value = "A JSON object containing the fields specified in the searchRequest"
        + ".recordSpec.fields. If no fields were specified, an empty object \"{}\" is returned. ")
    @NotNull
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public SearchRecord explain(Object explain) {
        this.explain = explain;
        return this;
    }

    public Object getExplain() {
        return explain;
    }

    public void setExplain(Object explain) {
        this.explain = explain;
    }

    public SearchRecord snippets(Object snippets) {
        this.snippets = snippets;
        return this;
    }

    public Object getSnippets() {
        return snippets;
    }

    public void setSnippet(Object snippets) {
        this.snippets = snippets;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchRecord searchRecord = (SearchRecord) o;
        return Objects.equals(this.id, searchRecord.id)
            && Objects.equals(this.rrScore, searchRecord.rrScore)
            && Objects.equals(this.content, searchRecord.content)
            && Objects.equals(this.explain, searchRecord.explain)
            && Objects.equals(this.snippets, searchRecord.snippets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rrScore, content, explain, snippets);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchRecord {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    rrScore: ").append(toIndentedString(rrScore)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    explain:  ").append(toIndentedString(explain)).append("\n");
        sb.append("    snippet:  ").append(toIndentedString(snippets)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    /**
     * Orders by score descending.
     */
    @Override
    public int compareTo(SearchRecord o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (o == this) {
            return 0;
        }
        return o.getRrScore() - this.getRrScore();
    }
}

