package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * The set of records as defined by searchRequest.recordSpec
 */
@ApiModel(description = "The set of records as defined by searchRequest.recordSpec ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-03-10T18:21:49.779Z")

public class RecordSet {
    @JsonProperty("size")
    private Integer size = null;

    @JsonProperty("nextOffset")
    private Integer nextOffset = null;

    @JsonProperty("records")
    private List<SearchRecord> records = new ArrayList<SearchRecord>();

    @JsonProperty("invalidFieldsRequested")
    private List<String> invalidFieldsRequested = new ArrayList<String>();

    @JsonProperty("aggregations")
    private List<Aggregation> aggregations = new ArrayList<>();

    public RecordSet size(Integer size) {
        this.size = size;
        return this;
    }

    /**
     * The number of records in the recordSet. The value will be >= searchRequest.recordSpec.size.
     *
     * @return size
     **/
    @ApiModelProperty(required = true, value = "The number of records in the recordSet. The value will be >= "
        + "searchRequest.recordSpec.size. ")
    @NotNull
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public RecordSet nextOffset(Integer nextOffset) {
        this.nextOffset = nextOffset;
        return this;
    }

    /**
     * The next offset to specify in searchRequest.recordSpec.offset if the caller wants to 'page' through the search
     * results. -1 will be returned when the end of the recordSet was reached.
     *
     * @return nextOffset
     **/
    @ApiModelProperty(required = true, value = "The next offset to specify in searchRequest.recordSpec.offset if the "
        + "caller wants to 'page' through the search results. -1 will be returned when the end of the recordSet was"
        + " reached. ")
    @NotNull
    public Integer getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(Integer nextOffset) {
        this.nextOffset = nextOffset;
    }

    public RecordSet records(List<SearchRecord> records) {
        this.records = records;
        return this;
    }

    public RecordSet addRecordsItem(SearchRecord recordsItem) {
        this.records.add(recordsItem);
        return this;
    }

    /**
     * An array of records as specified by searchRequest.recordSpec.
     *
     * @return records
     **/
    @ApiModelProperty(required = true, value = "An array of records as specified by searchRequest.recordSpec. ")
    @NotNull
    public List<SearchRecord> getRecords() {
        return records;
    }

    public void setRecords(List<SearchRecord> records) {
        this.records = records;
    }

    /**
     * An array of field names, specified in the request, that were not defined for the database.
     *
     * @return invalidFieldsRequested
     **/
    @ApiModelProperty(required = true, value = "An array of field names, specified in the request, that were not "
        + "defined for the database. ")
    @NotNull
    public List<String> getInvalidFieldsRequested() {
        return invalidFieldsRequested;
    }

    public void setInvalidFieldsRequested(List<String> invalidFieldsRequested) {
        this.invalidFieldsRequested = invalidFieldsRequested;
    }

    public boolean hasInvalidFieldsRequestedItem() {
        return !invalidFieldsRequested.isEmpty();
    }

    @ApiModelProperty(required = false, value = "An array of aggregations for this request. ")
    @NotNull
    public List<Aggregation> getAggregations() {
        return aggregations;
    }

    public void setAggregation(List<Aggregation> aggregations) {
        this.aggregations = aggregations;
    }

    public void addAggregation(Aggregation aggregation) {
        this.aggregations.add(aggregation);
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordSet recordSet = (RecordSet) o;
        return Objects.equals(this.size, recordSet.size)
            && Objects.equals(this.nextOffset, recordSet.nextOffset)
            && Objects.equals(this.records, recordSet.records)
            && Objects.equals(this.invalidFieldsRequested, recordSet.invalidFieldsRequested)
            && Objects.equals(this.aggregations, recordSet.aggregations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, nextOffset, records, invalidFieldsRequested, aggregations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RecordSet {\n");

        sb.append("    size: ").append(toIndentedString(size)).append("\n");
        sb.append("    nextOffset: ").append(toIndentedString(nextOffset)).append("\n");
        sb.append("    records: ").append(toIndentedString(records)).append("\n");
        sb.append("    invalidFieldsRequested: ").append(toIndentedString(invalidFieldsRequested)).append("\n");
        sb.append("    aggregations: ").append(toIndentedString(aggregations)).append("\n");
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

