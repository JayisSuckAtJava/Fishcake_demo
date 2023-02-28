package com.fht.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.BusinessDto;
import com.fht.test.dto.DetectedListDto;
import com.fht.test.dto.SystemDto;


@Repository
public interface SystemDao extends JpaRepository<SystemDto, Integer> {

	List<SystemDto> findByBusinessDto(BusinessDto dto);

	SystemDto findByTitleContaining(String sysTitle);

}
