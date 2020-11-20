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
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** This is an entity class for appointments with getters, setters,  and constructor
 * 
 * @author Sharique Nooman
 * @version 1.0
 *
 */
@Entity
@Table(name = "appointments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long appointmentId;

	@Column(name = "appointment_date", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@FutureOrPresent(message = "Please provide a valid date in format yyyy-MM-dd HH:mm and it should not be a past date")
	@NotNull
	private LocalDateTime appointmentDate;

	@Column(name = "appointment_description", nullable = false)
	@NotBlank
	@Size(min = 4, message = "Please provide a valid appointment description having min 4 characters")
	private String appointmentDescription;

	@Column(name = "appointment_type", nullable = false)
	@NotBlank
	@Size(min = 4, message = "Please provide a valid appointment type having min 4 characters")
	private String appointmentType;

	@ManyToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	@NotNull(message = "Please provide patient details")
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

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
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
