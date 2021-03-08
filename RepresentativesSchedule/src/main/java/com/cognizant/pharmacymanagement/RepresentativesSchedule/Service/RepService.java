package com.cognizant.pharmacymanagement.RepresentativesSchedule.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RepService {
	private static List<String> replist=new ArrayList<String>();
	private static int repMark=0;
	private static int repCount;
	static {
		replist.add(new String("R1"));
		replist.add(new String("R2"));
		replist.add(new String("R3"));
		repCount=3;
	}
	public void initialMark() {
		repMark=0;
	}
	public String retrieveRep() {
		int ind=repMark;
		if(++repMark==repCount) {
			repMark=0;
		}
        return replist.get(ind);
    }
}
