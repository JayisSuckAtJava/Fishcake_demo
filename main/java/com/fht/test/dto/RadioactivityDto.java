package com.fht.test.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name = "tb_r")
public class RadioactivityDto {

	@Id
	@GeneratedValue
	@Column(name = "r_index")
	private int index;
	
	@Column(name = "r_title")
	private String title;
}
