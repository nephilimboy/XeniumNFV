package com.ahghorab.xenonet.web.rest.vm.pojo.terminationPort;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TerminationPortPOJO {

    @JsonProperty("ovsdb:port-uuid")
    private String portUuid;

    @JsonProperty("ovsdb:interface-uuid")
    private String interfaceUuid;

    @JsonProperty("ovsdb:name")
    private String name;

    @JsonProperty("ovsdb:ingress-policing-rate")
    private String ingressPolicingRate;

    @JsonProperty("ovsdb:ingress-policing-burst")
    private String ingressPolicingBurst;

    @JsonProperty("ovsdb:ifindex")
    private String ifindex;

    @JsonProperty("ovsdb:interface-type")
    private String interfaceType;

    @JsonProperty("ovsdb:options")
    private List<TerminationPortOptionPOJO> options;

    @JsonProperty("ovsdb:ofport")
    private String ofport;

    @JsonProperty("ovsdb:ofport_request")
    private String ofportRequest;

    @JsonProperty("ovsdb:vlan-tag")
    private String vlanTag;

    @JsonProperty("ovsdb:trunks")
    private List<String> trunks;

    @JsonProperty("ovsdb:vlan-mode")
    private String vlanMode;

    @JsonProperty("ovsdb:interface-external-ids")
    private List<TerminationPortInterfaceExternalIdsPOJO> interfaceExternalIds;

    @JsonProperty("ovsdb:port-other-configs")
    private List<String> portOtherConfigs;

    @JsonProperty("ovsdb:interface-other-configs")
    private List<String> interfaceOtherConfigs;

    @JsonProperty("ovsdb:inteface-lldp")
    private List<String> intefaceLldp;

    @JsonProperty("ovsdb:qos")
    private String qos;

    @JsonProperty("tp-id")
    private String tpId;

    @JsonProperty("ovsdb:port-external-ids")
    private List<TerminationPortExternalIdsPOJO> terminationPortExternalIdsPOJO;

    public String getPortUuid() {
        return portUuid;
    }

    public void setPortUuid(String portUuid) {
        this.portUuid = portUuid;
    }

    public String getInterfaceUuid() {
        return interfaceUuid;
    }

    public void setInterfaceUuid(String interfaceUuid) {
        this.interfaceUuid = interfaceUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngressPolicingRate() {
        return ingressPolicingRate;
    }

    public void setIngressPolicingRate(String ingressPolicingRate) {
        this.ingressPolicingRate = ingressPolicingRate;
    }

    public String getIngressPolicingBurst() {
        return ingressPolicingBurst;
    }

    public void setIngressPolicingBurst(String ingressPolicingBurst) {
        this.ingressPolicingBurst = ingressPolicingBurst;
    }

    public String getIfindex() {
        return ifindex;
    }

    public void setIfindex(String ifindex) {
        this.ifindex = ifindex;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public List<TerminationPortOptionPOJO> getOptions() {
        return options;
    }

    public void setOptions(List<TerminationPortOptionPOJO> options) {
        this.options = options;
    }

    public String getOfport() {
        return ofport;
    }

    public void setOfport(String ofport) {
        this.ofport = ofport;
    }

    public String getOfportRequest() {
        return ofportRequest;
    }

    public void setOfportRequest(String ofportRequest) {
        this.ofportRequest = ofportRequest;
    }

    public String getVlanTag() {
        return vlanTag;
    }

    public void setVlanTag(String vlanTag) {
        this.vlanTag = vlanTag;
    }

    public List<String> getTrunks() {
        return trunks;
    }

    public void setTrunks(List<String> trunks) {
        this.trunks = trunks;
    }

    public String getVlanMode() {
        return vlanMode;
    }

    public void setVlanMode(String vlanMode) {
        this.vlanMode = vlanMode;
    }

    public List<TerminationPortInterfaceExternalIdsPOJO> getInterfaceExternalIds() {
        return interfaceExternalIds;
    }

    public void setInterfaceExternalIds(List<TerminationPortInterfaceExternalIdsPOJO> interfaceExternalIds) {
        this.interfaceExternalIds = interfaceExternalIds;
    }

    public List<String> getPortOtherConfigs() {
        return portOtherConfigs;
    }

    public void setPortOtherConfigs(List<String> portOtherConfigs) {
        this.portOtherConfigs = portOtherConfigs;
    }

    public List<String> getInterfaceOtherConfigs() {
        return interfaceOtherConfigs;
    }

    public void setInterfaceOtherConfigs(List<String> interfaceOtherConfigs) {
        this.interfaceOtherConfigs = interfaceOtherConfigs;
    }

    public List<String> getIntefaceLldp() {
        return intefaceLldp;
    }

    public void setIntefaceLldp(List<String> intefaceLldp) {
        this.intefaceLldp = intefaceLldp;
    }

    public String getQos() {
        return qos;
    }

    public void setQos(String qos) {
        this.qos = qos;
    }

    public String getTpId() {
        return tpId;
    }

    public void setTpId(String tpId) {
        this.tpId = tpId;
    }

    public List<TerminationPortExternalIdsPOJO> getTerminationPortExternalIdsPOJO() {
        return terminationPortExternalIdsPOJO;
    }

    public void setTerminationPortExternalIdsPOJO(List<TerminationPortExternalIdsPOJO> terminationPortExternalIdsPOJO) {
        this.terminationPortExternalIdsPOJO = terminationPortExternalIdsPOJO;
    }
}
