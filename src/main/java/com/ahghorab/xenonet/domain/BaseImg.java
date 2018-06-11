package com.ahghorab.xenonet.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

@MappedSuperclass
@Audited
@EntityListeners(BaseImg.class)
public class BaseImg implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Size(max = 50)
	@Column(name = "name", length = 50, unique = true, nullable = false)
	private String name;
	
	@Size(max = 100)
	@Column(name = "deployname", length = 100, unique = true, nullable = false)
	private String deployName;
	
	@Size(max = 200)
	@Column(name = "src", length = 200, unique = true, nullable = false)
	private String Src;
	
	@Size(max = 256)
    @Column(name = "imageurl", length = 256)
    private String imageUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeployName() {
		return deployName;
	}

	public void setDeployName(String deployName) {
		this.deployName = deployName;
	}

	public String getSrc() {
		return Src;
	}

	public void setSrc(String src) {
		Src = src;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

}
