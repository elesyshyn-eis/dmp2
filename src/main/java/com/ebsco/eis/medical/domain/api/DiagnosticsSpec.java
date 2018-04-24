package com.ebsco.eis.medical.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Specifies diagnostics to include in the search response.
 */
@ApiModel(description = "Specifies diagnostics to include in the search response.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-03-10T18:21:49.779Z")

public class DiagnosticsSpec {
    @JsonProperty("switches")
    private List<DiagnosticSwitch> switches = new ArrayList<DiagnosticSwitch>();

    public DiagnosticsSpec switches(List<DiagnosticSwitch> switches) {
        this.switches = switches;
        return this;
    }

    public DiagnosticsSpec addSwitchesItem(DiagnosticSwitch switchesItem) {
        this.switches.add(switchesItem);
        return this;
    }

    /**
     * Get switches.
     *
     * @return switches
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public List<DiagnosticSwitch> getSwitches() {
        return switches;
    }

    public void setSwitches(List<DiagnosticSwitch> switches) {
        this.switches = switches;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiagnosticsSpec diagnosticsSpec = (DiagnosticsSpec) o;
        return Objects.equals(this.switches, diagnosticsSpec.switches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(switches);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DiagnosticsSpec {\n");

        sb.append("    switches: ").append(toIndentedString(switches)).append("\n");
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

