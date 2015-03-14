package edu.stevens.cs548.clinic.domain;

public class ProviderFactory implements IProviderFactory {

	@Override
	public Provider createProviderWithSpec(long npi, String name,
			Specialization specialization) {
		Provider p = new Provider();
		p.setNpi(npi);
		p.setName(name);
		p.setSpecialization(specialization);
		return p;
	}

	@Override
	public Provider createProvider(long npi, String name) {
		Provider p = new Provider();
		p.setNpi(npi);
		p.setName(name);
		return p;
	}

}
