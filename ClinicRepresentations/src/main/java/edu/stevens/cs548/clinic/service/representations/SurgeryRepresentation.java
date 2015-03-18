package edu.stevens.cs548.clinic.service.representations;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRegistry;

import edu.stevens.cs548.clinic.service.web.rest.data.ObjectFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.RadiologyType;
import edu.stevens.cs548.clinic.service.web.rest.data.SurgeryType;

public class SurgeryRepresentation extends SurgeryType {
	
	public SurgeryRepresentation() {
	}

	@Override
	public Date getDate() {
        return super.getDate();
    }

	@XmlRegistry
	class ObjectFactorySurgery extends ObjectFactory {
		@Override
		public SurgeryType createSurgeryType() {
			return new SurgeryRepresentation();
		}
	}
	
}
