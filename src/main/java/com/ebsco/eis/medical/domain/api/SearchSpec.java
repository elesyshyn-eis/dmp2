package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * SearchSpec.
 */

public class SearchSpec {
    @JsonProperty("query")
    private Query query = new Query();

    @JsonProperty("filters")
    private Filters filters = new Filters();

    @JsonProperty("aggregations")
    private List<String> aggregations = new ArrayList<>();
    
    @JsonProperty("snippets")
    private Snippets snippets = new Snippets();

    public SearchSpec query(Query query) {
        this.query = query;
        return this;
    }

    /**
     * Get query.
     *
     * @return query
     **/
    @ApiModelProperty(value = "")
    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public SearchSpec filters(Filters filters) {
        this.filters = filters;
        return this;
    }

    /**
     * Get filters.
     *
     * @return filters
     **/
    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public List<String> getAggregations() {
        return aggregations;
    }

    /**
     * set aggregations.
     *
     * @param aggregations aggregations
     */
    public void setAggregations(List<String> aggregations) {
        if (aggregations != null) {
            for (String aggregation : aggregations) {
                addAggregation(aggregation);
            }
        }
    }

    /**
     * add aggregation.
     *
     * @param aggregation aggregation
     */
    public void addAggregation(String aggregation) {
        if (StringUtils.isNotBlank(aggregation)) {
            this.aggregations.add(aggregation);
        }
    }

    public SearchSpec aggregations(List<String> aggregations) {
        setAggregations(aggregations);
        return this;
    }
    
    /**
     * Get snippets.
     *
     * @return snippets
     **/
    public Snippets getSnippets() {
        return snippets;
    }

    public void setSnippets(Snippets snippets) {
        this.snippets = snippets;
    }

    public SearchSpec snippets(Snippets snippets) {
        setSnippets(snippets);
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchSpec searchSpec = (SearchSpec) o;
        return Objects.equals(this.query, searchSpec.query)
            && Objects.equals(this.filters, searchSpec.filters)
            && Objects.equals(this.aggregations, searchSpec.aggregations)
            && Objects.equals(this.snippets, searchSpec.snippets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, filters, aggregations, snippets);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchSpec {\n");

        sb.append("    query: ").append(toIndentedString(query)).append("\n");
        sb.append("    filters: ").append(toIndentedString(filters)).append("\n");
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
