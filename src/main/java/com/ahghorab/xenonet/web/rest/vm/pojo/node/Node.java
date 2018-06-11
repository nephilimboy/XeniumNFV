package com.ahghorab.xenonet.web.rest.vm.pojo.node;

import java.util.List;

public class Node {
    private String nodeId;
    private ConnectioInfoPOJO connectionInfo;

    private String dbVersion;

    private String ovsVersion;

    private List<ManagedNodeEntryPOJO> managedNodeEntry;

    private List<String> datapathTypeEntry;

    private List<String> interfaceTypeEntry;

    private List<OpenVswitchExternalIdsPOJO> openvswitchExternalIds;

    private List<String> openvswitchOtherConfig;

    private List<ManagerEntryPOJO> manageryEntry;

    private List<String> qosEntries;

    private List<String> queues;

    public Node(String nodeId, ConnectioInfoPOJO connectionInfo, String dbVersion, String ovsVersion, List<ManagedNodeEntryPOJO> managedNodeEntry, List<String> datapathTypeEntry, List<String> interfaceTypeEntry, List<OpenVswitchExternalIdsPOJO> openvswitchExternalIds, List<String> openvswitchOtherConfig, List<ManagerEntryPOJO> manageryEntry, List<String> qosEntries, List<String> queues) {
        this.nodeId = nodeId;
        this.connectionInfo = connectionInfo;
        this.dbVersion = dbVersion;
        this.ovsVersion = ovsVersion;
        this.managedNodeEntry = managedNodeEntry;
        this.datapathTypeEntry = datapathTypeEntry;
        this.interfaceTypeEntry = interfaceTypeEntry;
        this.openvswitchExternalIds = openvswitchExternalIds;
        this.openvswitchOtherConfig = openvswitchOtherConfig;
        this.manageryEntry = manageryEntry;
        this.qosEntries = qosEntries;
        this.queues = queues;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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
