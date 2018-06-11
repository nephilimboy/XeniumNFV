package com.ahghorab.xenonet.service.util.ovsManagementCommand;


import com.ahghorab.xenonet.service.util.httpReq.HttpRequestSender;
import com.ahghorab.xenonet.service.util.httpReq.HttpRequestTypeEnum;
import com.ahghorab.xenonet.web.rest.vm.pojo.PojoUtils;
import com.ahghorab.xenonet.web.rest.vm.pojo.bridge.Bridge;
import com.ahghorab.xenonet.web.rest.vm.pojo.terminationPort.TerminationPortPOJO;

public class GetNetworkTopology {
    private String NodeId;

    public GetNetworkTopology(String nodeId) {
        NodeId = nodeId;
    }

    public Integer execute() {
        HttpRequestSender reqSender = new HttpRequestSender(
                "http://localhost:8181/restconf/operational/network-topology:network-topology/topology/" + this.NodeId + "/",
                null,
                HttpRequestTypeEnum.GET
        );
        try {
            reqSender.execute();
            for (Bridge br : PojoUtils.gerAllBridge(reqSender.getMappedResBody().getTopology().get(0))) {
                System.out.println(br.getBridgeName());
                for (TerminationPortPOJO trPort: br.getTerminationPoint()){
                    System.out.println(trPort.getName());
                }
            }
            System.out.println(PojoUtils.getManageNode(reqSender.getMappedResBody().getTopology().get(0)).getNodeId());
            return reqSender.getResStatus();
        } catch (Exception e) {
            System.out.println("Bad Request");
        }
        return 0;
    }


    public String getNodeId() {
        return NodeId;
    }

    public void setNodeId(String nodeId) {
        NodeId = nodeId;
    }
}
