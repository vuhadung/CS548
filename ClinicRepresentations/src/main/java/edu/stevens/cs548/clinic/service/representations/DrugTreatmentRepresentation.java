package edu.stevens.cs548.clinic.service.representations;

import javax.xml.bind.annotation.XmlRegistry;

import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.web.rest.data.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.web.rest.data.ObjectFactory;

public class DrugTreatmentRepresentation extends DrugTreatmentType {

	public DrugTreatmentRepresentation() {
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}
	
	@Override
	public void setDosage(float value) {
		super.setDosage(value);
	}

	@XmlRegistry
	class ObjectFactoryDrugTreatment extends ObjectFactory {
		@Override
		public DrugTreatmentType createDrugTreatmentType() {
			return new DrugTreatmentRepresentation();
		}
	}
}
