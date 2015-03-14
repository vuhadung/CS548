package edu.stevens.cs548.clinic.service.dto.util;

import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.Treatment;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;

public class ProviderDtoFactory {
	ObjectFactory factory;

	public ProviderDtoFactory() {
		factory = new ObjectFactory();
	}

	public ProviderDto createProviderDto() {
		return factory.createProviderDto();
	}

	public ProviderDto createProviderDto(long npi, String name) {
		ProviderDto d = factory.createProviderDto();
		d.setName(name);
		d.setNpi(npi);
		return d;
	}

	public ProviderDto retrieveProviderDto(Provider p) {
		ProviderDto d = factory.createProviderDto();
		d.setProviderId(p.getId());
		d.setName(p.getName());
		d.setNpi(p.getNpi());
		if (p.getSpecialization() != null)
			d.setProviderType(p.getSpecialization().toString());
		for (Treatment trmt : p.getTreatments()) {
			d.getTreatments().add(trmt.getId());
		}
		return d;
	}

}
