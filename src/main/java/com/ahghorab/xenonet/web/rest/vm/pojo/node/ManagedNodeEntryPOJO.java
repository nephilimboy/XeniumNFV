package com.ahghorab.xenonet.web.rest.vm.pojo.node;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagedNodeEntryPOJO {
    @JsonProperty("bridge-ref")
    private String bridgeRef;

    public String getBridgeRef() {
        return bridgeRef;
    }

    public void setBridgeRef(String bridgeRef) {
        this.bridgeRef = bridgeRef;
    }
}
