package edu.stevens.cs548.clinic.domain;

import java.util.logging.Logger;

import javax.persistence.*;

import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;

import java.util.*;


public class ProviderDAO implements IProviderDAO {

	private EntityManager em;
	private TreatmentDAO treatmentDAO;
	
	public ProviderDAO(EntityManager em) {
		this.em = em;
		this.treatmentDAO = new TreatmentDAO(em);
	}

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(ProviderDAO.class.getCanonicalName());

	@Override
	public Provider getProviderByNPI(long npi) throws ProviderExn {
		TypedQuery<Provider> query = em.createNamedQuery(
				"SearchProviderByNPI", Provider.class).setParameter("npi", npi);
		List<Provider> providers = query.getResultList();
		if (providers.size() > 1)
			throw new ProviderExn("Duplicate provider records : provider npi = " + npi);
		else if (providers.size() < 1)
			throw new ProviderExn("Provider not found : provider npi = " + npi);
		else {
			Provider prov = providers.get(0);
			prov.setTreatmentDAO(this.treatmentDAO);
			return prov;
		}
	}

	@Override
	public long addProvider(Provider prov) throws ProviderExn {
		long npi = prov.getNpi();
		TypedQuery<Provider> query = em
				.createNamedQuery("SearchProviderByNPI", Provider.class)
				.setParameter("npi", npi);
		List<Provider> providers = query.getResultList();
		if (providers.size() < 1) {
			em.persist(prov);
			prov.setTreatmentDAO(this.treatmentDAO);
			em.flush(); //DungVH
			return npi;
		} 
		else if (providers.size() > 1) {
			throw new ProviderExn("Insertion: Provider with NPI (" + npi
					+ ") already exists.");
		}
		else 
			throw new IllegalStateException("Unimplemented");
	}

	@Override
	public void deleteProviders() {
		//Query update = em.createQuery("RemoveAllProviders");
		//update.executeUpdate();
		em.createQuery("delete from Provider").executeUpdate();
	}

	@Override
	public Provider getProvider(long id) throws ProviderExn {
		Provider prov = em.find(Provider.class, id);
		if (prov == null) {
			throw new ProviderExn("Provider not found : primary key =" + id);
		} else {
			prov.setTreatmentDAO(this.treatmentDAO);
			return prov;
		}	
	}

}