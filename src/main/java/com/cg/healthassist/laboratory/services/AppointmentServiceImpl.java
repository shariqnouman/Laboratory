package com.cg.healthassist.laboratory.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.healthassist.laboratory.exceptions.AppointmentNotFoundException;
import com.cg.healthassist.laboratory.model.Appointment;
import com.cg.healthassist.laboratory.repositories.AppointmentsRepository;

/** The AppointmentService class provides access to JPA methods to book,
 *  remove, view, and update appointments of patients
 *  
 * @author Sharique Nooman
 * @version 1.0
 * 
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentsRepository appointmentsRepository;

	/** This method returns list of all appointments 
	 * 
	 * @return list of all appointments
	 * 
	 * */
	@Override
	public List<Appointment> viewAllAppointments() {
		return appointmentsRepository.findAll();
	}

	/** This method takes appointment details and books an appointment for patient
	 * 
	 * @param appointment - appointment entity details
	 * 
	 * */
	@Override
	public Appointment bookAppointment(Appointment appointment) {
		return appointmentsRepository.saveAndFlush(appointment);
	}

	/** This method takes appointment Id and deletes the appointment
	 * 
	 * @param appointmentId - long value to remove appointment based on Id
	 * 
	 * */
	@Override
	public void removeAppointment(Long appointmentId) {
		appointmentsRepository.deleteById(appointmentId);
	}

	/** This method returns appointments by searching with specific appointment Id
	 * 
	 * @param appointmentId - long value to view appointment based on Id
	 * @return appointment details if found or else throws exception
	 * 
	 * */
	@Override
	public Appointment viewAppointmentById(Long appointmentId) {
		return appointmentsRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for this id : " + appointmentId));
	}

	/** This method updates the appointment details by searching with id
	 * 
	 * @param appointmentId - long value to update appointment based on Id
	 * 
	 * */
	@Override
	public Appointment updateAppointment(Long appointmentId, Appointment appointment) {
		Appointment existingAppointment = appointmentsRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for this id : " + appointmentId));
		BeanUtils.copyProperties(appointment, existingAppointment,"appointmentId");
		return appointmentsRepository.saveAndFlush(existingAppointment);
	}
}
