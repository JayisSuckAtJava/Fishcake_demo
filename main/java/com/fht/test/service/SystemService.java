package com.fht.test.service;

import java.util.List;
import java.util.Map;

import com.fht.test.dto.DetectedListDto;
import com.fht.test.dto.DetectedOptionPK;
import com.fht.test.dto.EventLogDto;
import com.fht.test.dto.ObjectDto;
import com.fht.test.dto.SpectralDto;
import com.fht.test.dto.SystemDto;
import com.fht.test.dto.UserDto;
import com.fht.test.dto.VisionDto;

public interface SystemService {

	List<EventLogDto> getEventLogList(Integer sysIndex);

	List<SystemDto> getSystemListAll();

	List<SystemDto> getSystemList(Integer bid);

	Map<String, Object> getSetting(Integer sid);

	void updateVision(VisionDto visionDto);

	void updateOpt(DetectedOptionPK optDto, SystemDto sys);

	void updateSpectral(SpectralDto spectralDto);

	SystemDto findSystem(Integer systemIndex);

	void updateOpt2(DetectedOptionPK optDto, SystemDto sys);

	List<DetectedListDto> getDetectedList(Integer index);

	void createDetectedList(DetectedListDto dto);

	void updateDetectedList(DetectedListDto dto);

	void deleteDetectedList(Integer index);

	List<ObjectDto> getObjectList(Integer systemIndex);

	List<SystemDto> getSysByBusiness(String text);

	String createUser(UserDto user);

}
