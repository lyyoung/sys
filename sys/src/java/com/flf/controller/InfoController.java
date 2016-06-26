package com.flf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.Accident;
import com.flf.entity.Info;
import com.flf.entity.Page;
import com.flf.service.AccidentService;
import com.flf.service.InfoService;

@Controller
@RequestMapping(value="/info")
public class InfoController {
	
//	@Autowired
//	private InfoService infoService;
//	
//	@RequestMapping
//	public String info(Model model,Page page){
//		List<Info> infoList = infoService.listPageInfo(page);
//		model.addAttribute("infoList", infoList);
//		model.addAttribute("page", page);
//		return "info";
//	}
	
	@Autowired
	private AccidentService accidentService;
	
	@RequestMapping
	public ModelAndView list(Accident accident){
		List<Accident>list = accidentService.listPageAccident(accident);
		System.out.println("test");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("accidentinfo");
		mv.addObject("accidentList",list);
		mv.addObject("accident", accident);
		return mv;
	}
	
}
