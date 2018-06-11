package com.ahghorab.xenonet.web.rest.vm.pojo;


import com.ahghorab.xenonet.web.rest.vm.pojo.bridge.Bridge;
import com.ahghorab.xenonet.web.rest.vm.pojo.node.Node;

import java.util.ArrayList;
import java.util.List;

public final class PojoUtils {
    public static Node getManageNode(TopologyPOJO topologyPOJO) {
        for (GenericNodePOJO genericNodePOJO : topologyPOJO.getNode()) {
            if (genericNodePOJO.getBridgeName() == null) {
                return new Node(
                        genericNodePOJO.getNodeId(),
                        genericNodePOJO.getConnectionInfo(),
                        genericNodePOJO.getDbVersion(),
                        genericNodePOJO.getOvsVersion(),
                        genericNodePOJO.getManagedNodeEntry(),
                        genericNodePOJO.getDatapathTypeEntry(),
                        genericNodePOJO.getInterfaceTypeEntry(),
                        genericNodePOJO.getOpenvswitchExternalIds(),
                        genericNodePOJO.getOpenvswitchOtherConfig(),
                        genericNodePOJO.getManageryEntry(),
                        genericNodePOJO.getQosEntries(),
                        genericNodePOJO.getQueues()
                );
            }
        }
        return null;
    }

    public static List<Bridge> gerAllBridge(TopologyPOJO topologyPOJO){
        List bridgesList = new ArrayList<Bridge>();
        for (GenericNodePOJO genericNodePOJO : topologyPOJO.getNode()){
            if (genericNodePOJO.getBridgeName() != null) {
                bridgesList.add(
                        new Bridge(
                                genericNodePOJO.getNodeId(),
                                genericNodePOJO.getBridgeUuid(),
                                genericNodePOJO.getStpEnable(),
                                genericNodePOJO.getBridgeName(),
                                genericNodePOJO.getBridgeOpenflowNodeRef(),
                                genericNodePOJO.getProtocolEntry(),
                                genericNodePOJO.getControllerEntry(),
                                genericNodePOJO.getDatapathId(),
                                genericNodePOJO.getDatapathType(),
                                genericNodePOJO.getFailMode(),
                                genericNodePOJO.getFlowNode(),
                                genericNodePOJO.getManagedBy(),
                                genericNodePOJO.getBridgeExternalIds(),
                                genericNodePOJO.getBridgeOtherConfigs(),
                                genericNodePOJO.getTerminationPoint()
                        )
                );
            }
        }
        return bridgesList;
    }
}
