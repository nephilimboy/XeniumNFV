package com.ahghorab.xenonet.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahghorab.xenonet.service.ServerService;
import com.ahghorab.xenonet.web.rest.util.HeaderUtil;
import com.ahghorab.xenonet.web.rest.util.PaginationUtil;
import com.ahghorab.xenonet.web.rest.vm.ServerVM;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/server")
public class ServerController {

	private final ServerService serverService;
	private final Logger log = LoggerFactory.getLogger(ServerController.class);
	private static final String ENTITY_NAME = "serverManagement";

	public ServerController(ServerService serverService) {
		this.serverService = serverService;
	}

	@PostMapping("/new")
	@Timed
	public ResponseEntity createServer(@Valid @RequestBody ServerVM serverVm) throws URISyntaxException {

		log.debug("REST request to save Server : {}", serverVm);
		Long newServerVmId = this.serverService.createServer(serverVm);
		return ResponseEntity
				.created(new URI("/api/server/new" + newServerVmId)).headers(HeaderUtil
						.createAlert("A Server is created with identifier ", Long.toString(newServerVmId)))
				.body(null);
	}

	@GetMapping("/getAll")
	@Timed
	public ResponseEntity<List<ServerVM>> getAllServer(@ApiParam Pageable pageable) {
		final Page<ServerVM> pageServerVM = serverService.getAllServer(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pageServerVM, "/api/server/getAll");
		return new ResponseEntity<>(pageServerVM.getContent(), headers, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
    @Timed
    public ResponseEntity<Void> deleteServer(@PathVariable Long id) {
        log.debug("REST request to delete User: {}", id);
        serverService.deleteServer(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "A Server is deleted with identifier ", Long.toString(id))).build();
    }
	
	 @GetMapping("/getById/{id}")
	    @Timed
	    public ResponseEntity<ServerVM> getServerById(@PathVariable Long id) {
	        log.debug("REST request to get Server : {}", id);
	        return ResponseUtil.wrapOrNotFound(serverService.getServerById(id).map(ServerVM::new));
	    }
	

}
