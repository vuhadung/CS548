package edu.stevens.cs548.clinic.service.web.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.service.dto.*;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceLocal;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;
import edu.stevens.cs548.clinic.service.web.soap.*;

@WebService(endpointInterface
		="edu.stevens.cs548.clinic.service.web.soap.IProviderWebService")


public class ProviderWebService implements IProviderWebService {

	@EJB(beanName = "ProviderServiceBean")
	IProviderServiceLocal service;

	@Override
	public long addProvider(ProviderDto dto) throws ProviderServiceExn {
		return service.addProvider(dto);
	}

	@Override
	public ProviderDto getProvider(long id) throws ProviderServiceExn {
		return service.getProvider(id);
	}

	@Override
	public ProviderDto getProviderByNpi(long npi) throws ProviderServiceExn {
		return service.getProviderByNpi(npi);
	}

	@Override
	public void addTreatment(long patientId, long providerNPI,
			TreatmentDto TreatmentDto) throws ProviderServiceExn,
			PatientServiceExn {
		service.addTreatment(patientId, providerNPI, TreatmentDto);
	}

	@Override
	public String siteInfo() {
		return service.siteInfo();
	}

}
