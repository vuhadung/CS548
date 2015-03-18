package edu.stevens.cs548.clinic.service.dto.util;

import java.util.*;

import edu.stevens.cs548.clinic.domain.DrugTreatment;
import edu.stevens.cs548.clinic.domain.RadiologyTreatment;
import edu.stevens.cs548.clinic.domain.SurgeryTreatment;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public class TreatmentDtoFactory {

	ObjectFactory factory;

	public TreatmentDtoFactory() {
		factory = new ObjectFactory();
	}

	public TreatmentDto createTreatmentDto() {
		return factory.createTreatmentDto();
	}

	
	//For assignment 6: 
	public TreatmentDto createDrugTreatmentDto(DrugTreatment t) {
		TreatmentDto dto = factory.createTreatmentDto();
		//ERROR here
		//dto.setId(t.getId());
		//dto.setPatient(t.getPatient().getId());
		//dto.setProvider(t.getProvider().getNpi());
		dto.setDiagnosis(t.getDiagnosis());
		DrugTreatmentType d = factory.createDrugTreatmentType();
		d.setName(t.getDrug());
		d.setDosage(t.getDosage());
		dto.setDrugTreatment(d);
		return dto;
	}

	public TreatmentDto createRadiologyDto(RadiologyTreatment t) {
		TreatmentDto dto = factory.createTreatmentDto();
		//dto.setId(t.getId());
		//dto.setPatient(t.getPatient().getId());
		//dto.setProvider(t.getProvider().getNpi());
		dto.setDiagnosis(t.getDiagnosis());
		RadiologyType r = factory.createRadiologyType();
		for (Date e : t.getDate()) {
			r.getDate().add(e);
		}
		dto.setRadiology(r);
		return dto;
	}

	public TreatmentDto createSurgeryDto(SurgeryTreatment t) {
		TreatmentDto dto = factory.createTreatmentDto();
		//dto.setId(t.getId());
		//dto.setPatient(t.getPatient().getId());
		//dto.setProvider(t.getProvider().getNpi());
		dto.setDiagnosis(t.getDiagnosis());
		SurgeryType s = factory.createSurgeryType();
		s.setDate(t.getDate());
		dto.setSurgery(s);
		return dto;
	}
	
	
	//For assignment 7:
	public TreatmentDto createNewDrugTreatmentDto(String drug, float dosage) {
		TreatmentDto dto = factory.createTreatmentDto();
		DrugTreatmentType d = factory.createDrugTreatmentType();
		d.setName(drug);
		d.setDosage(dosage);
		dto.setDrugTreatment(d);
		return dto;
	}

	public TreatmentDto createNewRadiologyDto(List<Date> dates) {
		TreatmentDto dto = factory.createTreatmentDto();
		RadiologyType r = factory.createRadiologyType();
		for (Date e : dates) {
			r.getDate().add(e);
		}
		dto.setRadiology(r);
		return dto;
	}

	public TreatmentDto createNewSurgeryDto(Date date) {
		TreatmentDto dto = factory.createTreatmentDto();
		SurgeryType s = factory.createSurgeryType();
		s.setDate(date);
		dto.setSurgery(s);
		return dto;
	}

}
