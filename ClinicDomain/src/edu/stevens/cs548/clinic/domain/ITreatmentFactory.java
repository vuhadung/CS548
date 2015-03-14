package edu.stevens.cs548.clinic.domain;

import java.util.*;

public interface ITreatmentFactory {
	
	public Treatment createDrugTreatment (String diagnosis, String drug, float dosage);
	
	public Treatment createRadiology (String diagnosis, List<Date> dates);
	
	public Treatment createSurgery (String diagnosis, Date date);

}
