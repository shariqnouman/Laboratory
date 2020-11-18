package com.cg.healthassist.laboratory.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long appointmentId;
	
	@Column(name = "appointment_date", nullable = false)
	private LocalDateTime appointmentDate;
	
	@Column(name = "appointment_description", nullable = false)
	private String appointmentDescription;
	
	@Column(name = "appointment_type", nullable = false)
	private String appointmentType;
	
	@ManyToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	public Appointment() {

	}

	public Appointment(LocalDateTime appointmentDate, String appointmentDescription,
			String appointmentType, Patient patient) {
		super();
		this.appointmentDate = appointmentDate;
		this.appointmentDescription = appointmentDescription;
		this.appointmentType = appointmentType;
		this.patient = patient;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentDescription() {
		return appointmentDescription;
	}

	public void setAppointmentDescription(String appointmentDescription) {
		this.appointmentDescription = appointmentDescription;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "\n Appointment Id = " + appointmentId + "\n Appointment Date = " + appointmentDate
				+ "\n Appointment Description = " + appointmentDescription + "\n Appointment Type = " + appointmentType
				+ patient;
	}
}
