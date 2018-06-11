package com.ahghorab.xenonet.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Xnet_switch_diagram")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SwitchDiagram extends BaseDiagram implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private SwitchBaseImg switchBaseImg;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Xnet_switch_server", joinColumns = {
			@JoinColumn(name = "switch_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "server_id", referencedColumnName = "id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@BatchSize(size = 20)
	private Set<ServerDiagram> serverDiagrams = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Xnet_switch_vnf", joinColumns = {
			@JoinColumn(name = "switch_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "vnf_id", referencedColumnName = "id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@BatchSize(size = 20)
	private Set<VnfDiagram> vnfDiagrams = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Xnet_switch_host", joinColumns = {
			@JoinColumn(name = "switch_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "host_id", referencedColumnName = "id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@BatchSize(size = 20)
	private Set<HostDiagram> hostDiagrams = new HashSet<>();
	
	
	@JsonIgnore
    @ManyToOne
    private Server server;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SwitchBaseImg getSwitchBaseImg() {
		return switchBaseImg;
	}

	public void setSwitchBaseImg(SwitchBaseImg switchBaseImg) {
		this.switchBaseImg = switchBaseImg;
	}

	public Set<ServerDiagram> getServerDiagrams() {
		return serverDiagrams;
	}

	public void setServerDiagrams(Set<ServerDiagram> serverDiagrams) {
		this.serverDiagrams = serverDiagrams;
	}

	public Set<VnfDiagram> getVnfDiagrams() {
		return vnfDiagrams;
	}

	public void setVnfDiagrams(Set<VnfDiagram> vnfDiagrams) {
		this.vnfDiagrams = vnfDiagrams;
	}

	public Set<HostDiagram> getHostDiagrams() {
		return hostDiagrams;
	}

	public void setHostDiagrams(Set<HostDiagram> hostDiagrams) {
		this.hostDiagrams = hostDiagrams;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}


}