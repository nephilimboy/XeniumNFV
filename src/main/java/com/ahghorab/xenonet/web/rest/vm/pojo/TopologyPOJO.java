package com.ahghorab.xenonet.web.rest.vm.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TopologyPOJO {
    @JsonProperty("topology-id")
    private String topologyId;

    @JsonProperty("node")
    private List<GenericNodePOJO> Node;

    public String getTopologyId() {
        return topologyId;
    }

    public void setTopologyId(String topologyId) {
        this.topologyId = topologyId;
    }

    public List<GenericNodePOJO> getNode() {
        return Node;
    }

    public void setNode(List<GenericNodePOJO> node) {
        Node = node;
    }
}
