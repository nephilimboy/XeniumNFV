package com.ahghorab.xenonet.service.util.ovsManagementCommand;

import com.ahghorab.xenonet.service.util.httpReq.HttpRequestSender;
import com.ahghorab.xenonet.service.util.httpReq.HttpRequestTypeEnum;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PutCreateBridgePort {
    private String nodeId;
    private String bridgeName;
    private String portName;
    private String portOptionKey;
    private String portOptionValue;
    private String interfaceType;

    public PutCreateBridgePort(String nodeId, String bridgeName, String portName, String portOptionKey, String portOptionValue, String interfaceType) {
        this.nodeId = nodeId;
        this.bridgeName = bridgeName;
        this.portName = portName;
        this.portOptionKey = portOptionKey;
        this.portOptionValue = portOptionValue;
        this.interfaceType = interfaceType;
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

    public String getPortOptionKey() {
        return portOptionKey;
    }

    public void setPortOptionKey(String portOptionKey) {
        this.portOptionKey = portOptionKey;
    }

    public String getPortOptionValue() {
        return portOptionValue;
    }

    public void setPortOptionValue(String portOptionValue) {
        this.portOptionValue = portOptionValue;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
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

    /*
     {
  "network-topology:termination-point": [
    {
      "ovsdb:options": [
        {
          "ovsdb:option": "peer",
          "ovsdb:value" : "patch0"
        }
      ],
      "ovsdb:name": "patch1",
      "tp-id": "patch1"
    }
  ]
}
         */
    public JSONObject getBody() {
        JSONObject jsonObj = new JSONObject();
        JSONObject port = new JSONObject();

        port.put("ovsdb:name", portName);
        port.put("tp-id", portName);
        port.put("ovsdb:interface-type", interfaceType);

        //Option Part
        if (portOptionKey != null) {
            JSONArray portOptions = new JSONArray();
            JSONObject optionObj = new JSONObject();
            optionObj.put("ovsdb:option", portOptionKey);
            optionObj.put("ovsdb:value", portOptionValue);
            portOptions.add(optionObj);

            port.put("ovsdb:options", portOptions);
        }
        jsonObj.put("network-topology:termination-point", port);
        return jsonObj;
    }

    public String getUri() {
        return "http://localhost:8181/restconf/config/network-topology:network-topology/topology/ovsdb:1/node/" + this.nodeId.split("//")[0] + "%2F%2F" + this.nodeId.split("//")[1] + "%2Fbridge%2F" + bridgeName + "/termination-point/" + portName + "/";
    }
}
