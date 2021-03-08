package com.cognizant.pharmacymanagement.RepresentativesSchedule.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.cognizant.pharmacymanagement.RepresentativesSchedule.Model.Doctor;

@Service
public class DoctorService {
	private static List<Doctor> doclist=new ArrayList<Doctor>();
	private static int docCount = 0;
	
	static{
		//adding doctors to list from flat file
		try {
			docCount=0;
			Scanner input = new Scanner(new File("D:\\Java\\RepresentativesSchedule\\src\\main\\resources\\DocList.txt"));
			input.useDelimiter("-|\n");
			while(input.hasNext()){
				String name=input.next();
				String number=input.next();
				String treating=input.next();
				String sl=input.next().replace("\r", "");
				doclist.add(new Doctor(name,number,treating,sl));
				docCount++;
			}
			input.close();
		} 
		catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	public Doctor retrieveDoc() {
        for (Doctor doc : doclist) {
            if(!doc.isAllotted()) {
                return doc;
            }
        }
        return null;
    }
	
	public Doctor retrieveDoc(String n) {
        for (Doctor doc : doclist) {
            if(!doc.isAllotted() && !doc.getName().equals(n)) {
                return doc;
            }
        }
        return null;
    }
	public void setAllotment(String n) {
		for (Doctor doc : doclist) {
            if(doc.getName().equals(n)) {
                doc.setAllotted(true);
                break;
            }
        }
	}
	public void resetAllotment() {
		for (Doctor doc : doclist) {
            doc.setAllotted(false);
        }
	}
	public List<Doctor> getDoclist() {
		return doclist;
	}
	public int getDocCount() {
		return docCount;
	}
	public void setDocCount(int docCount) {
		DoctorService.docCount = docCount;
	}
	 
}
