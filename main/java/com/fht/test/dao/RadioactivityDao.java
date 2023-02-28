package com.fht.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.RadioactivityDto;

@Repository
public interface RadioactivityDao extends JpaRepository<RadioactivityDto, Integer> {

}
