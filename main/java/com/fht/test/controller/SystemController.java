package com.fht.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fht.test.dto.DetectedListDto;
import com.fht.test.dto.DetectedOptionDto;
import com.fht.test.dto.DetectedOptionPK;
import com.fht.test.dto.EventLogDto;
import com.fht.test.dto.ObjectDto;
import com.fht.test.dto.SpectralDto;
import com.fht.test.dto.SystemDto;
import com.fht.test.dto.UserDto;
import com.fht.test.dto.VisionDto;
import com.fht.test.service.SystemService;

@Controller
public class SystemController {

	@Autowired
	private SystemService service;
	
	
	
	@GetMapping("/get/log/{sysIndex}")
	@ResponseBody
	public List<EventLogDto> getEventLogList(@PathVariable Integer sysIndex) {
		List<EventLogDto> eventLog = service.getEventLogList(sysIndex);
		return eventLog;
	}
	
	@GetMapping("/get/system/{businessIndex}")
	@ResponseBody
	public List<SystemDto> getSystemList(@PathVariable Integer businessIndex) {
		List<SystemDto> result;
		if(businessIndex == 0) {
			result = service.getSystemListAll();
			result.remove(0);
		}else {
			result = service.getSystemList(businessIndex);
		}
		return result;
	}
	
	@GetMapping("/get/setting/{systemIndex}")
	@ResponseBody
	public Map<String, Object> getSetting(@PathVariable Integer systemIndex) {
		return service.getSetting(systemIndex);
	}
	
	@PostMapping("/get/setting/update/opt")
	@ResponseBody
	public void updateOpt(DetectedOptionPK optDto, Integer systemIndex) {
		SystemDto sys = service.findSystem(systemIndex);
		service.updateOpt2(optDto, sys);
	}
	
	@PostMapping("/get/setting/update/vision")
	@ResponseBody
	public void updateVision(VisionDto visionDto) {
		service.updateVision(visionDto);
	}
	
	@PostMapping("/get/setting/update/spectral")
	@ResponseBody
	public void updateSpectral(SpectralDto spectralDto) {
		service.updateSpectral(spectralDto);
	}
	
	@GetMapping("/get/object/{index}/substance")
	@ResponseBody
	public List<DetectedListDto> getDetectedList(@PathVariable Integer index) {
		List<DetectedListDto> result = service.getDetectedList(index);
		return result;
	}
	
	@PostMapping("/get/object/create")
	@ResponseBody
	public void createDetectedList(DetectedListDto dto) {
		service.createDetectedList(dto);
	}
	
	@PostMapping("/get/object/update")
	@ResponseBody
	public void updateDetectedList(DetectedListDto dto) {
		service.updateDetectedList(dto);
	}
	
	@PostMapping("/get/object/delete")
	@ResponseBody
	public void deleteDetectedList(@RequestParam Integer index) {
		service.deleteDetectedList(index);
	}
	
	@GetMapping("/testt")
	public String ttwewe() {
		return "test";
	}
	
	@GetMapping("/get/object/{systemIndex}")
	@ResponseBody
	public List<ObjectDto> getObjectList(@PathVariable Integer systemIndex) {
		List<ObjectDto> result = service.getObjectList(systemIndex);
		return result;
	}
	
	@GetMapping("/get/sysByBusiness")
	@ResponseBody
	public List<SystemDto> getSysByBusiness(String text) {
		if (!text.isEmpty()) {
			return service.getSysByBusiness(text);			
		}
		return null;
	}
	
	@PostMapping("/user/create")
	@ResponseBody
	public String createUser(UserDto user) {
		return service.createUser(user);
	}
}
