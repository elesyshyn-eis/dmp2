package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Filters.
 */
public class Filters {

    @JsonProperty("excludedIds")
    private List<String> excludedIds = new ArrayList<>();

    @JsonProperty("contentDirectoryStructures")
    private List<String> cdsPaths = new ArrayList<>();
    
    @JsonProperty("includedIds")
    private List<String> includedIds = new ArrayList<>();

    /**
     * Check if all filters are empty.
     * @return filters are empty.
     */
    public boolean isEmpty() {
        return excludedIds.isEmpty() && cdsPaths.isEmpty() && includedIds.isEmpty();
    }

    /**
     * exclude ids.
     *
     * @param excludedIds ids
     * @return filters
     */
    public Filters excludedIds(List<String> excludedIds) {
        for (String excludedId : excludedIds) {
            addExcludedIdsItem(excludedId);
        }
        return this;
    }
    
    /**
     * include ids.
     *
     * @param includedIds ids
     * @return filters
     */
    public Filters includedIds(List<String> includedIds) {
        for (String includedId : includedIds) {
            addIncludedIdsItem(includedId);
        }
        return this;
    }

    /**
     * Adds given item to the list of excluded IDs.
     *
     * @param excludedIdsItem item
     * @return filters
     */
    public Filters addExcludedIdsItem(String excludedIdsItem) {
        if (!excludedIdsItem.trim().isEmpty()) {
            this.excludedIds.add(excludedIdsItem);
        }
        return this;
    }
    
    /**
     * Adds given item to the list of included IDs.
     *
     * @param includedIdsItem item
     * @return filters
     */
    public Filters addIncludedIdsItem(String includedIdsItem) {
        if (!includedIdsItem.trim().isEmpty()) {
            this.includedIds.add(includedIdsItem);
        }
        return this;
    }
    
    /**
     * A list of record ID's that must be excluded from the results.
     *
     * @return excludedIdList
     **/
    @ApiModelProperty(value = "A list of record ID's that must be excluded from the results.")
    public List<String> getExcludedIds() {
        return excludedIds;
    }
    
    /**
     * A list of record ID's that must be included in the results.
     * These records would only be included in the results if they match the remaining search criteria.
     * @return includedIdList
     **/
    @ApiModelProperty(value = "A list of record ID's that must be included in the results.")
    public List<String> getIncludedIds() {
        return includedIds;
    }
    
    /**
     * set excluded ids.
     *
     * @param excludedIds excluded.
     */
    public void setExcludedIds(List<String> excludedIds) {
        for (String excludedId : excludedIds) {
            addExcludedIdsItem(excludedId);
        }
    }
    
    /**
     * set included ids.
     *
     * @param includedIds excluded.
     */
    public void setIncludedIds(List<String> includedIds) {
        for (String includedId : includedIds) {
            addIncludedIdsItem(includedId);
        }
    }

    /**
     * A list of cds paths to filter by.
     *
     * @return cds paths
     */
    public List<String> getCdsPaths() {
        return cdsPaths;
    }

    /**
     * set cds path.
     *
     * @param cdsPaths cds paths
     * @return filters
     */
    public Filters setCdsPaths(List<String> cdsPaths) {
        for (String cdsPath : cdsPaths) {
            addCdsPath(cdsPath);
        }
        return this;
    }

    /**
     * add cds path.
     *
     * @param cdsPath - cds path
     * @return filters
     */
    public Filters addCdsPath(String cdsPath) {
        if (!cdsPath.trim().isEmpty()) {
            this.cdsPaths.add(cdsPath);
        }
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdsPaths == null) ? 0 : cdsPaths.hashCode());
        result = prime * result + ((excludedIds == null) ? 0 : excludedIds.hashCode());
        result = prime * result + ((includedIds == null) ? 0 : includedIds.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Filters other = (Filters) obj;
        if (cdsPaths == null) {
            if (other.cdsPaths != null) {
                return false;
            }
        } else if (!cdsPaths.equals(other.cdsPaths)) {
            return false;
        }
        if (excludedIds == null) {
            if (other.excludedIds != null) {
                return false;
            }
        } else if (!excludedIds.equals(other.excludedIds)) {
            return false;
        }
        if (includedIds == null) {
            if (other.includedIds != null) {
                return false;
            }
        } else if (!includedIds.equals(other.includedIds)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Filters {\n");
        sb.append("    cds: ").append(toIndentedString(cdsPaths)).append("\n");
        sb.append("    excludedIds: ").append(toIndentedString(excludedIds)).append("\n");
        sb.append("    includedIds: ").append(toIndentedString(includedIds)).append("\n");
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
