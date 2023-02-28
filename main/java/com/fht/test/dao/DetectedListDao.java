package com.fht.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.BusinessDto;
import com.fht.test.dto.DetectedListDto;
import com.fht.test.dto.ObjectDto;


@Repository
public interface DetectedListDao extends JpaRepository<DetectedListDto, Integer> {

	List<DetectedListDto> findByObj(ObjectDto objectDto);

}
