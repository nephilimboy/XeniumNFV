package com.ahghorab.xenonet.web.rest.vm.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NetworkTopologyPOJO {

    @JsonProperty("topology")
    private List<TopologyPOJO> topology;

    public List<TopologyPOJO> getTopology() {
        return topology;
    }

    public void setTopology(List<TopologyPOJO> topology) {
        this.topology = topology;
    }
}
