package edu.stevens.cs548.clinic.service.representations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRegistry;

import edu.stevens.cs548.clinic.service.web.rest.data.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.web.rest.data.ObjectFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.RadiologyType;

public class RadiologyRepresentation extends RadiologyType {
	
	public RadiologyRepresentation() {
	}

	@Override
	public List<Date> getDate() {
        return super.getDate();
    }

	@XmlRegistry
	class ObjectFactoryRadiology extends ObjectFactory {
		@Override
		public RadiologyType createRadiologyType() {
			return new RadiologyRepresentation();
		}
	}


}
