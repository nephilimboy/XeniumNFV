package com.ahghorab.xenonet.web.rest.vm;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import com.ahghorab.xenonet.domain.OveralDiagramConnection;
import com.ahghorab.xenonet.domain.Server;

public class ServerVM {

	private Long id;
	private String name;
	private String os;
	private String status;
	private Set<NetworkCardVM> networkCards = new HashSet<>();
	private Long userId;
	private String sshUsername;
	private String sshPassword;

	private String cpu;
	private String ram;
	private String hhd;
	private String cpuUsage;
	private String ramUsage;
	private String hhdUsage;
	private String dockerVersion;
	private String ovsVersion;
	private String kvmVersion;

	private Set<OveralDiagramConnection> overalDiagramConnections;
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ServerVM() {
	}

	public ServerVM(Server server) {
		this.id = server.getId();
		this.name = server.getName();
		this.os = server.getOs();
		this.status = server.getStatus();

		Set<NetworkCardVM> tempNetworkCards = new HashSet<>();
		server.getNetworkCards().forEach(el -> {
			NetworkCardVM tempNetworkCard = new NetworkCardVM();
			tempNetworkCard.setId(el.getId());
			tempNetworkCard.setIpAddress(el.getIpAddress());
			tempNetworkCard.setIsPrimary((el.getIsPrimary().equals("1")) ? true : false);
			tempNetworkCard.setMacAddress(el.getMacAddress());
			tempNetworkCard.setName(el.getName());
			tempNetworkCards.add(tempNetworkCard);
		});
		this.networkCards = tempNetworkCards;
		
		Set<OveralDiagramConnection> overalDiagramConnections = new HashSet<>();
		server.getOveralDiagramConnections().forEach(el->{
			OveralDiagramConnection overalDiagramConnection = new OveralDiagramConnection();
			overalDiagramConnection.setId(el.getId());
			overalDiagramConnection.setDestNetworkCardName(el.getDestNetworkCardName());
			overalDiagramConnection.setDestServerName(el.getDestServerName());
			overalDiagramConnection.setServer(el.getServer());
			overalDiagramConnection.setSrcNetworkCardName(el.getSrcNetworkCardName());
			overalDiagramConnection.setSrcServerName(el.getSrcServerName());
			overalDiagramConnections.add(overalDiagramConnection);
		});
		this.overalDiagramConnections = overalDiagramConnections;

		this.userId = server.getUser().getId();
		this.sshUsername = server.getSshUsername();
		this.sshPassword = server.getSshPassword();
		this.cpu = server.getCpu();
		this.ram = server.getRam();
		this.hhd = server.getHhd();
		this.cpuUsage = server.getCpuUsage();
		this.ramUsage = server.getRamUsage();
		this.hhdUsage = server.getHhdUsage();
		this.dockerVersion = server.getDockerVersion();
		this.ovsVersion = server.getOvsVersion();
		this.kvmVersion = server.getKvmVersion();

	}

	

	

	public ServerVM(Long id, String name, String os, String status, Set<NetworkCardVM> networkCards, Long userId,
			String sshUsername, String sshPassword, String cpu, String ram, String hhd, String cpuUsage,
			String ramUsage, String hhdUsage, String dockerVersion, String ovsVersion, String kvmVersion,
			Set<OveralDiagramConnection> overalDiagramConnections) {
		super();
		this.id = id;
		this.name = name;
		this.os = os;
		this.status = status;
		this.networkCards = networkCards;
		this.userId = userId;
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
		this.overalDiagramConnections = overalDiagramConnections;
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

	public Set<NetworkCardVM> getNetworkCards() {
		return networkCards;
	}

	public void setNetworkCards(Set<NetworkCardVM> networkCards) {
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

	public Set<OveralDiagramConnection> getOveralDiagramConnections() {
		return overalDiagramConnections;
	}

	public void setOveralDiagramConnections(Set<OveralDiagramConnection> overalDiagramConnections) {
		this.overalDiagramConnections = overalDiagramConnections;
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

	

}
