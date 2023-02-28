package com.fht.test.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity(name = "tb_sys_log")
public class SystemLogDto {

	@EmbeddedId
	private SystemLogPK id;
	
	//@Column(name = "sys_index")
	//private int system;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sys_index")
	@JsonBackReference("sys_log")
	private SystemDto systemDto;
	
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "s_l_dt")
//	private Date dt;
//	
//	@Column(name = "s_l_level")
//	private String level;
//	
//	@Column(name = "s_l_msg")
//	private String msg;
//	
//	@Column(name = "s_l_cam")
//	private String cam;
}
