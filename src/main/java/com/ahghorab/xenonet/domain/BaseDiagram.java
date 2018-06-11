package com.ahghorab.xenonet.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;


@MappedSuperclass
@Audited
@EntityListeners(BaseDiagram.class)
public class BaseDiagram implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Size(max = 50)
	@Column(name = "name", length = 50, unique = true, nullable = false)
	private String name;
	
	@Size(max = 100)
	@Column(name = "keycode", length = 100, unique = true, nullable = false)
	private String keyCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	
	
	

}
