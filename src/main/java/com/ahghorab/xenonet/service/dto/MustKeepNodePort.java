package com.ahghorab.xenonet.service.dto;

import com.ahghorab.xenonet.web.rest.vm.DiagramEntityConnectionsVM;

import java.util.List;

public class MustKeepNodePort<T> {
    private T node;
    private List<DiagramEntityConnectionsVM> mustDeletePort;
    private List<DiagramEntityConnectionsVM> mustKeepPort;
    private List<DiagramEntityConnectionsVM> mustCreatePort;

    public MustKeepNodePort(){

    }
    public MustKeepNodePort(T node, List<DiagramEntityConnectionsVM> mustDeletePort, List<DiagramEntityConnectionsVM> mustKeepPort, List<DiagramEntityConnectionsVM> mustCreatePort) {
        this.node = node;
        this.mustDeletePort = mustDeletePort;
        this.mustKeepPort = mustKeepPort;
        this.mustCreatePort = mustCreatePort;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public List<DiagramEntityConnectionsVM> getMustDeletePort() {
        return mustDeletePort;
    }

    public void setMustDeletePort(List<DiagramEntityConnectionsVM> mustDeletePort) {
        this.mustDeletePort = mustDeletePort;
    }

    public List<DiagramEntityConnectionsVM> getMustKeepPort() {
        return mustKeepPort;
    }

    public void setMustKeepPort(List<DiagramEntityConnectionsVM> mustKeepPort) {
        this.mustKeepPort = mustKeepPort;
    }

    public List<DiagramEntityConnectionsVM> getMustCreatePort() {
        return mustCreatePort;
    }

    public void setMustCreatePort(List<DiagramEntityConnectionsVM> mustCreatePort) {
        this.mustCreatePort = mustCreatePort;
    }
}
