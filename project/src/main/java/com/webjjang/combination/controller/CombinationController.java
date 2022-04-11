package com.webjjang.combination.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;
@RequestMapping("/combination")
@Controller
@Log4j
public class CombinationController {

	@GetMapping("/view.do")
	public String view() throws Exception{
		
		log.info("조합식");
		
		return "combination/view";
	}
	
}
