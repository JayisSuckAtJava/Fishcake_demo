package com.fht.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.BusinessDto;


@Repository
public interface BusinessDao extends JpaRepository<BusinessDto, Integer> {

	BusinessDto findByTitleContaining(String text);

}
