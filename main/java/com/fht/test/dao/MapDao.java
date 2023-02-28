package com.fht.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.MapDto;
import com.fht.test.dto.SystemDto;

@Repository
public interface MapDao extends JpaRepository<MapDto, Integer>{

	List<MapDto> findBySystemDto(SystemDto dto);

}
