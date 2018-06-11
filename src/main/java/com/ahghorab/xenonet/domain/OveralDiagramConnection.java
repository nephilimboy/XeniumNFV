package com.ahghorab.xenonet.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Xnet_overal_diagram_connection")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OveralDiagramConnection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 50)
	@Column(name = "srcservername", length = 50, unique = true, nullable = false)
	private String srcServerName;
	
	@Size(max = 50)
	@Column(name = "destservername", length = 50, unique = true, nullable = false)
	private String destServerName;
	
	@Size(max = 50)
	@Column(name = "srcnetworkcardname", length = 50, unique = false, nullable = false)
	private String srcNetworkCardName;
	
	@Size(max = 50)
	@Column(name = "destnetworkcardname", length = 50, unique = false, nullable = false)
	private String destNetworkCardName;
	
	
	@JsonIgnore
    @ManyToOne
    private Server server;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSrcServerName() {
		return srcServerName;
	}


	public void setSrcServerName(String srcServerName) {
		this.srcServerName = srcServerName;
	}


	public String getDestServerName() {
		return destServerName;
	}


	public void setDestServerName(String destServerName) {
		this.destServerName = destServerName;
	}


	public String getSrcNetworkCardName() {
		return srcNetworkCardName;
	}


	public void setSrcNetworkCardName(String srcNetworkCardName) {
		this.srcNetworkCardName = srcNetworkCardName;
	}


	public String getDestNetworkCardName() {
		return destNetworkCardName;
	}


	public void setDestNetworkCardName(String destNetworkCardName) {
		this.destNetworkCardName = destNetworkCardName;
	}


	public Server getServer() {
		return server;
	}


	public void setServer(Server server) {
		this.server = server;
	}
	
	
	
	
}
