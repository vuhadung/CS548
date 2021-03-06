package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public class TreatmentExporter implements ITreatmentExporter<Treatment> {

	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> T exportDrugTreatment(long tid, String diagnosis, String drug,
			float dosage) {
		DrugTreatment trmt = new DrugTreatment();
		trmt.setId(tid);
		trmt.setDiagnosis(diagnosis);
		trmt.setDrug(drug);
		trmt.setDosage(dosage);
		return (T) trmt;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> T exportRadiology(long tid, String diagnosis, List<Date> dates) {
		RadiologyTreatment trmt = new RadiologyTreatment();
		trmt.setId(tid);
		trmt.setDiagnosis(diagnosis);
		trmt.setDate(dates);
		return (T) trmt;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> T exportSurgery(long tid, String diagnosis, Date date) {
		SurgeryTreatment trmt = new SurgeryTreatment();
		trmt.setId(tid);
		trmt.setDiagnosis(diagnosis);
		trmt.setDate(date);
		return (T) trmt;
	}

}
