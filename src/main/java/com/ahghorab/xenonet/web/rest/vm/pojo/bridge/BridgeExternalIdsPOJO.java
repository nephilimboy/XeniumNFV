package com.ahghorab.xenonet.web.rest.vm.pojo.bridge;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BridgeExternalIdsPOJO {
    @JsonProperty("bridge-external-id-key")
    private String bridgeExternalIdKey;

    @JsonProperty("bridge-external-id-value")
    private String bridgeExternalIdValue;

    public String getBridgeExternalIdKey() {
        return bridgeExternalIdKey;
    }

    public void setBridgeExternalIdKey(String bridgeExternalIdKey) {
        this.bridgeExternalIdKey = bridgeExternalIdKey;
    }

    public String getBridgeExternalIdValue() {
        return bridgeExternalIdValue;
    }

    public void setBridgeExternalIdValue(String bridgeExternalIdValue) {
        this.bridgeExternalIdValue = bridgeExternalIdValue;
    }
}
