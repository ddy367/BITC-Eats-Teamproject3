package com.bitc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

	@RequestMapping("/")
	public String index() throws Exception {
		return "index";
	}
	
}
