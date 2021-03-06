package edu.stevens.cs548.clinic.domain;

import java.util.*;
import java.io.Serializable;

import javax.persistence.*;

import org.eclipse.persistence.annotations.PrivateOwned;

import edu.stevens.cs548.clinic.domain.Specialization;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

/**
 * Entity implementation class for Entity: Provider
 * @param <T>
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SearchProviderByNPI", query = "select p from Provider p where p.npi = :npi"),
		@NamedQuery(name = "SearchProviderByNameNPI", query = "select p from Provider p where p.name = :name and p.npi = :npi"),
		@NamedQuery(name = "SearchProviderByName", query = "select p from Provider p where p.name = :name") })
@Table(name = "PROVIDER")
public class Provider implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "npi")
	private long npi;

	@Column(name = "name")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "provider", orphanRemoval = true)
	@PrivateOwned
	@OrderBy
	private List<Treatment> treatments;

	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	public Provider() {
		super();
		treatments = new ArrayList<Treatment>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<Treatment> treatment) {
		this.treatments = treatment;
	}

	public long getNpi() {
		return npi;
	}

	public void setNpi(long npi) {
		this.npi = npi;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Transient
	private ITreatmentDAO treatmentDAO;

	public void setTreatmentDAO(ITreatmentDAO tdao) {
		this.treatmentDAO = tdao;
	}

	// DungVH

	public long addDrugTreatment(String diagnosis, String drug, float dosage,
			Patient patient) {
		TreatmentFactory trmtFactory = new TreatmentFactory();
		Treatment trmt = trmtFactory.createDrugTreatment(diagnosis, drug,
				dosage);
		trmt.setPatient(patient);
		trmt.setProvider(this);
		this.treatments.add(trmt);
		patient.addTreatment(trmt);
		return trmt.getId();
	}

	public long addSurgery(String diagnosis, Date date, Patient patient) {
		TreatmentFactory trmtFactory = new TreatmentFactory();
		Treatment trmt = trmtFactory.createSurgery(diagnosis, date);
		trmt.setPatient(patient);
		trmt.setProvider(this);
		this.treatments.add(trmt);
		patient.addTreatment(trmt);
		return trmt.getId();
	}

	public long addRadiology(String diagnosis, List<Date> dates, Patient patient) {
		TreatmentFactory trmtFactory = new TreatmentFactory();
		Treatment trmt = trmtFactory.createRadiology(diagnosis, dates);
		trmt.setPatient(patient);
		trmt.setProvider(this);
		this.treatments.add(trmt);
		patient.addTreatment(trmt);
		return trmt.getId();
	}

	// End

	// DungVH
	public <T> T exportTreatment(long tid, ITreatmentExporter<T> visitor)
			throws TreatmentExn {
		// export a treatment without violated Aggregate pattern
		// Check that the exported treatment is a treatment for this patient.
		Treatment t = this.treatmentDAO.getTreatment(tid);
		if (t.getProvider() != this) {
			throw new TreatmentExn("Inappropriate treatment access : provider"
					+ this.npi + " treatment = " + tid);
		}
		else
			return t.export(visitor);
	}

	
	@SuppressWarnings("rawtypes")
	public <T> List<T> exportTreatments(ITreatmentExporter<T> visitor) throws TreatmentExn {
		List<T> allTreatments = new ArrayList<T>();
		//System.out.println("DUNGVH: " + this.getTreatments().size() + " \n");
		for (Treatment t : this.getTreatments()) {
			//t = this.treatmentDAO.getTreatment(t.getId());
			allTreatments.add(t.export(visitor));
		}
		//System.out.println("DUNGVH: " + allTreatments.size() + " \n");
		return allTreatments;
	}

}
