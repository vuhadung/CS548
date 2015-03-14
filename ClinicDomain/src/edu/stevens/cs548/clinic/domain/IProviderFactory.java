package edu.stevens.cs548.clinic.domain;

public interface IProviderFactory {
	public Provider createProviderWithSpec(long npi, String name, Specialization specialization) throws IProviderDAO.ProviderExn;
	public Provider createProvider(long npi, String name) throws IProviderDAO.ProviderExn;
}
