package com.fht.test.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name = "tb_cam_v")
public class VisionDto {

	@Id
	@GeneratedValue
	@Column(name = "v_index")
	private int index;
	
	@Column(name = "v_ip")
	private String ip;
	
	@Column(name = "v_port")
	private String port;
	
	@Column(name = "v_id")
	private String id;
	
	@Column(name = "v_pw")
	private String password;
	
	@Column(name = "v_title")
	private String title;
}
