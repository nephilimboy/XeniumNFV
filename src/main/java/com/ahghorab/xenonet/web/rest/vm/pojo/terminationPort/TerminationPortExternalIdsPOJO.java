package com.ahghorab.xenonet.web.rest.vm.pojo.terminationPort;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TerminationPortExternalIdsPOJO {
    @JsonProperty("external-id-key")
    private String TerminationPortExternalIdKey;

    @JsonProperty("external-id-value")
    private String TerminationPortExternalIdValue;

    public String getTerminationPortExternalIdKey() {
        return TerminationPortExternalIdKey;
    }

    public void setTerminationPortExternalIdKey(String terminationPortExternalIdKey) {
        TerminationPortExternalIdKey = terminationPortExternalIdKey;
    }

    public String getTerminationPortExternalIdValue() {
        return TerminationPortExternalIdValue;
    }

    public void setTerminationPortExternalIdValue(String terminationPortExternalIdValue) {
        TerminationPortExternalIdValue = terminationPortExternalIdValue;
    }
}
