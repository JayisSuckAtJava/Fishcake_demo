package com.fht.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.SystemLogDto;
import com.fht.test.dto.SystemLogPK;

@Repository
public interface SystemLogDao extends JpaRepository<SystemLogDto, SystemLogPK> {

}
