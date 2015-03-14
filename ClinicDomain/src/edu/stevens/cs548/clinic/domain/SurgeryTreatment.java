package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.Treatment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SU")
public class SurgeryTreatment extends Treatment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date date;

	public SurgeryTreatment() {
		super();
		this.setTreatmentType("SU");
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T export(ITreatmentExporter<T> visitor) {
		return (T) visitor.exportSurgery(this.getId(), 
								   		   this.getDiagnosis(),
								   		   this.date);
	}
}