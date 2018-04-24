package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Used to enable diagnostics.
 */
@ApiModel(description = "Used to enable diagnostics.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-03-10T18:21:49.779Z")

public class DiagnosticSwitch {
    @JsonProperty("source")
    private String source = null;

    public DiagnosticSwitch source(String source) {
        this.source = source;
        return this;
    }

    /**
     * A diagnostic source defined by the service. Would like to provides an enumeration of values.
     *
     * @return source
     **/
    @ApiModelProperty(required = true, example = "false", value = "A diagnostic source defined by the service. Would "
        + "like to provides an enumeration of values. ")
    @NotNull
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiagnosticSwitch diagnosticSwitch = (DiagnosticSwitch) o;
        return Objects.equals(this.source, diagnosticSwitch.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DiagnosticSwitch {\n");

        sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

