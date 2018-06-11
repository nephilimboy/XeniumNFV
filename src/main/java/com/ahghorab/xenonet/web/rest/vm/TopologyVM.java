package com.ahghorab.xenonet.web.rest.vm;

import java.util.List;

public class TopologyVM extends BaseEntityVM<Integer> {
    private List<ControllerVM> controllers;
    private List<SwitchVM> switches;
    private List<VnfVM> vnfs;
    private List<DiagramEntityConnectionsVM> diagramEntityConnections;

    public TopologyVM(){

    }

    public TopologyVM(List<ControllerVM> controllers, List<SwitchVM> switches, List<VnfVM> vnfs, List<DiagramEntityConnectionsVM> diagramEntityConnections) {
        this.controllers = controllers;
        this.switches = switches;
        this.vnfs = vnfs;
        this.diagramEntityConnections = diagramEntityConnections;
    }

    public TopologyVM(Integer id, String createdDate, String updatedDate, List<ControllerVM> controllers, List<SwitchVM> switches, List<VnfVM> vnfs, List<DiagramEntityConnectionsVM> diagramEntityConnections) {
        super(id, createdDate, updatedDate);
        this.controllers = controllers;
        this.switches = switches;
        this.vnfs = vnfs;
        this.diagramEntityConnections = diagramEntityConnections;
    }

    public List<ControllerVM> getControllers() {
        return controllers;
    }

    public void setControllers(List<ControllerVM> controllers) {
        this.controllers = controllers;
    }

    public List<SwitchVM> getSwitches() {
        return switches;
    }

    public void setSwitches(List<SwitchVM> switches) {
        this.switches = switches;
    }

    public List<VnfVM> getVnfs() {
        return vnfs;
    }

    public void setVnfs(List<VnfVM> vnfs) {
        this.vnfs = vnfs;
    }

    public List<DiagramEntityConnectionsVM> getDiagramEntityConnections() {
        return diagramEntityConnections;
    }

    public void setDiagramEntityConnections(List<DiagramEntityConnectionsVM> diagramEntityConnections) {
        this.diagramEntityConnections = diagramEntityConnections;
    }
}
