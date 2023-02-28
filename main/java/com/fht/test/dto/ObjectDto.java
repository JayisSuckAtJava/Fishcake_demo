package com.fht.test.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name = "tb_obj_list")
public class ObjectDto {

	@Id
	@GeneratedValue
	@Column(name = "obj_index")
	private int index;
	
	@Column(name = "obj_name_en")
	private String en;
	
	@Column(name = "obj_name_ko")
	private String ko;
	
	@Column(name = "obj_info")
	private String info;
	
	@ManyToOne
	@JoinColumn(name = "s_index")
	@JsonBackReference(value = "sys_obj")
	private SystemDto systemDto;
}
