package edu.stevens.cs548.clinic.service.ejb;

import java.util.*;
import java.util.logging.Logger;

import javax.annotation.*;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;

import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.*;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.service.dto.*;
import edu.stevens.cs548.clinic.service.dto.util.*;
import edu.stevens.cs548.clinic.service.ejb.ClinicDomain;

/**
 * Session Bean implementation class PatientService
 */
@Stateless(name = "PatientServiceBean")
public class PatientService implements IPatientServiceLocal,
		IPatientServiceRemote {

	private Logger logger = Logger.getLogger(PatientService.class
			.getCanonicalName());

	private IPatientFactory patientFactory;

	private PatientDtoFactory patientDtoFactory;

	private IPatientDAO patientDAO;

	private ITreatmentDAO treatmentDAO;

	public PatientService() {
		patientFactory = new PatientFactory();
		patientDtoFactory = new PatientDtoFactory();
	}

	@PersistenceContext(unitName = "ClinicDomain")
	//@Inject @ClinicDomain
	EntityManager em;

	@PostConstruct
	private void initialize() {
		patientDAO = new PatientDAO(em);
	}

	/**
	 * @see IPatientService#addPatient(String, Date, long)
	 */
	@Override
	public long addPatient(PatientDto dto) throws PatientServiceExn {
		// Use factory to create patient entity, and persist with DAO
		try {
			Patient patient = patientFactory.createPatient(dto.getPatientId(),
					dto.getName(), dto.getDob(), dto.getAge());
			patientDAO.addPatient(patient);
			// logger.info("DUNGVH1: " + patient.getId());
			return patient.getId();
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	/**
	 * @see IPatientService#getPatient(long)
	 */
	@Override
	public PatientDto getPatient(long id) throws PatientServiceExn {
		try {
			Patient p = patientDAO.getPatient(id);
			return patientDtoFactory.retrievePatientDto(p);
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	/**
	 * @see IPatientService#getPatientByPatId(long)
	 */
	@Override
	public PatientDto getPatientByPatId(long pid) throws PatientServiceExn {
		try {
			Patient p = patientDAO.getPatientByPatientId(pid);
			return patientDtoFactory.retrievePatientDto(p);
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	public class TreatmentDtoExporter implements
			ITreatmentExporter<TreatmentDto> {
		private ObjectFactory factory = new ObjectFactory();

		@Override
		public <T> T exportDrugTreatment(long tid, String diagnosis,
				String drug, float dosage) {
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			dto.setId(tid);
			DrugTreatmentType drugInfo = factory.createDrugTreatmentType();
			drugInfo.setDosage(dosage);
			drugInfo.setName(drug);
			dto.setDrugTreatment(drugInfo);
			return (T) dto;
		}

		@Override
		public <T> T exportRadiology(long tid, String diagnosis,
				List<Date> dates) {
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			dto.setId(tid);
			RadiologyType radiologyInfo = factory.createRadiologyType();
			for (Date i : dates) {
				radiologyInfo.getDate().add(i);
			}
			dto.setRadiology(radiologyInfo);
			return (T) dto;
		}

		@Override
		public <T> T exportSurgery(long tid, String diagnosis, Date date) {
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			dto.setId(tid);
			SurgeryType surgeryInfo = factory.createSurgeryType();
			surgeryInfo.setDate(date);
			dto.setSurgery(surgeryInfo);
			return (T) dto;
		}

	}

	@Override
	public TreatmentDto getTreatment(long id, long tid)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn {
		// Export treatment DTO from patient aggregate
		try {
			Patient patient = patientDAO.getPatientByPatientId(id);
			TreatmentDtoExporter visitor = new TreatmentDtoExporter();
			return patient.exportTreatment(tid, visitor);
		} catch (PatientExn e) {
			throw new PatientNotFoundExn(e.toString());
		} catch (TreatmentExn e) {
			throw new TreatmentNotFoundExn(e.toString());
		}
	}

	@Resource(name = "SiteInfo")
	private String siteInformation;

	@Override
	public String siteInfo() {
		return siteInformation;
	}

}
