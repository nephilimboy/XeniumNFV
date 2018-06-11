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
@Table(name = "Xnet_vnf_diagram")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VnfDiagram extends BaseDiagram implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@OneToOne(cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private VnfBaseImg vnfBaseImg;
	
	
	@ManyToMany(mappedBy = "vnfDiagrams")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SwitchDiagram> switchDiagrams = new HashSet<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public VnfBaseImg getVnfBaseImg() {
		return vnfBaseImg;
	}


	public void setVnfBaseImg(VnfBaseImg vnfBaseImg) {
		this.vnfBaseImg = vnfBaseImg;
	}


	public Set<SwitchDiagram> getSwitchDiagrams() {
		return switchDiagrams;
	}


	public void setSwitchDiagrams(Set<SwitchDiagram> switchDiagrams) {
		this.switchDiagrams = switchDiagrams;
	}
	
	
	
}