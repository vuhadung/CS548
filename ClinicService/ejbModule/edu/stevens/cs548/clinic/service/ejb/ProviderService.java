package edu.stevens.cs548.clinic.service.ejb;

import java.util.logging.Logger;

import javax.annotation.*;
import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.*;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.service.dto.*;
import edu.stevens.cs548.clinic.service.dto.util.*;
import edu.stevens.cs548.clinic.service.ejb.ClinicDomain;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;

@Stateless(name = "ProviderServiceBean")
public class ProviderService implements IProviderServiceLocal,
		IProviderServiceRemote {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(ProviderService.class
			.getCanonicalName());

	private IProviderFactory providerFactory;

	private ProviderDtoFactory providerDtoFactory;

	private IProviderDAO providerDAO;

	private IPatientDAO patientDAO;

	public ProviderService() {
		providerFactory = new ProviderFactory();
		providerDtoFactory = new ProviderDtoFactory();
	}

	@PersistenceContext(unitName = "ClinicDomain")
	//@Inject @ClinicDomain 
	EntityManager em;

	@PostConstruct
	private void initialize() {
		providerDAO = new ProviderDAO(em);
		patientDAO = new PatientDAO(em);
	}

	@Override
	public long addProvider(ProviderDto dto) throws ProviderServiceExn {
		try {
			Provider provider = providerFactory.createProvider(dto.getNpi(),
					dto.getName());
			providerDAO.addProvider(provider);
			// logger.info("DUNGVH1: " + provider.getId());
			return provider.getId();
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
	}

	@Override
	public ProviderDto getProvider(long id) throws ProviderServiceExn {
		try {
			Provider p = providerDAO.getProvider(id);
			return providerDtoFactory.retrieveProviderDto(p);
		} catch (ProviderExn e) {
			//logger.info("DUNGVH: Exn 1");
			throw new ProviderServiceExn(e.toString());
		}
	}

	@Override
	public ProviderDto getProviderByNpi(long npi) throws ProviderServiceExn {
		try {
			Provider p = providerDAO.getProviderByNPI(npi);
			return providerDtoFactory.retrieveProviderDto(p);
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
	}

	@Override
	public void addTreatment(long patientId, long providerNPI,
			TreatmentDto TreatmentDto) throws ProviderServiceExn,
			PatientServiceExn {
		if ((TreatmentDto.getDrugTreatment()) != null) {
			this.addDrugTreatment(patientId, providerNPI, TreatmentDto);
		} else if ((TreatmentDto.getRadiology()) != null) {
			this.addRadiology(patientId, providerNPI, TreatmentDto);
		} else if ((TreatmentDto.getSurgery()) != null) {
			this.addSurgery(patientId, providerNPI, TreatmentDto);
		}
	}

	// DungVH
	private void addSurgery(long patientId, long providerNPI,
			TreatmentDto treatmentDto) throws PatientNotFoundExn,
			ProviderNotFoundExn {
		try {
			Patient patient = patientDAO.getPatient(patientId);
			Provider provider = providerDAO.getProvider(providerNPI);
			provider.addSurgery(treatmentDto.getDiagnosis(), treatmentDto
					.getSurgery().getDate(), patient);
		} catch (PatientExn e) {
			throw new PatientNotFoundExn(e.toString());
		} catch (ProviderExn e) {
			throw new ProviderNotFoundExn(e.toString());
		}

	}

	private void addRadiology(long patientId, long providerNPI,
			TreatmentDto treatmentDto) throws PatientNotFoundExn,
			ProviderNotFoundExn {
		try {
			Patient patient = patientDAO.getPatient(patientId);
			Provider provider = providerDAO.getProvider(providerNPI);
			provider.addRadiology(treatmentDto.getDiagnosis(), treatmentDto
					.getRadiology().getDate(), patient);
		} catch (PatientExn e) {
			throw new PatientNotFoundExn(e.toString());
		} catch (ProviderExn e) {
			throw new ProviderNotFoundExn(e.toString());
		}
	}

	private void addDrugTreatment(long patientId, long providerNPI,
			TreatmentDto treatmentDto) throws PatientNotFoundExn,
			ProviderNotFoundExn {
		try {
			Patient patient = patientDAO.getPatient(patientId);
			Provider provider = providerDAO.getProvider(providerNPI);
			provider.addDrugTreatment(treatmentDto.getDiagnosis(), treatmentDto
					.getDrugTreatment().getName(), treatmentDto
					.getDrugTreatment().getDosage(), patient);
		} catch (PatientExn e) {
			throw new PatientNotFoundExn(e.toString());
		} catch (ProviderExn e) {
			throw new ProviderNotFoundExn(e.toString());
		}

	}

	// End

	@Resource(name = "SiteInfo")
	private String siteInformation;

	@Override
	public String siteInfo() {
		return siteInformation;
	}

}
