package edu.stevens.cs548.clinic.domain;

import java.util.*;

public class TreatmentFactory implements ITreatmentFactory {

	@Override
	public DrugTreatment createDrugTreatment(String diagnosis, String drug, float dosage) {
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDrug(drug);
		treatment.setDosage(dosage);
		treatment.setTreatmentType(TreatmentType.DRUG_TREATMENT.getTag());
		return treatment;
	}
	
	@Override
	public RadiologyTreatment createRadiology(String diagnosis, List<Date> dates)
	{
		RadiologyTreatment treatment = new RadiologyTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDate(dates);
		treatment.setTreatmentType(TreatmentType.RADIOLOGY.getTag());
		return treatment;
	}
	
	@Override 
	public SurgeryTreatment createSurgery(String diagnosis, Date date)
	{
		SurgeryTreatment treatment = new SurgeryTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDate(date);
		treatment.setTreatmentType(TreatmentType.SURGERY.getTag());
		return treatment;
	}

}
