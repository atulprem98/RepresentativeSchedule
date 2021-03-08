package com.cognizant.pharmacymanagement.RepresentativesSchedule.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.pharmacymanagement.RepresentativesSchedule.Model.RepSchedule;
import com.cognizant.pharmacymanagement.RepresentativesSchedule.Service.RepScheduleService;

@RestController
public class RepSchedController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@Autowired
	RepScheduleService service;
	
	@RequestMapping(value ="/viewSchedule", method = RequestMethod.GET)
	public ModelAndView showdate() {
		return new ModelAndView("viewSchedule");
	}
	
	@GetMapping("/RepSchedule")
	public ModelAndView showschedule(@RequestParam Date startdate,ModelMap model){    
		//return service.returnschedule(startdate);
		model.put("details",service.returnschedule(startdate));
		return new ModelAndView("returnSchedule");
	}
}
