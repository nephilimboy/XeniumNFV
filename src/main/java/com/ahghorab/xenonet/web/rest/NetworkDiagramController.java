package com.ahghorab.xenonet.web.rest;

import com.ahghorab.xenonet.service.NetworkDiagramService;
import com.ahghorab.xenonet.service.util.TopologyDif;
import com.ahghorab.xenonet.web.rest.util.HeaderUtil;
import com.ahghorab.xenonet.web.rest.vm.ControllerVM;
import com.ahghorab.xenonet.web.rest.vm.SwitchVM;
import com.ahghorab.xenonet.web.rest.vm.TopologyVM;
import com.ahghorab.xenonet.web.rest.vm.VnfVM;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/networkDiagram")
public class NetworkDiagramController {
    private final Logger log = LoggerFactory.getLogger(NetworkDiagramController.class);
    private static final String ENTITY_NAME = "networkDiagram";

    private final NetworkDiagramService networkDiagramService;

    public NetworkDiagramController(NetworkDiagramService networkDiagramService) {
        this.networkDiagramService = networkDiagramService;
    }

    @PostMapping("/new")
    @Timed
    public ResponseEntity createTopology(@Valid @RequestBody TopologyVM topologyVM) throws URISyntaxException {
        this.networkDiagramService.parsTopology(topologyVM);
        return ResponseEntity
            .created(new URI("/api/networkDiagram/new" + topologyVM)).headers(HeaderUtil
                .createAlert("A Topology is created with identifier ", Long.toString(1)))
            .body(null);
    }

    @DeleteMapping("/deleteTopo/{id}")
    @Timed
    public ResponseEntity<Void> deleteTopology(@PathVariable Long id) {
        log.debug("REST request to delete Topology: {}", id);
        this.networkDiagramService.deleteTopology();
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("A Topology is deleted with identifier ", Long.toString(id))).build();
    }

}
