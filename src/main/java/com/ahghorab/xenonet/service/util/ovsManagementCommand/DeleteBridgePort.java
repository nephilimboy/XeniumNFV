package com.ahghorab.xenonet.service.util.ovsManagementCommand;

import com.ahghorab.xenonet.service.util.httpReq.HttpRequestSender;
import com.ahghorab.xenonet.service.util.httpReq.HttpRequestTypeEnum;
import org.json.simple.JSONObject;

public class DeleteBridgePort {
    private String nodeId;
    private String bridgeName;
    private String portName;

    public DeleteBridgePort(String nodeId, String bridgeName, String portName) {
        this.nodeId = nodeId;
        this.bridgeName = bridgeName;
        this.portName = portName;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getBridgeName() {
        return bridgeName;
    }

    public void setBridgeName(String bridgeName) {
        this.bridgeName = bridgeName;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public Integer execute() {
        HttpRequestSender reqSender;
        try {
            reqSender = new HttpRequestSender(
                    this.getUri(),
                    null,
                    HttpRequestTypeEnum.DELETE
            );
            try {
                reqSender.execute();
                return reqSender.getResStatus();

            } catch (Exception e) {
                System.out.println("Bad Request");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public JSONObject getBody() {
        return null;
    }

    public String getUri() {
        return "http://localhost:8181/restconf/config/network-topology:network-topology/topology/ovsdb:1/node/"
                + this.nodeId.split("//")[0] + "%2F%2F" + this.nodeId.split("//")[1]
                + "%2Fbridge%2F" + this.bridgeName
                + "/termination-point/" + portName ;
    }
}
