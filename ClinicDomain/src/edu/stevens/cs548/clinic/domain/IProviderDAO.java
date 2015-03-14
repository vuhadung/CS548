package edu.stevens.cs548.clinic.domain;

import java.util.*;

public interface IProviderDAO {
	public static class ProviderExn extends Exception {
		private static final long serialVersionUID = 1L;
		public ProviderExn (String msg) {
			super(msg);
		}
	}
	
	//DungVH
    public Provider getProviderByNPI(long npi) throws ProviderExn;
	
    public Provider getProvider (long id) throws ProviderExn;
    
	public long addProvider(Provider prov) throws ProviderExn;
	
	public void deleteProviders();

}
