package com.fht.test.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Embeddable
@Data
public class SystemLogPK implements Serializable{

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "s_l_dt")
	private Date dt;
	
	@Column(name = "s_l_level")
	private String level;
	
	@Column(name = "s_l_msg")
	private String msg;
	
	@Column(name = "s_l_cam")
	private String cam;
}
