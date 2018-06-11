package com.ahghorab.xenonet.web.rest.vm.pojo.node;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectioInfoPOJO {

    @JsonProperty("local-ip")
    private String localIp;

    @JsonProperty("local-port")
    private String localPort;

    @JsonProperty("remote-ip")
    private String remoteIp;

    @JsonProperty("remote-port")
    private String remotePort;

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public String getLocalPort() {
        return localPort;
    }

    public void setLocalPort(String localPort) {
        this.localPort = localPort;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(String remotePort) {
        this.remotePort = remotePort;
    }
}
