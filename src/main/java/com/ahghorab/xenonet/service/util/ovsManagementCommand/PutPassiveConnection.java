package com.ahghorab.xenonet.service.util.ovsManagementCommand;

import com.ahghorab.xenonet.service.util.httpReq.HttpRequestSender;
import com.ahghorab.xenonet.service.util.httpReq.HttpRequestTypeEnum;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class PutPassiveConnection {
    private String nodeId;
    private String remotePort;
    private String remoteIp;

    public PutPassiveConnection(String nodeId, String remotePort, String remoteIp) {
        this.nodeId = nodeId;
        this.remotePort = remotePort;
        this.remoteIp = remoteIp;
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

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(String remotePort) {
        this.remotePort = remotePort;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    //    {
//        "network-topology:node": [
//        {
//            "node-id": "ovsdb://HOST1",
//            "connection-info": {
//            "ovsdb:remote-port": "6640",
//            "ovsdb:remote-ip": "192.168.1.7"
//            }
//        }
//      ]
//    }
    public JSONObject getBody() {
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonNetworkTopologyArr = new JSONArray();
        JSONObject ConnectionInfoObj = new JSONObject();

        JSONObject subConnectionInfoObj = new JSONObject();
        subConnectionInfoObj.put("ovsdb:remote-port", remotePort);
        subConnectionInfoObj.put("ovsdb:remote-ip", remoteIp);

        ConnectionInfoObj.put("node-id", nodeId);
        ConnectionInfoObj.put("connection-info", subConnectionInfoObj);

        jsonNetworkTopologyArr.add(ConnectionInfoObj);

        jsonObj.put("network-topology:node", jsonNetworkTopologyArr);
        return jsonObj;
    }

    public String getUri() {
        return "http://localhost:8181/restconf/config/network-topology:network-topology/topology/ovsdb:1/node/" + nodeId.split("//")[0] + "%2F%2F" + nodeId.split("//")[1];
    }
}
