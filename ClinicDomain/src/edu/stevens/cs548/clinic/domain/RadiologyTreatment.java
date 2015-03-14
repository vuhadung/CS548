package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.Treatment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("RA")
public class RadiologyTreatment extends Treatment implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Date> dates;

	public RadiologyTreatment() {
		super();
		this.setTreatmentType("RA");
	}

	public List<Date> getDate() {
		return dates;
	}

	public void setDate(List<Date> dates) {
		this.dates = dates;
	}
	
	//DungVH
	@SuppressWarnings("unchecked")
	public <T> T export(ITreatmentExporter<T> visitor) {
		System.out.println("Enter this");
		return (T) visitor.exportRadiology(this.getId(), 
								   		   this.getDiagnosis(),
								   		   this.dates);
	}

}