package com.ahghorab.xenonet.service.util.ovsManagementCommand;

import com.ahghorab.xenonet.service.util.httpReq.HttpRequestSender;
import com.ahghorab.xenonet.service.util.httpReq.HttpRequestTypeEnum;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PutCreateBridge {
    private String nodeId;
    private String bridgeId;
    private String bridgeName;
    private String controllerIp;
    private String controllerPort;
    private String protocolVersion;

    public PutCreateBridge(String nodeId, String bridgeId, String bridgeName, String controllerIp, String controllerPort, String protocolVersion) {
        this.nodeId = nodeId;
        this.bridgeId = bridgeId;
        this.bridgeName = bridgeName;
        this.controllerIp = controllerIp;
        this.controllerPort = controllerPort;
        this.protocolVersion = protocolVersion;
    }

    public Integer execute() {
        HttpRequestSender reqSender;
        try {
            reqSender = new HttpRequestSender(
                this.getUri(),
                new StringEntity(this.getBody().toJSONString(), ContentType.APPLICATION_JSON),
                HttpRequestTypeEnum.PUT
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

    public String getBridgeId() {
        return bridgeId;
    }

    public void setBridgeId(String bridgeId) {
        this.bridgeId = bridgeId;
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

    public String getControllerIp() {
        return controllerIp;
    }

    public void setControllerIp(String controllerIp) {
        this.controllerIp = controllerIp;
    }

    public String getControllerPort() {
        return controllerPort;
    }

    public void setControllerPort(String controllerPort) {
        this.controllerPort = controllerPort;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /*
        {
      "network-topology:node": [
            {
                 "node-id": "ovsdb://HOST1/bridge/brtest",
                 "ovsdb:bridge-name": "brtest",
                  "ovsdb:controller-entry": [
                    {
                      "target": "tcp:<controller-ip>:6653"
                    }
                  ],
                ”ovsdb:protocol-entry": [
                    {
                     "protocol": "ovsdb:ovsdb-bridge-protocol-openflow-13"
                    }
                  ],
                 "ovsdb:managed-by": "/network-topology:network-topology/network-topology:topology[network-topology:topology-id='ovsdb:1']/network-topology:node[network-topology:node-id='ovsdb://HOST1']"
            }
        ]
      }
         */
    public JSONObject getBody() {
        JSONObject jsonObj = new JSONObject();
        JSONObject bridge = new JSONObject();

        bridge.put("node-id", nodeId + "/bridge/" + bridgeId);
        bridge.put("ovsdb:bridge-name", bridgeName);

        //Controller Part
        if (controllerIp != null) {
            JSONArray controllerArr = new JSONArray();
            JSONObject controllerObj = new JSONObject();
            controllerObj.put("target", "tcp:" + this.controllerIp + ":" + this.controllerPort);
            controllerArr.add(controllerObj);

            bridge.put("ovsdb:controller-entry", controllerArr);
        }

        //Protocol Part
        if (protocolVersion != null) {
            JSONArray protocolArr = new JSONArray();
            JSONObject protocolObj = new JSONObject();
            protocolObj.put("protocol", this.protocolVersion);
            protocolArr.add(protocolObj);

            bridge.put("ovsdb:protocol-entry", protocolArr);
        }
        bridge.put("ovsdb:managed-by", "/network-topology:network-topology/network-topology:topology[network-topology:topology-id='ovsdb:1']/network-topology:node[network-topology:node-id='" + nodeId + "']");

        jsonObj.put("network-topology:node", bridge);
        return jsonObj;
    }

    public String getUri() {
        return "http://localhost:8181/restconf/config/network-topology:network-topology/topology/ovsdb:1/node/" + this.nodeId.split("//")[0] + "%2F%2F" + this.nodeId.split("//")[1] + "%2Fbridge%2F" + this.bridgeName;
    }
}
