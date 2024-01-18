package com.springSec.springSecurity1.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class UserControl {

	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String getLogin(ModelMap map) {
		
		map.put("name", "jhon");
		return "home";
	}
}
