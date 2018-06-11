package com.ahghorab.xenonet.service;

import com.ahghorab.xenonet.service.dto.MustKeepTopology;
import com.ahghorab.xenonet.service.dto.TopologyTrinityStats;
import com.ahghorab.xenonet.service.util.TopologyDif;
import com.ahghorab.xenonet.service.util.ovsManagementCommand.DeleteBridge;
import com.ahghorab.xenonet.service.util.ovsManagementCommand.DeleteBridgePort;
import com.ahghorab.xenonet.service.util.ovsManagementCommand.PutCreateBridge;
import com.ahghorab.xenonet.service.util.ovsManagementCommand.PutCreateBridgePort;
import com.ahghorab.xenonet.service.util.ssh.ExecuteCommanOverSsh;
import com.ahghorab.xenonet.web.rest.vm.*;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NetworkDiagramService {
    private final Logger log = LoggerFactory.getLogger(NetworkDiagramService.class);
    private DockerClient dockerClient;
    private List<CreateContainerResponse> vnfContainers = new ArrayList<>();
    private TopologyVM currentTopology;

    public NetworkDiagramService() {
        DefaultDockerClientConfig config
            = DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withRegistryEmail("info@baeldung.com")
            .withRegistryPassword("nephilimboy")
            .withRegistryUsername("Avlavigne")
            .withDockerHost("tcp://192.168.1.7:2375").build();

        this.dockerClient = DockerClientBuilder.getInstance(config).build();

        //Init Current Topology at first time
        currentTopology = new TopologyVM();
        List<ControllerVM> controllers = new ArrayList<>();
        List<SwitchVM> switches = new ArrayList<>();
        List<VnfVM> vnfs = new ArrayList<>();
        List<DiagramEntityConnectionsVM> diagramEntityConnections = new ArrayList<>();
        currentTopology.setControllers(controllers);
        currentTopology.setSwitches(switches);
        currentTopology.setVnfs(vnfs);
        currentTopology.setDiagramEntityConnections(diagramEntityConnections);

    }

    public void parsTopology(TopologyVM topologyVM) {
        TopologyTrinityStats topologyTrinityStats = TopologyDif.getTopologyDif(this.currentTopology, topologyVM);
        deleteTopology(topologyTrinityStats.getMustDeleteTopo());
        createTopologyEntity(topologyTrinityStats.getMustCreateTopo());
        this.currentTopology = topologyVM;
    }

    public void changeTopology(MustKeepTopology mustKeepTopology) {
//        if (mustKeepTopology != null) {
//            // Switch Part
//            //**************************************************************************************************************************
//            for (MustKeepNodePort<SwitchVM> swt: mustKeepTopology.getSwitchDifs()) {
//                //Delete Ports
//                //*************************************************************
//                swt.getMustDeletePort().forEach(port -> {
//                    //Check if port exist or not and remove it from "existsPortKeys" list
//                    for (Iterator<String> iter = existsPortKeys.listIterator(); iter.hasNext(); ) {
//                        String portKey = iter.next();
//                        if (portKey.equals(port.getKey())) {
//                            iter.remove();
//                        }
//                    }
//                    //Find Port Type by Checking port key
//                    // Switch#XXXXXXXXX%SwitchXXXXXXXXX
//                    if (port.getKey().split("%")[0].split("#")[0].equals("Switch") &&
//                        port.getKey().split("%")[1].split("#")[0].equals("Switch")) {
//                        deleteBridgePort(
//                            "ovsdb://HOST1",
//                            port.getKey().split("%")[0].split("#")[1].split("-")[1],
//                            port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1] + "L"
//                        );
//                        deleteBridgePort(
//                            "ovsdb://HOST1",
//                            port.getKey().split("%")[1].split("#")[1].split("-")[1],
//                            port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1] + "R"
//                        );
//                    }
//                });
//                //Create Ports
//                //*************************************************************
//                swt.getMustCreatePort().forEach(port -> {
//
//                    //Check if port exist or not
//                    boolean isPortExist = false;
//                    for (String keyStr : existsPortKeys) {
//                        if (port.getKey() == keyStr) {
//                            isPortExist = true;
//                        }
//                    }
//                    if (!isPortExist) {
//                        //Port does'nt exist so add it in "existsPortKeys" array
//                        existsPortKeys.add(port.getKey());
//                        //Find Port Type by Checking port key
//                        // Switch#XXXXXXXXX%SwitchXXXXXXXXX
//                        if (port.getKey().split("%")[0].split("#")[0].equals("Switch") &&
//                            port.getKey().split("%")[1].split("#")[0].equals("Switch")) {
//                            createBridgePort(
//                                "ovsdb://HOST1",
//                                port.getKey().split("%")[0].split("#")[1].split("-")[1],
//                                port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1] + "L",
//                                "peer",
//                                port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1] + "R",
//                                "ovsdb:interface-type-patch"
//                            );
//                            createBridgePort(
//                                "ovsdb://HOST1",
//                                port.getKey().split("%")[1].split("#")[1].split("-")[1],
//                                port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1] + "R",
//                                "peer",
//                                port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1] + "L",
//                                "ovsdb:interface-type-patch"
//                            );
//                        }
//                    }
//                });
//            }
//            // VNF Part
//            //**************************************************************************************************************************
//            for (MustKeepNodePort<VnfVM> vnf : mustKeepTopology.getVnfDifs()) {
//                //Delete Ports
//                //*************************************************************
//                vnf.getMustDeletePort().forEach(port -> {
//                    if ((port.getKey().split("%")[0].split("#")[0].equals("Switch") && port.getKey().split("%")[1].split("#")[0].equals("VNF")) ||
//                        (port.getKey().split("%")[1].split("#")[0].equals("Switch") && port.getKey().split("%")[0].split("#")[0].equals("VNF"))) {
//                        for (MustKeepNodePort<SwitchVM> swt: mustKeepTopology.getSwitchDifs()) {
//                            swt.getMustDeletePort().forEach(swPort -> {
//                                if (swPort.getKey().equals(port.getKey())) {
//                                    deleteDockerVnfOvsPort(
//                                        swt.getNode().getDescribtion().split("#")[1],
//                                        vnf.getNode().getDescribtion().split("#")[1]);
//                                }
//                            });
//                        }
//                    }
//                });
//
//            }
//            // Controller Part
//            //**************************************************************************************************************************
//            for (MustKeepNodePort<ControllerVM> controller : mustKeepTopology.getControllerDifs()) {
//
//            }
//        }
////        if (mustKeepTopology != null) {
//
////        }
    }

    private TopologyVM createTopologyEntity(TopologyVM topology) {
        if (topology != null) {
            //Create Switches
            for (SwitchVM swt : topology.getSwitches()) {
                createBridge(
                    "ovsdb://HOST1",
                    swt.getDescribtion().split("#")[1],
                    swt.getDescribtion().split("#")[1],
                    "192.168.1.7",
                    "6633",
                    "ovsdb:ovsdb-bridge-protocol-openflow-13"
                );
            }
            //Create VNF
            for (VnfVM vnf : topology.getVnfs()) {
                createDockerVnf(
                    "ubuntu-ditg2:16.04",
                    vnf.getDescribtion().split("#")[1]
                );
            }
            //Create PORTS
            for (DiagramEntityConnectionsVM port : topology.getDiagramEntityConnections()) {

                //Create VNF <-> OVS port
                // VNF#XXXXXXXXX%Switch#XXXXXXXXX
                if ((port.getKey().split("%")[0].split("#")[0].equals("Switch") && port.getKey().split("%")[1].split("#")[0].equals("VNF")) ||
                    (port.getKey().split("%")[1].split("#")[0].equals("Switch") && port.getKey().split("%")[0].split("#")[0].equals("VNF"))) {
                    String vnfContainerName, switchName;
                    if (port.getKey().split("%")[0].split("#")[0].equals("Switch")) {
                        switchName = port.getKey().split("%")[0].split("#")[1].split("-")[1];
                        vnfContainerName = port.getKey().split("%")[1].split("#")[1].split("-")[1];
                    } else {
                        vnfContainerName = port.getKey().split("%")[0].split("#")[1].split("-")[1];
                        switchName = port.getKey().split("%")[1].split("#")[1].split("-")[1];
                    }
                    // Loop for finding VNF IP address
                    for (VnfVM vnf : topology.getVnfs()) {
                        if (vnf.getDescribtion().split("#")[1].equals(vnfContainerName)) {
                            createDockerVnfOvsPort(
                                switchName,
                                vnfContainerName,
                                vnf.getIpAddress());
                        }
                    }
                }

                //Create OVS <-> OVS Ports
                //Find Port Type by Checking port key
                // Switch#XXXXXXXXX%SwitchXXXXXXXXX
                else if (port.getKey().split("%")[0].split("#")[0].equals("Switch") &&
                    port.getKey().split("%")[1].split("#")[0].equals("Switch")) {
                    //Path Port Naming is like this:
                    /*
                    PortKey = Switch#011535b9-6895-44f5-97cf-7ecf521a7eb9%Switch#2834659e-a02f-416d-ad35-e8880c672127
                    Switch1.key = Switch#011535b9-6895-44f5-97cf-7ecf521a7eb9
                    Switch2.key = Switch#2834659e-a02f-416d-ad35-e8880c672127
                    so path0 should be = 6895_a02f
                    and path1 should be = a02f_6895
                    so with this kind of naming strategy we can determine switch names and path ports from port key
                    and no extra loop needed.
                    */
                    createBridgePort(
                        "ovsdb://HOST1",
                        port.getKey().split("%")[0].split("#")[1].split("-")[1],
                        port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1],
                        "peer",
                        port.getKey().split("%")[1].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[0].split("#")[1].split("-")[1],
                        "ovsdb:interface-type-patch"
                    );
                    createBridgePort(
                        "ovsdb://HOST1",
                        port.getKey().split("%")[1].split("#")[1].split("-")[1],
                        port.getKey().split("%")[1].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[0].split("#")[1].split("-")[1],
                        "peer",
                        port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1],
                        "ovsdb:interface-type-patch"
                    );
                }
                //Find Port Type by Checking port key
                // Switch#XXXXXXXXX%ControllerXXXXXXXXX
                else if ((port.getKey().split("%")[0].split("#")[0].equals("Switch") && port.getKey().split("%")[1].split("#")[0].equals("Controller")) ||
                    (port.getKey().split("%")[1].split("#")[0].equals("Switch") && port.getKey().split("%")[0].split("#")[0].equals("Controller"))) {

                    //TODO do something abt controller <-> switches
                }
            }

            //Create Controller
            for (ControllerVM controller : topology.getControllers()) {
                createOdlDocker("odl3", controller.getName());
            }
        }

        return topology;
    }

    public void deleteTopology(TopologyVM topology) {
        if (topology != null) {
            for (DiagramEntityConnectionsVM port : topology.getDiagramEntityConnections()) {
                // VNF#XXXXXXXXX%Switch#XXXXXXXXX
                if ((port.getKey().split("%")[0].split("#")[0].equals("Switch") && port.getKey().split("%")[1].split("#")[0].equals("VNF")) ||
                    (port.getKey().split("%")[1].split("#")[0].equals("Switch") && port.getKey().split("%")[0].split("#")[0].equals("VNF"))) {
                    String vnfContainerName, switchName;
                    if (port.getKey().split("%")[0].split("#")[0].equals("Switch")) {
                        switchName = port.getKey().split("%")[0].split("#")[1].split("-")[1];
                        vnfContainerName = port.getKey().split("%")[1].split("#")[1].split("-")[1];
                    } else {
                        vnfContainerName = port.getKey().split("%")[0].split("#")[1].split("-")[1];
                        switchName = port.getKey().split("%")[1].split("#")[1].split("-")[1];
                    }
                    deleteDockerVnfOvsPort(
                        switchName,
                        vnfContainerName);
                }
                //Find Port Type by Checking port key
                // Switch#XXXXXXXXX%SwitchXXXXXXXXX
                else if (port.getKey().split("%")[0].split("#")[0].equals("Switch") &&
                    port.getKey().split("%")[1].split("#")[0].equals("Switch")) {
                    //Path Port Naming is like this:
                    /*
                    PortKey = Switch#011535b9-6895-44f5-97cf-7ecf521a7eb9%Switch#2834659e-a02f-416d-ad35-e8880c672127
                    Switch1.key = Switch#011535b9-6895-44f5-97cf-7ecf521a7eb9
                    Switch2.key = Switch#2834659e-a02f-416d-ad35-e8880c672127
                    so path0 should be = 6895_a02f
                    and path1 should be = a02f_6895
                    so with this kind of naming strategy we can determine switch names and path ports from port key
                    and no extra loop needed.
                    deleteBridgePort("ovsdb://HOST1", 6895, 6895_a02f)
                    deleteBridgePort("ovsdb://HOST1", a02f, a02f_6895)
                     */
                    deleteBridgePort(
                        "ovsdb://HOST1",
                        port.getKey().split("%")[0].split("#")[1].split("-")[1],
                        port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1]
                    );
                    deleteBridgePort(
                        "ovsdb://HOST1",
                        port.getKey().split("%")[1].split("#")[1].split("-")[1],
                        port.getKey().split("%")[1].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[0].split("#")[1].split("-")[1]
                    );
                }
                //Find Port Type by Checking port key
                // Switch#XXXXXXXXX%ControllerXXXXXXXXX
                else if ((port.getKey().split("%")[0].split("#")[0].equals("Switch") && port.getKey().split("%")[1].split("#")[0].equals("Controller")) ||
                    (port.getKey().split("%")[1].split("#")[0].equals("Switch") && port.getKey().split("%")[0].split("#")[0].equals("Controller"))) {

                    //TODO do something abt controller <-> switches
                }
            }
        }


        //VNF PART
        List<String> mustDeleteContainerVnf = new ArrayList<>();
        for (VnfVM vnf : topology.getVnfs()) {
            mustDeleteContainerVnf.add(vnf.getDescribtion().split("#")[1]);
        }
        //Delete docker container(VNF)
        deleteDockerVnf(mustDeleteContainerVnf);

        //Bridge Part
        //Delete bridges
        for (SwitchVM swt : topology.getSwitches()) {
            deleteBridge(
                "ovsdb://HOST1",
                swt.getDescribtion().split("#")[1]
            );
        }

        // Controller Part
        //Delete Controller
        List<String> mustDeleteContainerController = new ArrayList<>();
        for (ControllerVM controller : topology.getControllers()) {
            mustDeleteContainerController.add(controller.getDescribtion().split("#")[1]);
        }
        //Delete Controller Docker container
        deleteDockerVnf(mustDeleteContainerController);

    }
    public void deleteTopology() {
        TopologyVM topology = currentTopology;
        if (topology != null) {
            for (DiagramEntityConnectionsVM port : topology.getDiagramEntityConnections()) {
                // VNF#XXXXXXXXX%Switch#XXXXXXXXX
                if ((port.getKey().split("%")[0].split("#")[0].equals("Switch") && port.getKey().split("%")[1].split("#")[0].equals("VNF")) ||
                    (port.getKey().split("%")[1].split("#")[0].equals("Switch") && port.getKey().split("%")[0].split("#")[0].equals("VNF"))) {
                    String vnfContainerName, switchName;
                    if (port.getKey().split("%")[0].split("#")[0].equals("Switch")) {
                        switchName = port.getKey().split("%")[0].split("#")[1].split("-")[1];
                        vnfContainerName = port.getKey().split("%")[1].split("#")[1].split("-")[1];
                    } else {
                        vnfContainerName = port.getKey().split("%")[0].split("#")[1].split("-")[1];
                        switchName = port.getKey().split("%")[1].split("#")[1].split("-")[1];
                    }
                    deleteDockerVnfOvsPort(
                        switchName,
                        vnfContainerName);
                }
                //Find Port Type by Checking port key
                // Switch#XXXXXXXXX%SwitchXXXXXXXXX
                else if (port.getKey().split("%")[0].split("#")[0].equals("Switch") &&
                    port.getKey().split("%")[1].split("#")[0].equals("Switch")) {
                    //Path Port Naming is like this:
                    /*
                    PortKey = Switch#011535b9-6895-44f5-97cf-7ecf521a7eb9%Switch#2834659e-a02f-416d-ad35-e8880c672127
                    Switch1.key = Switch#011535b9-6895-44f5-97cf-7ecf521a7eb9
                    Switch2.key = Switch#2834659e-a02f-416d-ad35-e8880c672127
                    so path0 should be = 6895_a02f
                    and path1 should be = a02f_6895
                    so with this kind of naming strategy we can determine switch names and path ports from port key
                    and no extra loop needed.
                    deleteBridgePort("ovsdb://HOST1", 6895, 6895_a02f)
                    deleteBridgePort("ovsdb://HOST1", a02f, a02f_6895)
                     */
                    deleteBridgePort(
                        "ovsdb://HOST1",
                        port.getKey().split("%")[0].split("#")[1].split("-")[1],
                        port.getKey().split("%")[0].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[1].split("#")[1].split("-")[1]
                    );
                    deleteBridgePort(
                        "ovsdb://HOST1",
                        port.getKey().split("%")[1].split("#")[1].split("-")[1],
                        port.getKey().split("%")[1].split("#")[1].split("-")[1] + "_" + port.getKey().split("%")[0].split("#")[1].split("-")[1]
                    );
                }
                //Find Port Type by Checking port key
                // Switch#XXXXXXXXX%ControllerXXXXXXXXX
                else if ((port.getKey().split("%")[0].split("#")[0].equals("Switch") && port.getKey().split("%")[1].split("#")[0].equals("Controller")) ||
                    (port.getKey().split("%")[1].split("#")[0].equals("Switch") && port.getKey().split("%")[0].split("#")[0].equals("Controller"))) {

                    //TODO do something abt controller <-> switches
                }
            }
        }


        //VNF PART
        List<String> mustDeleteContainerVnf = new ArrayList<>();
        for (VnfVM vnf : topology.getVnfs()) {
            mustDeleteContainerVnf.add(vnf.getDescribtion().split("#")[1]);
        }
        //Delete docker container(VNF)
        deleteDockerVnf(mustDeleteContainerVnf);

        //Bridge Part
        //Delete bridges
        for (SwitchVM swt : topology.getSwitches()) {
            deleteBridge(
                "ovsdb://HOST1",
                swt.getDescribtion().split("#")[1]
            );
        }

        // Controller Part
        //Delete Controller
        List<String> mustDeleteContainerController = new ArrayList<>();
        for (ControllerVM controller : topology.getControllers()) {
            mustDeleteContainerController.add(controller.getName());
        }
        //Delete Controller Docker container
        deleteDockerVnf(mustDeleteContainerController);

    }

    public void setControllerForAllBridges(TopologyVM topology) {
        if(topology != null){
            for (SwitchVM swt : topology.getSwitches()) {
                // TODO change this shit
                // Update controller by resetting its port
                createBridge(
                    "ovsdb://HOST1",
                    swt.getDescribtion().split("#")[1],
                    swt.getDescribtion().split("#")[1],
                    "192.168.1.7",
                    "6632",
                    "ovsdb:ovsdb-bridge-protocol-openflow-13"
                );
                createBridge(
                    "ovsdb://HOST1",
                    swt.getDescribtion().split("#")[1],
                    swt.getDescribtion().split("#")[1],
                    "192.168.1.7",
                    "6633",
                    "ovsdb:ovsdb-bridge-protocol-openflow-13"
                );
            }
        }
    }

    private Integer createBridge(String nodeId, String bridgeId, String bridgeName, String controllerIp, String controllerPort, String protocolVersion) {
        PutCreateBridge putCreateBridge = new PutCreateBridge(
            nodeId,
            bridgeId,
            bridgeName,
            controllerIp,
            controllerPort,
            protocolVersion
        );
        return putCreateBridge.execute();
    }

    private Integer createBridgePort(String nodeId, String bridgeName, String portName, String portOptionKey, String portOptionValue, String interfaceType) {
        PutCreateBridgePort putCreateBridgePor1 = new PutCreateBridgePort(
            nodeId,
            bridgeName,
            portName,
            portOptionKey,
            portOptionValue,
            interfaceType
        );
        return putCreateBridgePor1.execute();
    }

    private Integer deleteBridge(String nodeId, String bridgeId) {
        DeleteBridge deleteBridge = new DeleteBridge(
            nodeId,
            bridgeId
        );
        return deleteBridge.execute();
    }

    private Integer deleteBridgePort(String nodeId, String bridgeName, String portName) {
        DeleteBridgePort deleteBridgePort = new DeleteBridgePort(
            nodeId,
            bridgeName,
            portName
        );
        return deleteBridgePort.execute();
    }

    private Integer getNetworkTopology() {
        return null;
    }

    private void createDockerVnf(String imgName, String containerName) {
        CreateContainerResponse container
            = dockerClient.createContainerCmd(imgName)
            .withCmd("/bin/bash", "-c", "while true; do sleep 1000; done")
            .withName(containerName)
            .withNetworkMode("none")
            .exec();

        this.dockerClient.startContainerCmd(container.getId()).exec();
        vnfContainers.add(container);
    }

    private void createOdlDocker(String imgName, String containerName) {
        CreateContainerResponse container
            = dockerClient.createContainerCmd(imgName)
            .withCmd("/opt/opendaylight/bin/karaf")
            .withName(containerName)
            .withPortBindings(PortBinding.parse("6633:6633"), PortBinding.parse("8181:8181"), PortBinding.parse("8101:8101"))
            .exec();
        this.dockerClient.startContainerCmd(container.getId()).exec();
    }

    private void deleteDockerVnf(List<String> containerName) {
        for (Container container : dockerClient.listContainersCmd().exec()) {
            for (String str : containerName) {
                //Regex for that "container.getNames()[0]" return "/containerName" so we do'nt need "/"
                if (str.equals(container.getNames()[0].split("/")[1])) {
                    dockerClient.killContainerCmd(container.getId()).exec();
                    dockerClient.removeContainerCmd(container.getId()).exec();
                }
            }
        }
    }

    private void createDockerVnfOvsPort(String bridgeName, String containerName, String ipAddress) {
        List<String> commandArray = new ArrayList<String>();
        String command = "ovs-docker add-port " + bridgeName + " eth1 " + containerName + " --ipaddress=" + ipAddress + "/24";
        commandArray.add(command);
        ExecuteCommanOverSsh sshCommand = new ExecuteCommanOverSsh();
        if (sshCommand.openConnection("192.168.1.7", 22, "mininet",
            "mininet", 12000000, null)) {
            System.out.println("Connected to the Server");
            sshCommand.sendCommand(commandArray);
            Map<String, String> result = sshCommand.recvData();
            sshCommand.close();
        } else {
            System.out.println("Cant connect to the server");
        }
    }

    private void deleteDockerVnfOvsPort(String bridgeName, String containerName) {
        String command = "ovs-docker.sh del-port " + bridgeName + " eth1 " + containerName;
        List<String> commandArray = new ArrayList<String>();
        commandArray.add(command);
        ExecuteCommanOverSsh sshCommand = new ExecuteCommanOverSsh();
        if (sshCommand.openConnection("192.168.1.7", 22, "mininet",
            "mininet", 12000000, null)) {
            System.out.println("Connected to the Server");
            sshCommand.sendCommand(commandArray);
            Map<String, String> result = sshCommand.recvData();
            sshCommand.close();
        } else {
            System.out.println("Cant connect to the server");
        }

    }

}
