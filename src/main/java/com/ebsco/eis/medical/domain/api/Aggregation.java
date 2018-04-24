package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Aggregation {

    @JsonProperty("field")
    private String field;

    @JsonProperty("buckets")
    private Map<String, Integer> buckets = new TreeMap<>();

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Map<String, Integer> getBuckets() {
        return buckets;
    }

    public void setBuckets(Map<String, Integer> buckets) {
        this.buckets = buckets;
    }

    public void addBucket(String key, int value) {
        this.buckets.put(key, value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buckets == null) ? 0 : buckets.hashCode());
        result = prime * result + ((field == null) ? 0 : field.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aggregation other = (Aggregation) o;
        return Objects.equals(this.buckets, other.buckets) && Objects.equals(this.field, other.field);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Aggregation {\n");

        sb.append("    field: ").append(toIndentedString(field)).append("\n");
        sb.append("    buckets: ").append(toIndentedString(buckets)).append("\n");
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
