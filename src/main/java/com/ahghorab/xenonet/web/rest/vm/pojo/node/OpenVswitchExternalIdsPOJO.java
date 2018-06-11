package com.ahghorab.xenonet.web.rest.vm.pojo.node;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenVswitchExternalIdsPOJO {
    @JsonProperty("external-id-key")
    private String ExternalIdKey;

    @JsonProperty("external-id-value")
    private String ExternalIdValue;

    public String getExternalIdKey() {
        return ExternalIdKey;
    }

    public void setExternalIdKey(String externalIdKey) {
        ExternalIdKey = externalIdKey;
    }

    public String getExternalIdValue() {
        return ExternalIdValue;
    }

    public void setExternalIdValue(String externalIdValue) {
        ExternalIdValue = externalIdValue;
    }
}
