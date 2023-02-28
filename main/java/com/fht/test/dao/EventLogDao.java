package com.fht.test.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.EventLogDto;

@Repository
public interface EventLogDao extends JpaRepository<EventLogDto, Integer> {

	
	  @Query(value =
	  "SELECT e.s_e_dt, e.s_e_data, e.s_e_root, e.s_e_index, e.m_index, e.c_type, e.d_id "
	  + "FROM tb_event_log e, (SELECT m.m_index FROM tb_map m WHERE m.sys_index = :sysIndex) r "
	  + "WHERE e.m_index = r.m_index " + "AND e.s_e_root IS null ", nativeQuery = true)
	public List<EventLogDto> mainQuery(Integer sysIndex);
}
