package com.fht.test.dto;

import java.util.List;

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

@Data
@Entity(name = "tb_map")
public class MapDto {

	@Id
	@GeneratedValue
	@Column(name = "m_index")
	private int index;
	/*
	@Column(name = "v_index")
	private Integer vision;
	
	@Column(name = "s_index")
	private Integer spec;
	
	@Column(name = "r_index")
	private Integer radio;
	
	@Column(name = "sys_index")
	private int system;
	*/
	//@OneToOne(mappedBy = "index", fetch = FetchType.LAZY)
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_index")
	private VisionDto visionDto;
	
	//@OneToOne(mappedBy = "index", fetch = FetchType.LAZY)
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "s_index")
	private SpectralDto specDto;
	
	//@OneToOne(mappedBy = "index", fetch = FetchType.LAZY)
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "r_index")
	private RadioactivityDto radioDto;
	
	@JsonIgnore
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "sys_index")
	private SystemDto systemDto;
	
	@OneToMany(mappedBy = "mapDto", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "map_event")
	private List<EventLogDto> eventList;
	
}
