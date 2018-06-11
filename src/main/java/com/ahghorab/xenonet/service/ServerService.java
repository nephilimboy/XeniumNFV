package com.ahghorab.xenonet.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahghorab.xenonet.domain.NetworkCard;
import com.ahghorab.xenonet.domain.OveralDiagramConnection;
import com.ahghorab.xenonet.domain.Server;
import com.ahghorab.xenonet.repository.OveralDiagramConnectionRepository;
import com.ahghorab.xenonet.repository.ServerRepository;
import com.ahghorab.xenonet.security.SecurityUtils;
import com.ahghorab.xenonet.service.util.ssh.ExecuteCommanOverSsh;
import com.ahghorab.xenonet.web.rest.vm.NetworkCardVM;
import com.ahghorab.xenonet.web.rest.vm.ServerVM;

@Service
@Transactional
public class ServerService {

	private final Logger log = LoggerFactory.getLogger(ServerService.class);

	private final ServerRepository serverRepository;

	private final UserService userService;
	
	private final OveralDiagramConnectionRepository overalDiagramConnectionRepository;

	private int globalIndexCounter = 0;

	public ServerService(ServerRepository serverRepository, UserService userService, OveralDiagramConnectionRepository overalDiagramConnectionRepository) {
		this.serverRepository = serverRepository;
		this.userService = userService;
		this.overalDiagramConnectionRepository = overalDiagramConnectionRepository;
	}

	public Server createServer(String name, String os, String status, Set<NetworkCard> networkCardSet, Set<OveralDiagramConnection> overalDiagramConnections) {

		Server newServer = new Server();
		newServer.setName(name);
		newServer.setOs(os);
		newServer.setStatus(status);
		newServer.setNetworkCards(networkCardSet);
		newServer.setOveralDiagramConnections(overalDiagramConnections);
		this.serverRepository.save(newServer);
		log.debug("Created Information for NewServer: {}", newServer);
		return newServer;
	}

	public Long createServer(ServerVM serverVm) {
//		ServerVM tempServerVM = this.getServerStatistic(serverVm);
		ServerVM tempServerVM = serverVm;
		Server newServer = new Server();
		newServer.setName(tempServerVM.getName());
		newServer.setOs(tempServerVM.getOs());
		newServer.setStatus(tempServerVM.getStatus());

		newServer.setCpu(tempServerVM.getCpu());
		newServer.setRam(tempServerVM.getRam());
		newServer.setHhd(tempServerVM.getHhd());
		newServer.setCpuUsage(tempServerVM.getCpuUsage());
		newServer.setRamUsage(tempServerVM.getRamUsage());
		newServer.setHhdUsage(tempServerVM.getHhdUsage());
		newServer.setDockerVersion(tempServerVM.getDockerVersion());
		newServer.setOvsVersion(tempServerVM.getOvsVersion());
		newServer.setKvmVersion(tempServerVM.getKvmVersion());

		newServer.setSshUsername(tempServerVM.getSshUsername());
		newServer.setSshPassword(tempServerVM.getSshPassword());
		
		newServer.setOveralDiagramConnections(null);

		newServer.setUser(this.userService.getUserWithAuthorities());
		tempServerVM.getNetworkCards().forEach(networkCard -> {
			NetworkCard networkCardTemp = new NetworkCard();
			boolean isPrimary = (networkCard.getIsPrimary() == null) ? false : networkCard.getIsPrimary();
			networkCardTemp.setIsPrimary((isPrimary == true) ? "1" : "0");
			networkCardTemp.setName(networkCard.getName());
			networkCardTemp.setMacAddress(networkCard.getMacAddress());
			networkCardTemp.setIpAddress(networkCard.getIpAddress());
			networkCardTemp.setServer(newServer);
			newServer.getNetworkCards().add(networkCardTemp);
		});
		this.serverRepository.save(newServer);
		log.debug("Created Information for NewServer: {}", newServer);
		return newServer.getId();

	}

	public ServerVM getServerStatistic(ServerVM serverVM) {

		ServerVM tempServerVM = serverVM;
		String primeNetworkCardIpAddress = "";
		List<String> commandArray = new ArrayList<String>();
		List<String> mapArray = new ArrayList<String>();

		String command = "";

		command += "top -bn1 | grep 'Cpu(s)' | sed 's/.*, *\\([0-9.]*\\)%* id.*/\\1/' | awk '{print 100 - $1''}'" + ";"
				+ "echo  $(( $(lscpu | awk '/^Socket/{ print $2 }') * $(lscpu | awk '/^Core/{ print $4 }') ))" + ";"
				+ "df | grep '^/dev/[hs]d' | awk '{s+=$2} END {print s/1048576}'" + ";"
				+ "expr $(awk '/MemTotal/ {print $2}' /proc/meminfo) / 1048576" + ";"
				+ "free | grep Mem | awk '{print $3/$2 * 100.0}'" + ";" + "df -P / | awk '/%/ {print 100 -$5 ''}'" + ";"
				+ "sudo ovs-vsctl --version |head -n 1| cut -d ' ' -f 4" + ";"
				+ "sudo ovs-vsctl --version |head -n 1| cut -d ' ' -f 4" + ";"
				+ "sudo ovs-vsctl --version |head -n 1| cut -d ' ' -f 4" + ";";
		mapArray.add("cpu");
		mapArray.add("ram");
		mapArray.add("hhd");
		mapArray.add("cpuUsage");
		mapArray.add("ramUsage");
		mapArray.add("hhdUsage");
		mapArray.add("dockerVersion");
		mapArray.add("ovsVersion");
		mapArray.add("kvmVersion");

		int index = 0;
		for (NetworkCardVM networkCard : serverVM.getNetworkCards()) {
			mapArray.add("name" + Integer.toString(index));
			mapArray.add("ipAddress" + Integer.toString(index));
			mapArray.add("macAddress" + Integer.toString(index));

			command += "echo " + networkCard.getName() + " ;" + "echo $(ifconfig | grep -A 1 '" + networkCard.getName()
					+ "' | tail -1 | cut -d ':' -f 2 | cut -d ' ' -f 1)" + " ;" + "ifconfig " + networkCard.getName()
					+ " | awk '/HWaddr/ {print $5}'" + " ;";

			if (networkCard.getIsPrimary()) {
				primeNetworkCardIpAddress = networkCard.getIpAddress();
			}
			index++;
		}
		;

		commandArray.add(command);

		ExecuteCommanOverSsh sshCommand = new ExecuteCommanOverSsh();
		if (sshCommand.openConnection(primeNetworkCardIpAddress, 22, serverVM.getSshUsername(),
				serverVM.getSshPassword(), 12000000, mapArray)) {
			System.out.println("Connected to the Server");
			// sshCommand.sendCommand("screen -S sss -dm bash -c 'sudo mn'");
			sshCommand.sendCommand(commandArray);
			Map<String, String> result = sshCommand.recvData();
			tempServerVM.setCpu(result.get("cpu"));
			tempServerVM.setRam(result.get("ram"));
			tempServerVM.setHhd(result.get("hhd"));
			tempServerVM.setCpuUsage(result.get("cpuUsage"));
			tempServerVM.setRamUsage(result.get("ramUsage"));
			tempServerVM.setHhdUsage(result.get("hhdUsage"));
			tempServerVM.setDockerVersion(result.get("dockerVersion"));
			tempServerVM.setOvsVersion(result.get("ovsVersion"));
			tempServerVM.setKvmVersion(result.get("kvmVersion"));
			this.globalIndexCounter = 0;
			tempServerVM.getNetworkCards().forEach(el -> {
				el.setName(result.get("name" + Integer.toString(this.globalIndexCounter)));
				el.setIpAddress(result.get("ipAddress" + Integer.toString(this.globalIndexCounter)));
				el.setMacAddress(result.get("macAddress" + Integer.toString(this.globalIndexCounter)));
				this.globalIndexCounter++;
			});
			sshCommand.close();
		} else {
			System.out.println("Cant connect to the server");
		}

		// PRINTING FOR TEST REMOVE IT AFTER
		// --------------------------------------------

		System.out.println("DATA RECIEV FROM SERVER");

		System.out.println(tempServerVM.getCpu());
		System.out.println(tempServerVM.getRam());
		System.out.println(tempServerVM.getHhd());
		System.out.println(tempServerVM.getDockerVersion());
		System.out.println(tempServerVM.getOvsVersion());
		System.out.println(tempServerVM.getKvmVersion());

		tempServerVM.getNetworkCards().forEach(el -> {
			System.out.println(el.getName());
			System.out.println(el.getIpAddress());
			System.out.println(el.getMacAddress());
		});
		// --------------------------------------------

		return tempServerVM;
	}
	
	public void updateServerOveralConnections(ServerVM serverVm) {
        serverRepository.findOneById(serverVm.getId()).ifPresent(server -> {
        	
        	server.getOveralDiagramConnections().clear();
        	Set<OveralDiagramConnection> overalDiagramConnections = new HashSet<>();
        	
        	serverVm.getOveralDiagramConnections().forEach(el->{
    			OveralDiagramConnection overalDiagramConnection = new OveralDiagramConnection();
    			overalDiagramConnection.setId(el.getId());
    			overalDiagramConnection.setDestNetworkCardName(el.getDestNetworkCardName());
    			overalDiagramConnection.setDestServerName(el.getDestServerName());
    			overalDiagramConnection.setServer(el.getServer());
    			overalDiagramConnection.setSrcNetworkCardName(el.getSrcNetworkCardName());
    			overalDiagramConnection.setSrcServerName(el.getSrcServerName());
    			overalDiagramConnections.add(overalDiagramConnection);
    		});
        	server.setOveralDiagramConnections(overalDiagramConnections);
            log.debug("Changed Overal Connection Information for Sever: {}", serverVm.getName());
        });
    }

	public void deleteServer(Long id) {
		this.serverRepository.findOneById(id).ifPresent(server -> {
			this.serverRepository.delete(server);
			log.debug("Deleted Server: {}", server);
		});

	}

	@Transactional(readOnly = true)
	public Page<ServerVM> getAllServer(Pageable pageable) {
		return serverRepository.findBy(SecurityUtils.getCurrentUserLogin(), pageable).map(ServerVM::new);
	}

	@Transactional(readOnly = true)
	public Optional<Server> getServerById(Long id) {
		return serverRepository.findOneById(id);
	}

}
