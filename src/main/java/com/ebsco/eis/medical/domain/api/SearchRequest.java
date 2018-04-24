package com.ebsco.eis.medical.domain.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * SearchRequest.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchRequest implements ApiRequest {

    @JsonProperty("searchSpec")
    private SearchSpec searchSpec = new SearchSpec();

    @JsonProperty("recordSpec")
    private RecordSpec recordSpec = new RecordSpec();

    @JsonProperty("diagnosticsSpec")
    private DiagnosticsSpec diagnosticsSpec = null;

    public SearchRequest searchSpec(SearchSpec searchSpec) {
        this.searchSpec = searchSpec;
        return this;
    }

    /**
     * Get searchSpec.
     *
     * @return searchSpec
     **/
    @ApiModelProperty(value = "")
    public SearchSpec getSearchSpec() {
        return searchSpec;
    }

    public void setSearchSpec(SearchSpec searchSpec) {
        this.searchSpec = searchSpec;
    }

    public SearchRequest recordSpec(RecordSpec recordSpec) {
        this.recordSpec = recordSpec;
        return this;
    }

    /**
     * Get recordSpec.
     *
     * @return recordSpec
     **/
    @ApiModelProperty(value = "")
    public RecordSpec getRecordSpec() {
        return recordSpec;
    }

    public void setRecordSpec(RecordSpec recordSpec) {
        this.recordSpec = recordSpec;
    }

    public SearchRequest diagnosticsSpec(DiagnosticsSpec diagnosticsSpec) {
        this.diagnosticsSpec = diagnosticsSpec;
        return this;
    }

    /**
     * Get diagnosticsSpec.
     *
     * @return diagnosticsSpec
     **/
    @ApiModelProperty(value = "")
    public DiagnosticsSpec getDiagnosticsSpec() {
        return diagnosticsSpec;
    }

    public void setDiagnosticsSpec(DiagnosticsSpec diagnosticsSpec) {
        this.diagnosticsSpec = diagnosticsSpec;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchRequest searchRequest = (SearchRequest) o;
        return Objects.equals(this.searchSpec, searchRequest.searchSpec)
            && Objects.equals(this.recordSpec, searchRequest.recordSpec)
            && Objects.equals(this.diagnosticsSpec, searchRequest.diagnosticsSpec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchSpec, recordSpec, diagnosticsSpec);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchRequest {\n");
        sb.append("    searchSpec: ").append(toIndentedString(searchSpec)).append("\n");
        sb.append("    recordSpec: ").append(toIndentedString(recordSpec)).append("\n");
        sb.append("    diagnosticsSpec: ").append(toIndentedString(diagnosticsSpec)).append("\n");
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
}

