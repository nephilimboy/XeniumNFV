package com.ahghorab.xenonet.service.util.ssh;

import java.util.ArrayList;
import java.util.List;

import com.ahghorab.xenonet.web.rest.vm.NetworkCardVM;

public class ServerStatistics {
	private String serverCPU;
	private String serverRAM;
	private String serverHHD;
	private String dockerVersion;
	private String ovsVersion;
	private String kvmVersion;
	private List<NetworkCardVM> serverStatisticsNetworkCard = new ArrayList<NetworkCardVM>();

	public ServerStatistics(String serverCPU, String serverRAM, String serverHHD, String dockerVersion,
			String ovsVersion, String kvmVersion, List<NetworkCardVM> serverStatisticsNetworkCard) {
		super();
		this.serverCPU = serverCPU;
		this.serverRAM = serverRAM;
		this.serverHHD = serverHHD;
		this.dockerVersion = dockerVersion;
		this.ovsVersion = ovsVersion;
		this.kvmVersion = kvmVersion;
		this.serverStatisticsNetworkCard = serverStatisticsNetworkCard;
	}

	public ServerStatistics() {
	}

	public String getServerCPU() {
		return serverCPU;
	}

	public void setServerCPU(String serverCPU) {
		this.serverCPU = serverCPU;
	}

	public String getServerRAM() {
		return serverRAM;
	}

	public void setServerRAM(String serverRAM) {
		this.serverRAM = serverRAM;
	}

	public String getServerHHD() {
		return serverHHD;
	}

	public void setServerHHD(String serverHHD) {
		this.serverHHD = serverHHD;
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

	public List<NetworkCardVM> getServerStatisticsNetworkCard() {
		return serverStatisticsNetworkCard;
	}

	public void setServerStatisticsNetworkCard(List<NetworkCardVM> serverStatisticsNetworkCard) {
		this.serverStatisticsNetworkCard = serverStatisticsNetworkCard;
	}

}
