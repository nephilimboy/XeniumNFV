package com.ahghorab.xenonet.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Xnet_server_diagram")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ServerDiagram extends BaseDiagram implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private ServerBaseImg serverBaseImg;
	
	@ManyToMany(mappedBy = "serverDiagrams")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SwitchDiagram> switchDiagrams = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServerBaseImg getServerBaseImg() {
		return serverBaseImg;
	}

	public void setServerBaseImg(ServerBaseImg serverBaseImg) {
		this.serverBaseImg = serverBaseImg;
	}

	public Set<SwitchDiagram> getSwitchDiagrams() {
		return switchDiagrams;
	}

	public void setSwitchDiagrams(Set<SwitchDiagram> switchDiagrams) {
		this.switchDiagrams = switchDiagrams;
	}
	
	

	
}