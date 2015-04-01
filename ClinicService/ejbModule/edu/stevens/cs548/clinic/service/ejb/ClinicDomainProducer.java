package edu.stevens.cs548.clinic.service.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class PatientProducer
 */
@Stateless
@LocalBean
public class ClinicDomainProducer {

	/**
	 * Default constructor.
	 */
	public ClinicDomainProducer() {
	}

	// @Qualifier
	// @Retention(RetentionPolicy.RUNTIME)
	// @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,
	// ElementType.TYPE})
	// public @interface ClinicDomain {}

	@PersistenceContext(unitName = "ClinicDomain")
	private EntityManager em;

	@Produces
	@ClinicDomain
	public EntityManager clinicDomainProducer() {
		return em;
	}

	// DungVH
	public void clinicDomainDispose(@Disposes @ClinicDomain EntityManager em) {
		/*
		 * if(em.isOpen()) { em.close(); }
		 */
	}

}
