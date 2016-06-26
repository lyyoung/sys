package com.flf.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.Accident;
import com.flf.entity.User;
import com.flf.entity.Yyqj;
import com.flf.service.AccidentService;
import com.flf.util.ExcelHelper;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

@Controller
@RequestMapping(value="/accident")
public class AccidentController {
	
	@Autowired
	private AccidentService accidentService;
	
	@RequestMapping
	public ModelAndView list(Accident accident){
		List<Accident> list = accidentService.listPageAccident(accident);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("accidentinfos");
		mv.addObject("accidentList",list);
		mv.addObject("accident", accident);
		return mv;
	}
	
	@RequestMapping(value="/tongji")
	public String test(){
		return "tongji";
	}
	
	@RequestMapping(value="/tongji1")
	public void tongji1(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		//获得不同类型管线的数量
		Map<String,Integer> map = new HashMap<String,Integer>();
		String category = request.getParameter("category");
		System.out.println(category);
		List<Yyqj> list = accidentService.getSizeByCategory(category);
		System.out.println(list.size());
		for(Yyqj y:list){
			System.out.println(y.getName());
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JSONUtil.serialize(list));
		response.getWriter().flush();
	}
	/**
	 * 保存事故信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveUser(Accident accident){
		ModelAndView mv = new ModelAndView();
		if(accident.getId()!=null){
			accidentService.updateAccident(accident);
		}
		mv.setViewName("save_result");
		mv.addObject("msg","success");
		return mv;
	}
	@RequestMapping(value="/edit")
	public ModelAndView toEdit(@RequestParam String id){
		ModelAndView mv = new ModelAndView();
		System.out.println(id);
		Accident accident = accidentService.getAccidentById(id);
		System.out.println(accident+":");
		mv.setViewName("accident_info");
		mv.addObject("accident", accident);
		return mv;
	}
	
	@RequestMapping(value="/accidentinfo")
	public String toAccidentInfo(){
		return "accident_info";
	}
	@RequestMapping(value="/add")
	public String toAdd(){
		return "accident_add";
	}
	@RequestMapping(value="/batchImport")
	public String batchImport(@RequestParam("filename") MultipartFile Mfile,HttpServletRequest request,HttpServletResponse resposne){
		if(Mfile==null) return null;
		String filename = Mfile.getOriginalFilename();
		long size = Mfile.getSize();
		if(filename==null||"".equals(filename)&&size==0){
			String Msg = "批量导入Excel失败!";
			request.getSession().setAttribute("msg", Msg);
		}
		CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File file = fi.getStoreLocation(); 
		List<Accident> list = new ArrayList<Accident>();
		list = ExcelHelper.parserExcel(filename,file);
		int n = 0;
		if(list!=null)		
		   n = accidentService.insertBatchAccident(list);
		if(n>0){
			String Msg = "批量导入Excel成功!";
			request.getSession().setAttribute("msg", Msg);
		}else{
			String Msg = "批量导入Excel失败!";
			request.getSession().setAttribute("msg", Msg);
		}
		return "accident_add";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}

}
