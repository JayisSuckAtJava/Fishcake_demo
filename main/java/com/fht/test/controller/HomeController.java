package com.fht.test.controller;

import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fht.test.dao.BusinessDao;
import com.fht.test.dao.DetectedListDao;
import com.fht.test.dao.EventLogDao;
import com.fht.test.dao.MapDao;
import com.fht.test.dao.ObjectDao;
import com.fht.test.dao.SystemDao;
import com.fht.test.dao.UserDao;
import com.fht.test.dto.BusinessDto;
import com.fht.test.dto.DetectedListDto;
import com.fht.test.dto.EventLogDto;
import com.fht.test.dto.MapDto;
import com.fht.test.dto.ObjectDto;
import com.fht.test.dto.SystemDto;
import com.fht.test.dto.UserDto;

@Controller
public class HomeController {
	
	@Autowired
	private BusinessDao bDao;
	
	@Autowired
	private SystemDao sDao;
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	private ObjectDao oDao;
	
	@Autowired
	private DetectedListDao dLDao;
	
	@Autowired
	private MapDao mDao;
	
	@Autowired
	private EventLogDao eDao;
	
	@GetMapping(value = {"/main", "/index", "/"})
	public String mainPage(Model model, HttpSession session) {
		Integer bid = (Integer) session.getAttribute("BID");
		if(bid != null) {
			List<BusinessDto> result = bDao.findAll();
			result.remove(0);
			model.addAttribute("businessList", result);
			return "index";			
		}else {
			return "plzlogin";
		}
		
	}
	
	@GetMapping("/show/{bIndex}")
	public String systemList(@PathVariable Integer bIndex ,Model model, HttpSession session) {
		Integer bid = (Integer) session.getAttribute("BID");
		if (bid == 0 || bIndex == 0) {
			List<SystemDto> result = sDao.findAll();
			result.remove(0);
			model.addAttribute("systemList", result);		
			return "index";
		} else if(bid == bIndex) {
			Optional<BusinessDto> dto = bDao.findById(bIndex);
			List<SystemDto> result = sDao.findByBusinessDto(dto.get());
			model.addAttribute("systemList", result);			
			return "index";
		} else {
			return "plzlogin";
		}
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public String loginProc(String inputEmail, String inputPassword, HttpSession session) {
		UserDto result = uDao.findByIdAndPassword(inputEmail, inputPassword);
		if (result != null) {
			session.setAttribute("BID", result.getSystemDto().getBusinessDto().getIndex());
			return String.valueOf(result.getSystemDto().getIndex());
		}else {
			return "fail";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("BID", null);
		return "plzlogin";
	}
	
	@GetMapping("/setting")
	public String settingPage(HttpSession session, Model model) {
		Integer bid = (Integer) session.getAttribute("BID");
		if(bid != null) {
			if(bid == 0) {
				List<BusinessDto> result = bDao.findAll();
				model.addAttribute("businessList", result);
				return "setting";			
			}else {
				Optional<BusinessDto> dto = bDao.findById(bid);
				List<SystemDto> result = sDao.findByBusinessDto(dto.get());
				model.addAttribute("systemList", result);	
				return "setting";
			}
		}else {
			return "plzlogin";
		}
	}
	
	@GetMapping("/object")
	public String objectPage(HttpSession session, Model model) { // ${businessList} // ${systemList}
		Integer bid = (Integer) session.getAttribute("BID");
		if(bid != null) {
			if(bid == 0) {
				//List<ObjectDto> result = oDao.findAll();
				//model.addAttribute("objectList", result);
				List<BusinessDto> businessList = bDao.findAll();
				model.addAttribute("businessList", businessList);
				return "object";			
			}else {
				Optional<BusinessDto> dto = bDao.findById(bid);
				List<SystemDto> systemList = sDao.findByBusinessDto(dto.get());
				model.addAttribute("systemList", systemList);
				return "object";
			}			
		}else {
			return "plzlogin";
		}
	}
	
	@GetMapping("/register")
	public String createUser(HttpSession session) {
		Integer bid = (Integer) session.getAttribute("BID");
		if (bid != null) {
			if(bid == 0) {
				return "register";			
			}else {
				return "401";
			}			
		}else {
			return "plzlogin";
		}
	}
	
	@GetMapping("/createEvent")
	public String createEvent() {
		return "createEvent";
	}
	
	@PostMapping("/createEvent")
	//public String createEventProc(EventLogDto event) {
	public String createEventProc(MultipartHttpServletRequest req) throws IOException, ParseException {
		
		//System.out.println(req.getParameter("detected"));
		//System.out.println(req.getParameter("map"));
		//System.out.println(req.getParameter("dt"));
		
		String sDt = req.getParameter("dt");
		sDt = sDt.replace('T',  ' ');
		
		Integer type = 2;
		String type_str = req.getParameter("type");
		if (type_str.startsWith("v")) {
			type = 0;
		}else if (type_str.startsWith("s")) {
			type = 1;
		}else {
			type = 2;
		}
		Integer detectedListIndex = Integer.parseInt(req.getParameter("detected"));
		Integer mapDtoIndex = Integer.parseInt(req.getParameter("map"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dt = formatter.parse(sDt);
		byte[] data;
		
		if((req.getFile("data") == null || req.getFile("data").isEmpty()) && (!req.getParameter("dataString").isBlank() || req.getParameter("dataString") != null) ) {
			String dataString = req.getParameter("dataString");
			data = dataString.getBytes();
		}else {
			data = req.getFile("data").getBytes();			
		}
		
		Optional<DetectedListDto> dLOpt = dLDao.findById(detectedListIndex);
		Optional<MapDto> mOpt = mDao.findById(mapDtoIndex);
		
		EventLogDto event = new EventLogDto();
		event.setData(data);
		event.setDt(dt);
		event.setType(type);
		event.setDetectedList(dLOpt.get());
		event.setMapDto(mOpt.get());
		if(req.getParameter("root") != null && !req.getParameter("root").isBlank())  {
			Integer root = Integer.parseInt(req.getParameter("root"));
			event.setRoot(root);
		}
		
		eDao.saveAndFlush(event);
		
		return "createEvent";
	}
}

