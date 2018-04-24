package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Query.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-03-10T18:21:49.779Z")

public class Query {
    @JsonProperty("keyphrase")
    private String keyphrase = null;

    public Query keyphrase(String keyphrase) {
        this.keyphrase = keyphrase;
        return this;
    }

    /**
     * A string to search for.
     *
     * @return keyphrase
     **/
    @ApiModelProperty(required = true, example = "bed", value = "A string to search for.")
    @NotNull
    public String getKeyphrase() {
        return keyphrase;
    }

    public void setKeyphrase(String keyphrase) {
        this.keyphrase = keyphrase;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Query query = (Query) o;
        return Objects.equals(this.keyphrase, query.keyphrase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyphrase);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Query {\n");

        sb.append("    keyphrase: ").append(toIndentedString(keyphrase)).append("\n");
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

