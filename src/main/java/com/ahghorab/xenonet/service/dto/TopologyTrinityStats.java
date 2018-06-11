package com.ahghorab.xenonet.service.dto;

import com.ahghorab.xenonet.web.rest.vm.TopologyVM;

public class TopologyTrinityStats {
    private TopologyVM mustCreateTopo;
    private TopologyVM mustDeleteTopo;

    public TopologyTrinityStats(TopologyVM mustCreateTopo, TopologyVM mustDeleteTopo) {
        this.mustCreateTopo = mustCreateTopo;
        this.mustDeleteTopo = mustDeleteTopo;
    }

    public TopologyVM getMustCreateTopo() {
        return mustCreateTopo;
    }

    public void setMustCreateTopo(TopologyVM mustCreateTopo) {
        this.mustCreateTopo = mustCreateTopo;
    }

    public TopologyVM getMustDeleteTopo() {
        return mustDeleteTopo;
    }

    public void setMustDeleteTopo(TopologyVM mustDeleteTopo) {
        this.mustDeleteTopo = mustDeleteTopo;
    }
}
