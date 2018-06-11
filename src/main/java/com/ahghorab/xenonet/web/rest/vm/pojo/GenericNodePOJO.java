package com.ahghorab.xenonet.web.rest.vm.pojo;

import com.ahghorab.xenonet.web.rest.vm.pojo.bridge.BridgeExternalIdsPOJO;
import com.ahghorab.xenonet.web.rest.vm.pojo.bridge.ControllerEntryPOJO;
import com.ahghorab.xenonet.web.rest.vm.pojo.bridge.ProtocolEntryPOJO;
import com.ahghorab.xenonet.web.rest.vm.pojo.node.ConnectioInfoPOJO;
import com.ahghorab.xenonet.web.rest.vm.pojo.node.ManagedNodeEntryPOJO;
import com.ahghorab.xenonet.web.rest.vm.pojo.node.ManagerEntryPOJO;
import com.ahghorab.xenonet.web.rest.vm.pojo.node.OpenVswitchExternalIdsPOJO;
import com.ahghorab.xenonet.web.rest.vm.pojo.terminationPort.TerminationPortPOJO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GenericNodePOJO {
    //Common Part
    @JsonProperty("node-id")
    private String nodeId;

    //Bridge Part
    @JsonProperty("ovsdb:bridge-uuid")
    private String bridgeUuid;

    @JsonProperty("ovsdb:stp_enable")
    private String stpEnable;

    @JsonProperty("ovsdb:bridge-name")
    private String bridgeName;

    @JsonProperty("ovsdb:bridge-openflow-node-ref")
    private String bridgeOpenflowNodeRef;

    @JsonProperty("ovsdb:protocol-entry")
    private List<ProtocolEntryPOJO> protocolEntry;

    @JsonProperty("ovsdb:controller-entry")
    private List<ControllerEntryPOJO> controllerEntry;

    @JsonProperty("ovsdb:datapath-id")
    private String datapathId;

    @JsonProperty("ovsdb:datapath-type")
    private String datapathType;

    @JsonProperty("ovsdb:fail-mode")
    private String failMode;

    @JsonProperty("ovsdb:flow-node")
    private String flowNode;

    @JsonProperty("ovsdb:managed-by")
    private String managedBy;

    @JsonProperty("ovsdb:bridge-external-ids")
    private List<BridgeExternalIdsPOJO> bridgeExternalIds;

    @JsonProperty("ovsdb:bridge-other-configs")
    private String bridgeOtherConfigs;

    @JsonProperty("termination-point")
    private List<TerminationPortPOJO> terminationPoint;


    // node Part
    @JsonProperty("ovsdb:connection-info")
    private ConnectioInfoPOJO connectionInfo;

    @JsonProperty("ovsdb:db-version")
    private String dbVersion;

    @JsonProperty("ovsdb:ovs-version")
    private String ovsVersion;

    @JsonProperty("ovsdb:managed-node-entry")
    private List<ManagedNodeEntryPOJO> managedNodeEntry;

    //    @JsonProperty("ovsdb:datapath-type-entry")
    @JsonIgnore
    private List<String> datapathTypeEntry;

    //    @JsonProperty("ovsdb:interface-type-entry")
    @JsonIgnore
    private List<String> interfaceTypeEntry;

    @JsonProperty("ovsdb:openvswitch-external-ids")
    private List<OpenVswitchExternalIdsPOJO> openvswitchExternalIds;

    //    @JsonProperty("ovsdb:openvswitch-other-config")
    @JsonIgnore
    private List<String> openvswitchOtherConfig;

    @JsonProperty("ovsdb:manager-entry")
    private List<ManagerEntryPOJO> manageryEntry;

    //    @JsonProperty("ovsdb:qos-entries")
    @JsonIgnore
    private List<String> qosEntries;

    //    @JsonProperty("ovsdb:queues")
    @JsonIgnore
    private List<String> queues;

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

    public ConnectioInfoPOJO getConnectionInfo() {
        return connectionInfo;
    }

    public void setConnectionInfo(ConnectioInfoPOJO connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    public String getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(String dbVersion) {
        this.dbVersion = dbVersion;
    }

    public String getOvsVersion() {
        return ovsVersion;
    }

    public void setOvsVersion(String ovsVersion) {
        this.ovsVersion = ovsVersion;
    }

    public List<ManagedNodeEntryPOJO> getManagedNodeEntry() {
        return managedNodeEntry;
    }

    public void setManagedNodeEntry(List<ManagedNodeEntryPOJO> managedNodeEntry) {
        this.managedNodeEntry = managedNodeEntry;
    }

    public List<String> getDatapathTypeEntry() {
        return datapathTypeEntry;
    }

    public void setDatapathTypeEntry(List<String> datapathTypeEntry) {
        this.datapathTypeEntry = datapathTypeEntry;
    }

    public List<String> getInterfaceTypeEntry() {
        return interfaceTypeEntry;
    }

    public void setInterfaceTypeEntry(List<String> interfaceTypeEntry) {
        this.interfaceTypeEntry = interfaceTypeEntry;
    }

    public List<OpenVswitchExternalIdsPOJO> getOpenvswitchExternalIds() {
        return openvswitchExternalIds;
    }

    public void setOpenvswitchExternalIds(List<OpenVswitchExternalIdsPOJO> openvswitchExternalIds) {
        this.openvswitchExternalIds = openvswitchExternalIds;
    }

    public List<String> getOpenvswitchOtherConfig() {
        return openvswitchOtherConfig;
    }

    public void setOpenvswitchOtherConfig(List<String> openvswitchOtherConfig) {
        this.openvswitchOtherConfig = openvswitchOtherConfig;
    }

    public List<ManagerEntryPOJO> getManageryEntry() {
        return manageryEntry;
    }

    public void setManageryEntry(List<ManagerEntryPOJO> manageryEntry) {
        this.manageryEntry = manageryEntry;
    }

    public List<String> getQosEntries() {
        return qosEntries;
    }

    public void setQosEntries(List<String> qosEntries) {
        this.qosEntries = qosEntries;
    }

    public List<String> getQueues() {
        return queues;
    }

    public void setQueues(List<String> queues) {
        this.queues = queues;
    }
}
