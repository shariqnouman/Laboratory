package com.cg.healthassist.laboratory.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.healthassist.laboratory.model.Appointment;
import com.cg.healthassist.laboratory.model.Patient;
import com.cg.healthassist.laboratory.repositories.AppointmentsRepository;

/** The AppointmentServiceImplTest class has test methods for the appointment service layer
 * 
 * @author Sharique Nooman
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppointmentServiceImpl.class)
public class AppointmentServiceImplTest {

	@MockBean
	private AppointmentsRepository appointmentsRepository;

	@Autowired
	private AppointmentService appointmentService;

	@Test
	public void testViewAllAppointments() {
		Patient patient = new Patient(105L, "Nikhilwa", "Bangalore", "nikhilwa@gmail.com", "9738222973");
		Appointment appointment = new Appointment(LocalDateTime.now(), "appointment for diabetes", "blood test", patient);
		Appointment appointment1 = new Appointment(LocalDateTime.now(), "appointment for fracture", "scan", patient);
		Appointment appointment2 = new Appointment(LocalDateTime.now(), "appointment for wrist scan", "scan", patient);

		List<Appointment> appointments = new ArrayList<>();
		appointments.add(appointment);
		appointments.add(appointment1);
		appointments.add(appointment2);

		Mockito.when(appointmentsRepository.findAll()).thenReturn(appointments);
		assertThat(appointmentService.viewAllAppointments()).isEqualTo(appointments);
		assertEquals(3, appointmentService.viewAllAppointments().size());

		verify(appointmentsRepository, times(2)).findAll();
	}

	@Test
	public void testBookAppointment() {
		Patient patient = new Patient(101L, "Shariq", "Bangalore", "shariq@gmail.com", "9901738247");
		Appointment appointment = new Appointment(LocalDateTime.now(), "appointment for diabetes", "blood test", patient);

		Mockito.when(appointmentsRepository.saveAndFlush(appointment)).thenReturn(appointment);
		assertThat(appointmentService.bookAppointment(appointment)).isEqualTo(appointment);

		verify(appointmentsRepository).saveAndFlush(appointment);
	}

	@Test
	public void testRemoveAppointment() {
		Patient patient = new Patient(102L, "Adil", "Bangalore", "adil@gmail.com", "933725656");
		Appointment appointment = new Appointment(LocalDateTime.now(), "appointment for fracture", "scan", patient);
		appointment.setAppointmentId(10L);

		appointmentService.removeAppointment(appointment.getAppointmentId());
		verify(appointmentsRepository, times(1)).deleteById(appointment.getAppointmentId());
	}

	@Test
	public void testViewAppointmentById() {
		Patient patient = new Patient(101L, "Shariq", "Bangalore", "shariq@gmail.com", "9901738247");
		Appointment appointment = new Appointment(LocalDateTime.now(), "appointment for fracture", "scan", patient);
		appointment.setAppointmentId(20L);

		Mockito.when(appointmentsRepository.findById(appointment.getAppointmentId())).thenReturn(Optional.of(appointment));
		assertThat(appointmentService.viewAppointmentById(20L)).isEqualTo(appointment);

		verify(appointmentsRepository).findById(appointment.getAppointmentId());
	}

	@Test
	public void testUpdateAppointment() {
		Patient patient = new Patient(103L, "Nikhilwa", "Bangalore", "nikhilwa@gmail.com", "9738222973");
		Appointment appointment = new Appointment(LocalDateTime.now(), "appointment for diabetes", "blood test", patient);
		appointment.setAppointmentId(30L);

		Appointment updatedAppointment = appointment;
		updatedAppointment.setAppointmentId(30L);
		updatedAppointment.setAppointmentDate(LocalDateTime.now());
		updatedAppointment.setAppointmentDescription("appointment for fracture");
		updatedAppointment.setAppointmentType("scan");
		updatedAppointment.setPatient(patient);

		Mockito.when(appointmentsRepository.saveAndFlush(updatedAppointment)).thenReturn(updatedAppointment);
		Mockito.when(appointmentsRepository.findById(appointment.getAppointmentId())).thenReturn(Optional.of(appointment));
		appointmentService.updateAppointment(appointment.getAppointmentId(), updatedAppointment);

		assertThat(appointment.getAppointmentDescription()).isEqualTo(updatedAppointment.getAppointmentDescription());
		verify(appointmentsRepository).saveAndFlush(updatedAppointment);
	}

}
