package com.cg.healthassist.laboratory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** This is an entity class for patients with getters, setters,  and constructor
 * 
 * @author Sharique Nooman
 * @version 1.0
 *
 */
@Entity
@Table(name = "patients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "patient_id")
	@NotNull(message = "Please provide a valid id it must be a number")
	@Positive
	private Long patientId;

	@Column(name = "patient_name", nullable = false)
	@Pattern(regexp = "^[A-Za-z][A-Za-z ]+", message = "Please provide a valid name only alphabets and spaces")
	@NotBlank
	private String patientName;

	@Column(name = "patient_address", nullable = false)
	@Size(min = 4, message = "Please provide a valid address")
	@NotBlank
	private String patientAddress;

	@Column(name = "patient_email", nullable = false)
	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9-]+@[A-Za-z]+[.][A-Za-z]+$", message = "Please provide a valid email of format example@example.domain")
	@NotBlank
	private String patientEmail;

	@Column(name = "patient_number", nullable = false)
	@Pattern(regexp = "^[6-9][0-9]{9}", message = "Please provide a valid mobile number of 10 digits")
	@NotBlank
	private String patientNumber;

	public Patient() {

	}

	public Patient(Long patientId, String patientName, String patientAddress, String patientEmail, String patientNumber) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientAddress = patientAddress;
		this.patientEmail = patientEmail;
		this.patientNumber = patientNumber;
	}

	public Long getPatientId() {
		return patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

	@Override
	public String toString() {
		return "\n Patient Id = " + patientId + "\n Patient Name = " + patientName + "\n Patient Address = " + patientAddress
				+ "\n Patient Email = " + patientEmail + "\n Patient Mobile Number = " + patientNumber + "\n---------------------------------\n";
	}
}
