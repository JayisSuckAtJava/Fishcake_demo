package com.fht.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.ObjectDto;
import com.fht.test.dto.SystemDto;

@Repository
public interface ObjectDao extends JpaRepository<ObjectDto, Integer> {

	List<ObjectDto> findBySystemDto(SystemDto systemDto);

	ObjectDto findByKo(String object);

}
