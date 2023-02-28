package com.fht.test.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.val;

@Data
@Entity(name = "tb_sys")
public class SystemDto {

	@Id
	@GeneratedValue
	@Column(name = "sys_index")
	private int index;
	
	@Column(name = "sys_title")
	private String title;
	
	//@Column(name = "b_index")
	//private int business;
	
	//@Column(name = "d_id")
	//private int detected;

	@JsonIgnore
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "b_index")
	@JsonManagedReference(value = "sys_bus")
	private BusinessDto businessDto;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "systemDto", cascade = CascadeType.REMOVE)
	@JsonManagedReference(value = "sys_detOpt")
	private DetectedOptionDto DetectedOption;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "systemDto")
	@JsonManagedReference(value = "sys_map")
	private List<MapDto> mapList;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "systemDto")
	@JsonManagedReference(value = "sys_log")
	private List<SystemLogDto> systemLog;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "systemDto")
	@JsonManagedReference(value = "sys_obj")
	private List<ObjectDto> object;
}
