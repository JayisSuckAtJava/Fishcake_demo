package com.fht.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.SpectralDto;

@Repository
public interface SpectralDao extends JpaRepository<SpectralDto, Integer> {

}
