package com.ahghorab.xenonet.service.util;

import com.ahghorab.xenonet.service.dto.MustKeepNodePort;
import com.ahghorab.xenonet.service.dto.MustKeepTopology;
import com.ahghorab.xenonet.service.dto.TopologyTrinityStats;
import com.ahghorab.xenonet.web.rest.vm.*;

import java.util.ArrayList;
import java.util.List;

public final class TopologyDif {
    public static TopologyTrinityStats getTopologyDif(TopologyVM oldTopo, TopologyVM newTopo) {

        // Controller Part
        //****************************************************************************
        List<ControllerVM> mustDeleteControlles = new ArrayList<>();
        List<ControllerVM> mustCreateControlles = new ArrayList<>();
        // Find Must-Delete and Must-keep controller array list
        for (ControllerVM oldController : oldTopo.getControllers()) {
            boolean isMustDelete = true;
            for (ControllerVM newController : newTopo.getControllers()) {
                // old one is the same as new one So keep it
                if (oldController.getKey().equals(newController.getKey())) {
                    isMustDelete = false;
                    break;
                }
            }
            if (isMustDelete) {
                // old one is not same az all new one's so delete it
                mustDeleteControlles.add(oldController);
            }
        }
        // Find Must-create controller array list
        for (ControllerVM newController : newTopo.getControllers()) {
            if (oldTopo.getControllers().size() > 0) {
                boolean isMustCreate = true;
                for (ControllerVM oldController : oldTopo.getControllers()) {
                    // new one is new so create it !!
                    if (oldController.getKey().equals(newController.getKey())) {
                        isMustCreate = false;
                        break;
                    }
                }
                if (isMustCreate) {
                    mustCreateControlles.add(newController);
                }
            } else {
                mustCreateControlles.add(newController);
            }
        }
        // Switch Part
        //****************************************************************************
        List<SwitchVM> mustDeleteSwitch = new ArrayList<>();
        List<SwitchVM> mustCreateSwitch = new ArrayList<>();
        // Find Must-Delete and Must-keep Switch array list
        for (SwitchVM oldSwitch : oldTopo.getSwitches()) {
            boolean isMustDelete = true;
            for (SwitchVM newSwitch : newTopo.getSwitches()) {
                // old one is not same az all new one's so delete it
                if (oldSwitch.getKey().equals(newSwitch.getKey())) {
                    isMustDelete = false;
                    break;
                }
            }
            if (isMustDelete) {
                mustDeleteSwitch.add(oldSwitch);
            }
        }
        // Find Must-create Switch array list
        for (SwitchVM newSwitch : newTopo.getSwitches()) {
            if (oldTopo.getSwitches().size() > 0) {
                boolean isMustCreate = true;
                for (SwitchVM oldSwitch : oldTopo.getSwitches()) {
                    // new one is new so create it !!
                    if (oldSwitch.getKey().equals(newSwitch.getKey())) {
                        isMustCreate = false;
                        break;
                    }
                }
                if (isMustCreate) {
                    mustCreateSwitch.add(newSwitch);
                }
            } else {
                mustCreateSwitch.add(newSwitch);
            }
        }

        // VNF Part
        //****************************************************************************
        List<VnfVM> mustDeleteVnf = new ArrayList<>();
        List<VnfVM> mustCreateVnf = new ArrayList<>();
        // Find Must-Delete and Must-keep Vnf array list
        for (VnfVM oldVnf : oldTopo.getVnfs()) {
            boolean isMustDelete = true;
            for (VnfVM newVnf : newTopo.getVnfs()) {
                // old one is not same az all new one's so delete it
                if (oldVnf.getKey().equals(newVnf.getKey())) {
                    isMustDelete = false;
                    break;
                }
            }
            if (isMustDelete) {
                mustDeleteVnf.add(oldVnf);
            }
        }
        // Find Must-create Vnf array list
        for (VnfVM newVnf : newTopo.getVnfs()) {
            if (oldTopo.getVnfs().size() > 0) {
                boolean isMustCreate = true;
                for (VnfVM oldVnf : oldTopo.getVnfs()) {
                    // new one is new so create it !!
                    if (oldVnf.getKey().equals(newVnf.getKey())) {
                        isMustCreate = false;
                        break;
                    }
                }
                if (isMustCreate) {
                    mustCreateVnf.add(newVnf);
                }
            } else {
                mustCreateVnf.add(newVnf);
            }

        }

        // PORT'S Part
        //****************************************************************************
        List<DiagramEntityConnectionsVM> mustDeletePort = new ArrayList<>();
        List<DiagramEntityConnectionsVM> mustCreatePort = new ArrayList<>();
        // Find Must-Delete and Must-keep Port array list
        for (DiagramEntityConnectionsVM oldPort : oldTopo.getDiagramEntityConnections()) {
            boolean isMustDelete = true;
            for (DiagramEntityConnectionsVM newPort : newTopo.getDiagramEntityConnections()) {
                // old one is not same az all new one's so delete it
                if (oldPort.getKey().equals(newPort.getKey())) {
                    isMustDelete = false;
                    break;
                }
            }
            if (isMustDelete) {
                mustDeletePort.add(oldPort);
            }
        }
        // Find Must-create Vnf array list
        for (DiagramEntityConnectionsVM newPort : newTopo.getDiagramEntityConnections()) {
            if (oldTopo.getVnfs().size() > 0) {
                boolean isMustCreate = true;
                for (DiagramEntityConnectionsVM oldPort : oldTopo.getDiagramEntityConnections()) {
                    // new one is new so create it !!
                    if (oldPort.getKey().equals(newPort.getKey())) {
                        isMustCreate = false;
                        break;
                    }
                }
                if (isMustCreate) {
                    mustCreatePort.add(newPort);
                }
            } else {
                mustCreatePort.add(newPort);
            }

        }
        //****************************************************************************

        TopologyVM mustCreateTopology = new TopologyVM(
            mustCreateControlles,
            mustCreateSwitch,
            mustCreateVnf,
            mustCreatePort);
        TopologyVM mustDeleteTopology = new TopologyVM(
            mustDeleteControlles,
            mustDeleteSwitch,
            mustDeleteVnf,
            mustDeletePort);

        return new TopologyTrinityStats(
            mustCreateTopology,
            mustDeleteTopology
        );
    }

}
