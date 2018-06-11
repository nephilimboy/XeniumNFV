package com.ahghorab.xenonet.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ahghorab.xenonet.web.rest.ServerController;
import com.ahghorab.xenonet.web.rest.vm.ServerVM;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Xnet_server")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Server extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 50)
	@Column(name = "name", length = 50, unique = true, nullable = false)
	private String name;

	@Size(max = 50)
	@Column(name = "os", length = 50)
	private String os;

	@Size(max = 50)
	@Column(name = "status", length = 50)
	private String status;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "server")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<NetworkCard> networkCards = new HashSet<>();
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "server")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<SwitchDiagram> switchDiagrams = new HashSet<>();
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "server")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<OveralDiagramConnection> overalDiagramConnections;
	

	@JsonIgnore
	@ManyToOne
	private User user;

	@Size(max = 50)
	@Column(name = "sshusername", length = 50)
	private String sshUsername;

	@Size(max = 100)
	@Column(name = "sshpassword", length = 100)
	private String sshPassword;

	@Size(max = 20)
	@Column(name = "cpu", length = 20)
	private String cpu;
	@Size(max = 20)
	@Column(name = "ram", length = 20)
	private String ram;
	@Size(max = 20)
	@Column(name = "hhd", length = 20)
	private String hhd;
	@Size(max = 20)
	@Column(name = "cpuusage", length = 20)
	private String cpuUsage;
	@Size(max = 20)
	@Column(name = "ramusage", length = 20)
	private String ramUsage;
	@Size(max = 20)
	@Column(name = "hhdusage", length = 20)
	private String hhdUsage;
	@Size(max = 20)
	@Column(name = "dockerversion", length = 20)
	private String dockerVersion;
	@Size(max = 20)
	@Column(name = "ovsversion", length = 20)
	private String ovsVersion;
	@Size(max = 20)
	@Column(name = "kvmversion", length = 20)
	private String kvmVersion;
	
	public Server(){
		
	}
	
	public Server(ServerVM serverVm){
		this.id = serverVm.getId();
		this.name = serverVm.getName();
		this.os = serverVm.getOs();
		this.status = serverVm.getStatus();
//		this.networkCards = networkCards;
//		this.switchDiagrams = switchDiagrams;
//		this.srcServers = srcServers;
//		this.destServers = destServers;
//		this.user = user;
		this.sshUsername = serverVm.getSshUsername();
		this.sshPassword = serverVm.getSshPassword();
		this.cpu = serverVm.getCpu();
		this.ram = serverVm.getRam();
		this.hhd = serverVm.getHhd();
		this.cpuUsage = serverVm.getCpuUsage();
		this.ramUsage = serverVm.getRamUsage();
		this.hhdUsage = serverVm.getHhdUsage();
		this.dockerVersion = serverVm.getDockerVersion();
		this.ovsVersion = serverVm.getOvsVersion();
		this.kvmVersion = serverVm.getKvmVersion();

//		newServer.setCpu(serverVm.getCpu());
//		newServer.setRam(serverVm.getRam());
//		newServer.setHhd(serverVm.getHhd());
//		newServer.setDockerVersion(serverVm.getDockerVersion());
//		newServer.setOvsVersion(serverVm.getOvsVersion());
//		newServer.setKvmVersion(serverVm.getKvmVersion());
//
//		newServer.setSshUsername(serverVm.getSshUsername());
//		newServer.setSshPassword(serverVm.getSshPassword());
//		
//		newServer.setSrcServers(null);
//		newServer.setDestServers(null);

//		newServer.setUser(this.userService.getUserWithAuthorities());
//		tempServerVM.getNetworkCards().forEach(networkCard -> {
//			NetworkCard networkCardTemp = new NetworkCard();
//			boolean isPrimary = (networkCard.getIsPrimary() == null) ? false : networkCard.getIsPrimary();
//			networkCardTemp.setIsPrimary((isPrimary == true) ? "1" : "0");
//			networkCardTemp.setName(networkCard.getName());
//			networkCardTemp.setMacAddress(networkCard.getMacAddress());
//			networkCardTemp.setIpAddress(networkCard.getIpAddress());
//			networkCardTemp.setServer(newServer);
//			newServer.getNetworkCards().add(networkCardTemp);
//		});
	}


	public Server(Long id, String name, String os, String status, Set<NetworkCard> networkCards,
			Set<SwitchDiagram> switchDiagrams, Set<OveralDiagramConnection> overalDiagramConnections, User user,
			String sshUsername, String sshPassword, String cpu, String ram, String hhd, String cpuUsage,
			String ramUsage, String hhdUsage, String dockerVersion, String ovsVersion, String kvmVersion) {
		super();
		this.id = id;
		this.name = name;
		this.os = os;
		this.status = status;
		this.networkCards = networkCards;
		this.switchDiagrams = switchDiagrams;
		this.overalDiagramConnections = overalDiagramConnections;
		this.user = user;
		this.sshUsername = sshUsername;
		this.sshPassword = sshPassword;
		this.cpu = cpu;
		this.ram = ram;
		this.hhd = hhd;
		this.cpuUsage = cpuUsage;
		this.ramUsage = ramUsage;
		this.hhdUsage = hhdUsage;
		this.dockerVersion = dockerVersion;
		this.ovsVersion = ovsVersion;
		this.kvmVersion = kvmVersion;
	}

	public String getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public String getRamUsage() {
		return ramUsage;
	}

	public void setRamUsage(String ramUsage) {
		this.ramUsage = ramUsage;
	}

	public String getHhdUsage() {
		return hhdUsage;
	}

	public void setHhdUsage(String hhdUsage) {
		this.hhdUsage = hhdUsage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<NetworkCard> getNetworkCards() {
		return networkCards;
	}

	public void setNetworkCards(Set<NetworkCard> networkCards) {
		this.networkCards = networkCards;
	}

	public String getSshUsername() {
		return sshUsername;
	}

	public void setSshUsername(String sshUsername) {
		this.sshUsername = sshUsername;
	}

	public String getSshPassword() {
		return sshPassword;
	}

	public void setSshPassword(String sshPassword) {
		this.sshPassword = sshPassword;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHhd() {
		return hhd;
	}

	public void setHhd(String hhd) {
		this.hhd = hhd;
	}

	public String getDockerVersion() {
		return dockerVersion;
	}

	public void setDockerVersion(String dockerVersion) {
		this.dockerVersion = dockerVersion;
	}

	public String getOvsVersion() {
		return ovsVersion;
	}

	public void setOvsVersion(String ovsVersion) {
		this.ovsVersion = ovsVersion;
	}

	public String getKvmVersion() {
		return kvmVersion;
	}

	public void setKvmVersion(String kvmVersion) {
		this.kvmVersion = kvmVersion;
	}

	public Set<SwitchDiagram> getSwitchDiagrams() {
		return switchDiagrams;
	}

	public void setSwitchDiagrams(Set<SwitchDiagram> switchDiagrams) {
		this.switchDiagrams = switchDiagrams;
	}

	public Set<OveralDiagramConnection> getOveralDiagramConnections() {
		return overalDiagramConnections;
	}

	public void setOveralDiagramConnections(Set<OveralDiagramConnection> overalDiagramConnections) {
		this.overalDiagramConnections = overalDiagramConnections;
	}
	
	

}
