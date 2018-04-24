package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * SearchResponse.
 */

public class SearchResponse implements ApiResponse {

    @JsonProperty("callStatus")
    private CallStatus callStatus = null;

    @JsonProperty("searchInfo")
    private SearchInfo searchInfo = null;

    @JsonProperty("recordSets")
    private List<RecordSet> recordSets = new ArrayList<>();

    public SearchResponse callStatus(CallStatus callStatus) {
        this.callStatus = callStatus;
        return this;
    }

    /**
     * Get callStatus.
     *
     * @return callStatus
     **/
    @ApiModelProperty(required = true, value = "HttpStatus and message.")
    public CallStatus getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(CallStatus callStatus) {
        this.callStatus = callStatus;
    }

    public SearchResponse searchInfo(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
        return this;
    }

    /**
     * Get searchInfo.
     *
     * @return searchInfo
     **/
    @ApiModelProperty(required = true, value = "Information about the completed search.")
    @NotNull
    public SearchInfo getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
    }

    public SearchResponse recordSets(List<RecordSet> recordSets) {
        this.recordSets = recordSets;
        return this;
    }

    public SearchResponse addRecordSetsItem(RecordSet recordSetsItem) {
        this.recordSets.add(recordSetsItem);
        return this;
    }

    /**
     * An array of recordSet objects. Currently, a response will contain at most 1 recordSet.
     *
     * @return recordSets
     **/
    @ApiModelProperty(required = true, value = "An array of recordSet objects. Currently, a response will contain at "
        + "most 1 recordSet. ")
    @NotNull
    public List<RecordSet> getRecordSets() {
        return recordSets;
    }

    public void setRecordSets(List<RecordSet> recordSets) {
        this.recordSets = recordSets;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchResponse searchResponse = (SearchResponse) o;
        return Objects.equals(this.callStatus, searchResponse.callStatus)
            && Objects.equals(this.searchInfo, searchResponse.searchInfo)
            && Objects.equals(this.recordSets, searchResponse.recordSets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callStatus, searchInfo, recordSets);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchResponse {\n");

        sb.append("    callStatus: ").append(toIndentedString(callStatus)).append("\n");
        sb.append("    searchInfo: ").append(toIndentedString(searchInfo)).append("\n");
        sb.append("    recordSets: ").append(toIndentedString(recordSets)).append("\n");
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

    public boolean hasHits() {
        return this.searchInfo != null && this.searchInfo.getTotalHits() > 0L;
    }
}

