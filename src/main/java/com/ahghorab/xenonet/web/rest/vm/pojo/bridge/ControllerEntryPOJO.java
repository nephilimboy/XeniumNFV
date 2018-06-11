package com.ahghorab.xenonet.web.rest.vm.pojo.bridge;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ControllerEntryPOJO {
    @JsonProperty("target")
    private String target;

    @JsonProperty("is-connected")
    private String isConnected;

    @JsonProperty("controller-uuid")
    private String controllerUuid;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(String isConnected) {
        this.isConnected = isConnected;
    }

    public String getControllerUuid() {
        return controllerUuid;
    }

    public void setControllerUuid(String controllerUuid) {
        this.controllerUuid = controllerUuid;
    }
}
