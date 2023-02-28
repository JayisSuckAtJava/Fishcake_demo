package com.fht.test.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name = "tb_u")
public class UserDto {

	@Id
	@GeneratedValue
	@Column(name = "u_index")
	private int index;
	
	@Column(name = "u_id")
	private String id;
	
	@Column(name = "u_pw")
	private String password;
	
	@Column(name = "u_class")
	private int uclass;

	@Column(name = "u_info")
	private String info;
	
	/*
	@Column(name = "sys_index")
	private int system;
	*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sys_index")
	private SystemDto systemDto;
}
