package com.cognizant.pharmacymanagement.RepresentativesSchedule.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.pharmacymanagement.RepresentativesSchedule.Model.Doctor;
import com.cognizant.pharmacymanagement.RepresentativesSchedule.Model.RepSchedule;

@Service
public class RepScheduleService {
	
	private static List<RepSchedule> details=new ArrayList<RepSchedule>();
	private static int detCount;
	
	@Autowired
	DoctorService doctors;
	
	@Autowired
	RepService reps;
	
	private static List<String> meds=new ArrayList<String>();
	private static Doctor doc=new Doctor();
	
	public List<RepSchedule> returnschedule(Date d)
	{
		details.clear();
		detCount=0;
		reps.initialMark();
		while(detCount<doctors.getDocCount()) {
			if(d.getDay()!=0) {
				doc=doctors.retrieveDoc();
				meds=findMeds(doc.getTreating());
				if(meds!=null) {
					doctors.setAllotment(doc.getName());
				}
				else {
					doc=doctors.retrieveDoc(doc.getName());
				}
				details.add(new RepSchedule(reps.retrieveRep(),doc.getName(),doc.getTreating(),meds,doc.getSlot(),d,doc.getNumber()));   
				d=findNextDay(d);
				detCount++;
			}
			else {
				d=findNextDay(d);
			}
		}
		doctors.resetAllotment();
		return details;
	}
	
	//Method to accept data from Database through Shivank's microservice
	//To convert JSON format to object format, use @RequestBody in parameter-list
	public List<String> findMeds(String Ailment){
		List<String> s=new ArrayList<String>();
		s.add("Orthoherb");
		s.add("Cholecalciferol");
		return s;
	}
	
	public static Date findNextDay(Date date)
	{
		final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
	    return new Date(date.getTime() + MILLIS_IN_A_DAY);
	}
	
}
