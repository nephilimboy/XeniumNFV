package com.ahghorab.xenonet.web.rest.vm.pojo.node;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagerEntryPOJO {
    @JsonProperty("target")
    private String target;

    @JsonProperty("connected")
    private String connected;

    @JsonProperty("number_of_connections")
    private String numberOfConnections;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getConnected() {
        return connected;
    }

    public void setConnected(String connected) {
        this.connected = connected;
    }

    public String getNumberOfConnections() {
        return numberOfConnections;
    }

    public void setNumberOfConnections(String numberOfConnections) {
        this.numberOfConnections = numberOfConnections;
    }
}
