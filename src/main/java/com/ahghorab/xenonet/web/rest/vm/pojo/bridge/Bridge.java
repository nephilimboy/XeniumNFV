package com.ahghorab.xenonet.web.rest.vm.pojo.bridge;


import com.ahghorab.xenonet.web.rest.vm.pojo.terminationPort.TerminationPortPOJO;

import java.util.List;

public class Bridge {

    private String nodeId;

    private String bridgeUuid;

    private String stpEnable;

    private String bridgeName;

    private String bridgeOpenflowNodeRef;

    private List<ProtocolEntryPOJO> protocolEntry;

    private List<ControllerEntryPOJO> controllerEntry;

    private String datapathId;

    private String datapathType;

    private String failMode;

    private String flowNode;

    private String managedBy;

    private List<BridgeExternalIdsPOJO> bridgeExternalIds;

    private String bridgeOtherConfigs;

    private List<TerminationPortPOJO> terminationPoint;

    public Bridge(String nodeId, String bridgeUuid, String stpEnable, String bridgeName, String bridgeOpenflowNodeRef, List<ProtocolEntryPOJO> protocolEntry, List<ControllerEntryPOJO> controllerEntry, String datapathId, String datapathType, String failMode, String flowNode, String managedBy, List<BridgeExternalIdsPOJO> bridgeExternalIds, String bridgeOtherConfigs, List<TerminationPortPOJO> terminationPoint) {
        this.nodeId = nodeId;
        this.bridgeUuid = bridgeUuid;
        this.stpEnable = stpEnable;
        this.bridgeName = bridgeName;
        this.bridgeOpenflowNodeRef = bridgeOpenflowNodeRef;
        this.protocolEntry = protocolEntry;
        this.controllerEntry = controllerEntry;
        this.datapathId = datapathId;
        this.datapathType = datapathType;
        this.failMode = failMode;
        this.flowNode = flowNode;
        this.managedBy = managedBy;
        this.bridgeExternalIds = bridgeExternalIds;
        this.bridgeOtherConfigs = bridgeOtherConfigs;
        this.terminationPoint = terminationPoint;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getBridgeUuid() {
        return bridgeUuid;
    }

    public void setBridgeUuid(String bridgeUuid) {
        this.bridgeUuid = bridgeUuid;
    }

    public String getStpEnable() {
        return stpEnable;
    }

    public void setStpEnable(String stpEnable) {
        this.stpEnable = stpEnable;
    }

    public String getBridgeName() {
        return bridgeName;
    }

    public void setBridgeName(String bridgeName) {
        this.bridgeName = bridgeName;
    }

    public String getBridgeOpenflowNodeRef() {
        return bridgeOpenflowNodeRef;
    }

    public void setBridgeOpenflowNodeRef(String bridgeOpenflowNodeRef) {
        this.bridgeOpenflowNodeRef = bridgeOpenflowNodeRef;
    }

    public List<ProtocolEntryPOJO> getProtocolEntry() {
        return protocolEntry;
    }

    public void setProtocolEntry(List<ProtocolEntryPOJO> protocolEntry) {
        this.protocolEntry = protocolEntry;
    }

    public List<ControllerEntryPOJO> getControllerEntry() {
        return controllerEntry;
    }

    public void setControllerEntry(List<ControllerEntryPOJO> controllerEntry) {
        this.controllerEntry = controllerEntry;
    }

    public String getDatapathId() {
        return datapathId;
    }

    public void setDatapathId(String datapathId) {
        this.datapathId = datapathId;
    }

    public String getDatapathType() {
        return datapathType;
    }

    public void setDatapathType(String datapathType) {
        this.datapathType = datapathType;
    }

    public String getFailMode() {
        return failMode;
    }

    public void setFailMode(String failMode) {
        this.failMode = failMode;
    }

    public String getFlowNode() {
        return flowNode;
    }

    public void setFlowNode(String flowNode) {
        this.flowNode = flowNode;
    }

    public String getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(String managedBy) {
        this.managedBy = managedBy;
    }

    public List<BridgeExternalIdsPOJO> getBridgeExternalIds() {
        return bridgeExternalIds;
    }

    public void setBridgeExternalIds(List<BridgeExternalIdsPOJO> bridgeExternalIds) {
        this.bridgeExternalIds = bridgeExternalIds;
    }

    public String getBridgeOtherConfigs() {
        return bridgeOtherConfigs;
    }

    public void setBridgeOtherConfigs(String bridgeOtherConfigs) {
        this.bridgeOtherConfigs = bridgeOtherConfigs;
    }

    public List<TerminationPortPOJO> getTerminationPoint() {
        return terminationPoint;
    }

    public void setTerminationPoint(List<TerminationPortPOJO> terminationPoint) {
        this.terminationPoint = terminationPoint;
    }
}
