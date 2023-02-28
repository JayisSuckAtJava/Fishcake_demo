package com.fht.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.VisionDto;

@Repository
public interface VisionDao extends JpaRepository<VisionDto, Integer> {

}
