package com.ahghorab.xenonet.web.rest.vm.pojo.bridge;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProtocolEntryPOJO {
    @JsonProperty("protocol")
    private String protocol;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
