package com.cg.healthassist.laboratory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "patient_id")
	private Long patientId;
	
	@Column(name = "patient_name", nullable = false)
	private String patientName;
	
	@Column(name = "patient_address", nullable = false)
	private String patientAddress;
	
	@Column(name = "patient_email", nullable = false)
	private String patientEmail;
	
	@Column(name = "patient_number", nullable = false)
	private String patientNumber;
	
	public Patient() {

	}

	public Patient(long patientId, String patientName, String patientAddress, String patientEmail, String patientNumber) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientAddress = patientAddress;
		this.patientEmail = patientEmail;
		this.patientNumber = patientNumber;
	}

	public long getPatientId() {
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
