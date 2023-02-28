package com.fht.test.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name = "tb_b")
public class BusinessDto {

	@Id
	@GeneratedValue
	@Column(name = "b_index")
	private int index;
	
	@Column(name = "b_title")
	private String title;
	
	@OneToMany(mappedBy = "businessDto")
	@JsonBackReference(value = "sys_bus")
	//@JsonManagedReference(value = "sys_bus")
	private List<SystemDto> system;
}
