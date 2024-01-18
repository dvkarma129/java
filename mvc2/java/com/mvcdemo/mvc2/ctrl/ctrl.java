package com.mvcdemo.mvc2.ctrl;

import org.hibernate.metamodel.mapping.AttributeMappingsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mvcdemo.mvc2.dto.Emp;
import com.mvcdemo.mvc2.repo.EmpRepo;
import com.mvcdemo.mvc2.service.serv;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ctrl {
	boolean validUser = false;
	
	private final serv ser;
	private final EmpRepo empRepo;
	
	
	
//	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute Emp emp) {

		List<Emp> list = empRepo.findAll();
		return new ModelAndView("home", "e", list);
	}
	
	
}
