package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Contains summary information about the search result.
 */
@ApiModel(description = "Contains summary information about the search result.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-03-10T18:21:49.779Z")

public class SearchInfo {
    @JsonProperty("totalHits")
    private Long totalHits = null;

    @JsonProperty("searchTimeInMs")
    private Long searchTimeInMs = null;

    @JsonProperty("resultSetId")
    private String resultSetId;

    public SearchInfo totalHits(Long totalHits) {
        this.totalHits = totalHits;
        return this;
    }

    /**
     * The total number of records that satisfied the requested query.
     *
     * @return totalHits
     **/
    @ApiModelProperty(required = true, value = "The total number of records that satisfied the requested query. ")
    @NotNull
    public Long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Long totalHits) {
        this.totalHits = totalHits;
    }

    public SearchInfo searchTimeInMs(Long searchTimeInMs) {
        this.searchTimeInMs = searchTimeInMs;
        return this;
    }

    /**
     * The number of milliseconds required to respond to the search request.
     *
     * @return searchTimeInMs
     **/
    @ApiModelProperty(required = true, value = "The number of milliseconds required to respond to the search request. ")
    @NotNull
    public Long getSearchTimeInMs() {
        return searchTimeInMs;
    }

    public void setSearchTimeInMs(Long searchTimeInMs) {
        this.searchTimeInMs = searchTimeInMs;
    }

    /**
     * The GUId required to respond to the search request.
     *
     * @return GUId
     **/
    public String getResultSetId() {
        return resultSetId;
    }

    public void setResultSetId(String uuid) {
        this.resultSetId = uuid;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchInfo searchInfo = (SearchInfo) o;
        return Objects.equals(this.totalHits, searchInfo.totalHits)
            && Objects.equals(this.searchTimeInMs, searchInfo.searchTimeInMs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalHits, searchTimeInMs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchInfo {\n");

        sb.append("    totalHits: ").append(toIndentedString(totalHits)).append("\n");
        sb.append("    searchTimeInMs: ").append(toIndentedString(searchTimeInMs)).append("\n");
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
