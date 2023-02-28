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
@Entity(name = "tb_d_list")
public class DetectedListDto {

	@Id
	@GeneratedValue
	@Column(name = "d_id")
	private int id;
	
	@Column(name = "d_index")
	private int index;
	
//	@Column(name = "i_index")
//	private int itemIndex;
	
	@Column(name = "d_char")
	private String character;
	
	@Column(name = "d_red")
	private int red;
	
	@Column(name = "d_green")
	private int green;
	
	@Column(name = "d_blue")
	private int blue;
	
	@Column(name = "d_title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "obj_index")
	private ObjectDto obj;

}
