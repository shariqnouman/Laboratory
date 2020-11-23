package com.cg.healthassist.laboratory.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassist.laboratory.model.Appointment;
import com.cg.healthassist.laboratory.services.AppointmentService;

/** The AppointmentController class provides restful services to client like
 *  GET, POST, DELETE, and PUT
 * 
 * @author Sharique Nooman
 *
 */
@RestController
@Validated
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	/** GET method for viewing all appointments
	 * 
	 * @return list of all appointments
	 */
	@GetMapping
	public List<Appointment> viewAllAppointments() {
		return appointmentService.viewAllAppointments();
	}

	/** POST method to book appointment
	 * 
	 * @param appointment
	 * @return Appointment object
	 */
	@PostMapping
	public Appointment bookAppointment(@Valid @RequestBody Appointment appointment) {
		return appointmentService.bookAppointment(appointment);
	}

	/** DELETE method to remove appointment
	 * 
	 * @param appointmentId
	 */
	@DeleteMapping("{appointmentId}")
	public void removeAppointment(@PathVariable @Positive Long appointmentId) {
		appointmentService.removeAppointment(appointmentId);
	}

	/** GET method to view appointment by Id
	 * 
	 * @param appointmentId
	 * @return Appointment object
	 */
	@GetMapping("{appointmentId}")
	public ResponseEntity<Appointment> viewAppointmentById(@PathVariable @Positive Long appointmentId) {
		Appointment appointment = appointmentService.viewAppointmentById(appointmentId);
		return ResponseEntity.ok().body(appointment);
	}

	/** PUT method to update appointment
	 * 
	 * @param appointmentId
	 * @param appointment
	 * @return Appointment object
	 */
	@PutMapping("{appointmentId}")
	public Appointment updateAppointment(@PathVariable @Positive Long appointmentId, @Valid @RequestBody Appointment appointment) {
		return appointmentService.updateAppointment(appointmentId, appointment);
	}
}
