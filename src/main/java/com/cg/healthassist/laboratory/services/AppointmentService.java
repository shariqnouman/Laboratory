package com.cg.healthassist.laboratory.services;

import java.util.List;

import com.cg.healthassist.laboratory.model.Appointment;

/** This is an interface for AppointmentService class, it has all the abstract methods
 * 
 * @author Sharique Nooman
 *
 */
public interface AppointmentService {

	/** This method returns list of all appointments 
	 * 
	 * @return list of all appointments
	 * 
	 * */
	List<Appointment> viewAllAppointments();

	/** This method takes appointment details and books an appointment for patient
	 * 
	 * @param appointment - appointment entity details
	 * 
	 * */
	Appointment bookAppointment(Appointment appointment);

	/** This method takes appointment Id and deletes the appointment
	 * 
	 * @param appointmentId - long value to remove appointment based on Id
	 * 
	 * */
	void removeAppointment(Long appointmentId);

	/** This method returns appointments by searching with specific appointment Id
	 * 
	 * @param appointmentId - long value to view appointment based on Id
	 * @return appointment details if found or else throws exception
	 * 
	 * */
	Appointment viewAppointmentById(Long appointmentId);

	/** This method updates the appointment details by searching with id
	 * 
	 * @param appointmentId - long value to update appointment based on Id
	 * 
	 * */
	Appointment updateAppointment(Long appointmentId, Appointment appointment);

}