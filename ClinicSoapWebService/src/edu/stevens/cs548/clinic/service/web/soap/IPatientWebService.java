package edu.stevens.cs548.clinic.service.web.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.*;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.TreatmentNotFoundExn;

//DungVH: @WebService name is used as the name of the wsdl:portType
@WebService(name = "IPatientWebPort", targetNamespace = "http://cs548.stevens.edu/clinic/service/web/soap/patient")
public interface IPatientWebService {

	@WebMethod(operationName = "addPatient")
	public long addPatient(PatientDto dto) throws PatientServiceExn;

	@WebMethod(operationName = "getPatient")
	public PatientDto getPatient(long id) throws PatientServiceExn;

	@WebMethod(operationName = "getPatientByPatientId")
	public PatientDto getPatientByPatId(long pid) throws PatientServiceExn;

	@WebMethod(operationName = "patientGetTreatment")
	public TreatmentDto getTreatment(long id, long tid)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn;

	@WebMethod
	public String siteInfo();

}
