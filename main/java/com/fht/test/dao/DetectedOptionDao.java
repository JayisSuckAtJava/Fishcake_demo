package com.fht.test.dao;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.DetectedListDto;
import com.fht.test.dto.DetectedOptionDto;
import com.fht.test.dto.DetectedOptionPK;
import com.fht.test.dto.SystemDto;

@Repository
public interface DetectedOptionDao extends JpaRepository<DetectedOptionDto, DetectedOptionPK>{

	DetectedOptionDto findBySystemDto(SystemDto sysDto);

	@Query(value =
			  "update tb_d_opt set "
			  + "o_label = :label, "
			  + "o_conf = :conf, "
			  + "o_s_thres = :sth, "
			  + "o_nms = :nms, "
			  + "o_v_thres = :vth, "
			  + "o_cuda = :cuda "
			  + "where sys_index = :index ", nativeQuery = true)
	@Modifying(clearAutomatically = true)
	@Transactional
	void updateOpt(
			@Param("index") Integer sysIndex,
			@Param("label") boolean label,
			@Param("conf") boolean conf,
			@Param("cuda") boolean cuda,
			@Param("vth") Float vThres,
			@Param("sth") Float sThres,
			@Param("nms") Float nms);
	
	
	  
	 
	
	
	/*
	 * // @Query(value =
	 * "DELETE from tb_d_opt where sys_index = :#{#dto['sysIndex']}; " // +
	 * "INSERT tb_d_opt values ( :#{#dto['label']}, :#{#dto['conf']}, " // +
	 * ":#{#dto['sThres']}, #{#dto['nms']}, :#{#dto['vThres']}, " // +
	 * ":#{#dto['cuda']}, :#{#dto[sysIndex]} )", nativeQuery = true)
	 */
	
	/*
	 * @Query(value =
	 * "DELETE from tb_d_opt where sys_index = :dto.get('sysIndex'); " +
	 * "INSERT tb_d_opt values ( :dto.get('label'), :dto.get('conf'), " +
	 * ":dto.get('sThres'), :dto.get('nms'), :dto.get('vThres'), " +
	 * ":dto.get('cuda'), :dto.get(sysIndex) )", nativeQuery = true)
	 */
}
