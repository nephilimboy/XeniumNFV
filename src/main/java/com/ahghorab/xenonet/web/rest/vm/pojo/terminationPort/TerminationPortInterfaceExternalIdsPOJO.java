package com.ahghorab.xenonet.web.rest.vm.pojo.terminationPort;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TerminationPortInterfaceExternalIdsPOJO {
    @JsonProperty("external-id-key")
    private String TerminationPortInterfaceExternalIdKey;

    @JsonProperty("external-id-value")
    private String TerminationPortInterfaceExternalIdValue;

    public String getTerminationPortInterfaceExternalIdKey() {
        return TerminationPortInterfaceExternalIdKey;
    }

    public void setTerminationPortInterfaceExternalIdKey(String terminationPortInterfaceExternalIdKey) {
        TerminationPortInterfaceExternalIdKey = terminationPortInterfaceExternalIdKey;
    }

    public String getTerminationPortInterfaceExternalIdValue() {
        return TerminationPortInterfaceExternalIdValue;
    }

    public void setTerminationPortInterfaceExternalIdValue(String terminationPortInterfaceExternalIdValue) {
        TerminationPortInterfaceExternalIdValue = terminationPortInterfaceExternalIdValue;
    }
}
