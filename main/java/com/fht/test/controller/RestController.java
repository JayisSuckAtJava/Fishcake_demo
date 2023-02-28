package com.fht.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fht.test.dao.BusinessDao;
import com.fht.test.dao.DetectedListDao;
import com.fht.test.dao.DetectedOptionDao;
import com.fht.test.dao.EventLogDao;
import com.fht.test.dao.MapDao;
import com.fht.test.dao.ObjectDao;
import com.fht.test.dao.RadioactivityDao;
import com.fht.test.dao.SpectralDao;
import com.fht.test.dao.SystemDao;
import com.fht.test.dao.SystemLogDao;
import com.fht.test.dao.UserDao;
import com.fht.test.dao.VisionDao;
import com.fht.test.dto.BusinessDto;
import com.fht.test.dto.DetectedListDto;
import com.fht.test.dto.DetectedOptionDto;
import com.fht.test.dto.EventLogDto;
import com.fht.test.dto.MapDto;
import com.fht.test.dto.ObjectDto;
import com.fht.test.dto.RadioactivityDto;
import com.fht.test.dto.SpectralDto;
import com.fht.test.dto.SystemDto;
import com.fht.test.dto.SystemLogDto;
import com.fht.test.dto.UserDto;
import com.fht.test.dto.VisionDto;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private BusinessDao business;
	
	@Autowired
	private DetectedListDao detectedList;
	
	@Autowired
	private SystemDao sys;
	
	@Autowired
	private DetectedOptionDao detectedOpt;
	
	@Autowired
	private EventLogDao eventLog;
	
	@Autowired
	private MapDao map;
	
	@Autowired
	private RadioactivityDao radio;
	
	@Autowired
	private SpectralDao spec;
	
	@Autowired
	private SystemLogDao sysLog;
	
	@Autowired
	private UserDao user;
	
	@Autowired
	private VisionDao vision;
	
	@Autowired
	private ObjectDao obj;
	
	@GetMapping("/get/system")
	public List<SystemDto> getSystem() {
		List<SystemDto> result = sys.findAll();
		return result;
	}
	
	@GetMapping("/get/business")
		public List<BusinessDto> getBusiness(){
		List<BusinessDto> result = business.findAll();
		return result;
	}
	
	@GetMapping("/get/detectedList")
	public List<DetectedListDto> getDetectedList() {
		List<DetectedListDto> result = detectedList.findAll();
		return result;
	}
	
	@GetMapping("/get/detectedOpt")
	public List<DetectedOptionDto> getDeteOption() {
		List<DetectedOptionDto> result = detectedOpt.findAll();
		return result;
	}
	
	@GetMapping("/get/eventLog")
	public List<EventLogDto> getEvent() {
		List<EventLogDto> result = eventLog.findAll();
		return result;
	}
	
	@GetMapping("/get/map")
	public List<MapDto> getMap() {
		List<MapDto> result = map.findAll();
		return result;
	}
	
	@GetMapping("/get/radio")
	public List<RadioactivityDto> getRadioactivity() {
		List<RadioactivityDto> result = radio.findAll();
		return result;
	}
	@GetMapping("/get/spec")
	public List<SpectralDto> getSpectral() {
		List<SpectralDto> result = spec.findAll();
		return result;
	}
	@GetMapping("/get/sysLog")
	public List<SystemLogDto> getSystemLog() {
		List<SystemLogDto> result = sysLog.findAll();
		return result;
	}
	@GetMapping("/get/user")
	public List<UserDto> getUser() {
		List<UserDto> result = user.findAll();
		return result;
	}
	@GetMapping("/get/vision")
	public List<VisionDto> getVision() {
		List<VisionDto> result = vision.findAll();
		return result;
	}
	@GetMapping("/get/object")
	public List<ObjectDto> getObject() {
		List<ObjectDto> result = obj.findAll();
		return result;
	}

	@GetMapping("/get/sysList/{bIndex}")
	public List<SystemDto> getSysList(@PathVariable Integer bIndex) {
		//Optional<BusinessDto> dto = business.findById(bIndex);
		BusinessDto dto = new BusinessDto();
		dto.setIndex(bIndex);
		//List<SystemDto> result = sys.findByBusinessDto(dto.get());
		List<SystemDto> result = sys.findByBusinessDto(dto);
		return result;
	}
	
	@GetMapping("/get/detList")
	public List<DetectedListDto> getDetList(String object) {
		ObjectDto dto = obj.findByKo(object);
		return detectedList.findByObj(dto);
	}
	
	@GetMapping("/get/sysMap")
	public List<MapDto> getSysMap(String sysTitle) {
		SystemDto dto = sys.findByTitleContaining(sysTitle);
		return map.findBySystemDto(dto);
	}
}
