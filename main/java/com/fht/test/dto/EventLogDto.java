package com.fht.test.dto;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.val;

@Data
@Entity(name = "tb_event_log")
public class EventLogDto {

	@Id
	@GeneratedValue
	@Column(name = "s_e_index")
	private int index;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "s_e_dt")
	private Date dt;
	
	@Column(name = "s_e_data")
	private byte[] data;
	
	@Column(name = "c_type")
	private Integer type;	
	
	
	@Column(name = "s_e_root")
	private Integer root;
	/*
	@Column(name = "d_id")
	private int detected;
	
	@Column(name = "m_index")
	private int map;
	*/
		
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "s_e_root")
	private List<EventLogDto> rootList;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JsonManagedReference(value = "event_dct")
	@JoinColumn(name = "d_id")
	private DetectedListDto detectedList;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_index")
	@JsonBackReference(value = "map_event")
	private MapDto mapDto;
}
