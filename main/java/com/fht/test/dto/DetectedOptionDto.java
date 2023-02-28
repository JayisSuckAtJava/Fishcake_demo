package com.fht.test.dto;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity(name = "tb_d_opt")
public class DetectedOptionDto {

	@EmbeddedId
	private DetectedOptionPK id;
	
	//@Column(name = "sys_index")
	//private int system;
	
	@JsonBackReference(value = "sys_detOpt")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sys_index", unique = true)
	private SystemDto systemDto;

}

