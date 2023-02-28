package com.fht.test.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name = "tb_cam_s")
public class SpectralDto {

	@Id
	@GeneratedValue
	@Column(name = "s_index")
	private int index;
	
	@Column(name = "s_sample")
	private int sample;
	
	@Column(name = "s_bands")
	private int band;
	
	@Column(name = "s_framerate")
	private int frame;
	
	@Column(name = "s_integration_time")
	private float time;
	
	@Column(name = "s_title")
	private String title;
}
