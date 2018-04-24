package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Specifies the records to include in the response. The caller could supply and empty recordSpec if no records are
 * needed - the caller might only be interested in the number of search hits.
 */
@ApiModel(description = "Specifies the records to include in the response. The caller could supply and empty "
    + "recordSpec if no records are needed - the caller might only be interested in the number of search hits. ")

public class RecordSpec {
    @JsonProperty("fields")
    private List<FieldSpec> fields = new ArrayList<>();

    @JsonProperty("size")
    private Integer size = 0;

    @JsonProperty("offset")
    private Integer offset = 1;

    public RecordSpec fields(List<FieldSpec> fields) {
        this.fields = fields;
        return this;
    }

    public RecordSpec addFieldsItem(FieldSpec fieldsItem) {
        this.fields.add(fieldsItem);
        return this;
    }

    /**
     * The array can be undefined or empty. In this case, no metadata is returned. Each record in the response would
     * contain only an ID and a relevance ranking.
     *
     * @return fields
     **/
    @ApiModelProperty(value = "The array can be undefined or empty. In this case, no metadata is returned. Each "
        + "record in the response would contain only an ID and a relevance ranking. ")
    public List<FieldSpec> getFields() {
        return fields;
    }

    public void setFields(List<FieldSpec> fields) {
        this.fields = fields;
    }

    public RecordSpec size(Integer size) {
        this.size = size;
        return this;
    }

    /**
     * The number of records to return in the response. If not specified, default = 0.
     *
     * @return size
     **/
    @ApiModelProperty(required = true, example = "10", value = "The number of records to return in the response. If "
        + "not specified, default = 0.")
    @NotNull
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public RecordSpec offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    /**
     * The starting offset from which to return records. The first record offset = 1. This value is not validated if
     * size == 0. If size > 0, then offset <= 0 causes an error response. If not specified, the default = 1.
     *
     * @return offset
     **/
    @ApiModelProperty(required = true, example = "1", value = "The starting offset from which to return records. The "
        + "first record offset = 1. This value is not validated if size == 0. If size > 0, then offset <= 0 causes "
        + "an error response. If not specified, the default = 1. ")
    @NotNull
    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordSpec recordSpec = (RecordSpec) o;
        return Objects.equals(this.fields, recordSpec.fields)
            && Objects.equals(this.size, recordSpec.size)
            && Objects.equals(this.offset, recordSpec.offset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields, size, offset);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RecordSpec {\n");

        sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
        sb.append("    size: ").append(toIndentedString(size)).append("\n");
        sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
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

