package com.mvcpro.mvcpro.ctrl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvcpro.mvcpro.dto.Emp;
import com.mvcpro.mvcpro.repo.EmpRepo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ctrl {
	boolean validUser = false;
	
	
	private final EmpRepo empRepo;
	
	
	
//	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute Emp emp) {

		List<Emp> list = empRepo.findAll();
		
		return new ModelAndView("home", "e", list);
	}
	
	
}
