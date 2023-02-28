package com.fht.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fht.test.dao.BusinessDao;
import com.fht.test.dao.DetectedListDao;
import com.fht.test.dao.DetectedOptionDao;
import com.fht.test.dao.EventLogDao;
import com.fht.test.dao.ObjectDao;
import com.fht.test.dao.SpectralDao;
import com.fht.test.dao.SystemDao;
import com.fht.test.dao.UserDao;
import com.fht.test.dao.VisionDao;
import com.fht.test.dto.BusinessDto;
import com.fht.test.dto.DetectedListDto;
import com.fht.test.dto.DetectedOptionDto;
import com.fht.test.dto.DetectedOptionPK;
import com.fht.test.dto.EventLogDto;
import com.fht.test.dto.MapDto;
import com.fht.test.dto.ObjectDto;
import com.fht.test.dto.SpectralDto;
import com.fht.test.dto.SystemDto;
import com.fht.test.dto.UserDto;
import com.fht.test.dto.VisionDto;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDao sys;
	
	@Autowired
	private EventLogDao eventLog;
	
	@Autowired
	private DetectedOptionDao optDao;
	
	@Autowired
	private VisionDao visionDao;

	@Autowired
	private SpectralDao specDao;
	
	@Autowired
	private ObjectDao objDao;
	
	@Autowired
	private DetectedListDao detDao;
	
	@Autowired
	private BusinessDao busDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<EventLogDto> getEventLogList(Integer sysIndex) {
		// 여기서 ROOT 가 NULL 이 아닌건 Byte 어레이를 풀어서 줘야하나?
		return  eventLog.mainQuery(sysIndex);
	}

	@Override
	public List<SystemDto> getSystemListAll() {
		return sys.findAll();
	}

	@Override
	public List<SystemDto> getSystemList(Integer bid) {
		BusinessDto dto = new BusinessDto();
		dto.setIndex(bid);
		return sys.findByBusinessDto(dto);
	}

	@Override
	public SystemDto findSystem(Integer systemIndex) {
		Optional<SystemDto> opt = sys.findById(systemIndex);
		return opt.get();
	}

	@Override
	public Map<String, Object> getSetting(Integer sid) {
		Optional<SystemDto> system = sys.findById(sid);
		SystemDto sysDto = system.get();
		DetectedOptionDto optDto = optDao.findBySystemDto(sysDto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<MapDto> mapping = sysDto.getMapList();
		map.put("system", system);
		map.put("opt", optDto);
		
		for(MapDto i : mapping) {
			if(i.getVisionDto() != null) {
				map.put("vision", i);
			}else if(i.getSpecDto() != null) {
				map.put("spectral", i);
			}
		}
		
		return map;

	}

	@Override
	public void updateOpt(DetectedOptionPK optDto, SystemDto sys) {
		DetectedOptionDto old = optDao.findBySystemDto(sys);
		//System.out.println(old);
		optDao.delete(old);
		DetectedOptionDto dto = new DetectedOptionDto();
		dto.setSystemDto(sys);
		dto.setId(optDto);
		optDao.saveAndFlush(dto);			
		
	}
	
	@Override
	public void updateOpt2(DetectedOptionPK optDto, SystemDto sys) {;
		Integer sysIndex = sys.getIndex();
		boolean label = optDto.isLabel();
		boolean conf = optDto.isConf();
		boolean cuda = optDto.isCuda();
		Float vThres = optDto.getVisionThreshold();
		Float sThres = optDto.getSpectralThreshold();
		Float nms = optDto.getNonMaximumSuppression();
		optDao.updateOpt(sysIndex, label, conf, cuda, vThres, sThres, nms);		
	}
	
	@Override
	public void updateVision(VisionDto visionDto) {
		visionDao.saveAndFlush(visionDto);
	}


	@Override
	public void updateSpectral(SpectralDto spectralDto) {
		specDao.saveAndFlush(spectralDto);
	}

	@Override
	public List<DetectedListDto> getDetectedList(Integer index) {
		Optional<ObjectDto> opt = objDao.findById(index);
		if(!opt.isEmpty()) {
			List<DetectedListDto> result = detDao.findByObj(opt.get());
			return result;
		}else {
			return null;			
		}
	}

	@Override
	public void createDetectedList(DetectedListDto dto) {
		detDao.save(dto);
		
	}

	@Override
	public void updateDetectedList(DetectedListDto dto) {
		detDao.save(dto);
		
	}

	@Override
	public void deleteDetectedList(Integer index) {
		detDao.deleteById(index);
	}

	@Override
	public List<ObjectDto> getObjectList(Integer systemIndex) {
		Optional<SystemDto> opt = sys.findById(systemIndex);
		return objDao.findBySystemDto(opt.get());
	}

	@Override
	public List<SystemDto> getSysByBusiness(String text) {
		BusinessDto result = busDao.findByTitleContaining(text);
		return sys.findByBusinessDto(result);
	}

	@Override
	public String createUser(UserDto user) {
		UserDto result = userDao.findById(user.getId());
		if(result != null) {
			return "fail";
		}else {
			userDao.saveWithPassword(user);
			return "done";
		}
	}
	
	
}
