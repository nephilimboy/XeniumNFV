package com.ahghorab.xenonet.service.dto;

import com.ahghorab.xenonet.web.rest.vm.ControllerVM;
import com.ahghorab.xenonet.web.rest.vm.SwitchVM;
import com.ahghorab.xenonet.web.rest.vm.VnfVM;

import java.util.List;

public class MustKeepTopology {
    private List<MustKeepNodePort<SwitchVM>> switchDifs;
    private List<MustKeepNodePort<VnfVM>> vnfDifs;
    private List<MustKeepNodePort<ControllerVM>> controllerDifs;

    public MustKeepTopology(List<MustKeepNodePort<SwitchVM>> switchDifs, List<MustKeepNodePort<VnfVM>> vnfDifs, List<MustKeepNodePort<ControllerVM>> controllerDifs) {
        this.switchDifs = switchDifs;
        this.vnfDifs = vnfDifs;
        this.controllerDifs = controllerDifs;
    }

    public MustKeepTopology() {
    }

    public List<MustKeepNodePort<SwitchVM>> getSwitchDifs() {
        return switchDifs;
    }

    public void setSwitchDifs(List<MustKeepNodePort<SwitchVM>> switchDifs) {
        this.switchDifs = switchDifs;
    }

    public List<MustKeepNodePort<VnfVM>> getVnfDifs() {
        return vnfDifs;
    }

    public void setVnfDifs(List<MustKeepNodePort<VnfVM>> vnfDifs) {
        this.vnfDifs = vnfDifs;
    }

    public List<MustKeepNodePort<ControllerVM>> getControllerDifs() {
        return controllerDifs;
    }

    public void setControllerDifs(List<MustKeepNodePort<ControllerVM>> controllerDifs) {
        this.controllerDifs = controllerDifs;
    }
}
