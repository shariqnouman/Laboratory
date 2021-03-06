package com.cg.healthassist.laboratory.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.healthassist.laboratory.model.Appointment;
import com.cg.healthassist.laboratory.model.Patient;

/** The AppointmentsJpaRepositoryTest class has test methods for
 *  methods in AppointmentsJpaRepository class
 * 
 * @author Sharique Nooman
 * @version 1.0
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class AppointmentsRepositoryTest {

	@Autowired
	private AppointmentsRepository appointmentsJpaRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void testSaveAndFlush() {
		Patient patient = new Patient(101L, "Shariq", "Bangalore", "shariq@gmail.com", "9901738247");
		Appointment appointment = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for diabetes", "blood test", patient);

		Long appointmentId = testEntityManager.persistAndGetId(appointment, Long.class);
		Appointment expectedAppointment = appointmentsJpaRepository.getOne(appointment.getAppointmentId());

		assertNotNull(appointmentId);
		assertNotNull(appointment);
		assertEquals(expectedAppointment.getAppointmentDate(), appointment.getAppointmentDate());
	}

	@Test
	public void testGetOne() {
		Patient patient = new Patient(101L, "Shariq", "Bangalore", "shariq@gmail.com", "9901738247");
		Appointment appointment = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for fracture", "scan", patient);

		Appointment expectedAppointment = testEntityManager.persist(appointment);
		Appointment actualAppointment = appointmentsJpaRepository.getOne(appointment.getAppointmentId());

		assertNotNull(expectedAppointment);
		assertNotNull(actualAppointment);
		assertThat(expectedAppointment).isEqualTo(actualAppointment);
	}

	@Test
	public void testDeleteById() {
		Patient patient = new Patient(102L, "Adil", "Bangalore", "adil@gmail.com", "9337256565");
		Appointment appointment = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for fracture", "scan", patient);
		Appointment appointment1 = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for diabetes", "blood test", patient);

		testEntityManager.persist(appointment1);
		Appointment expectedAppointment = testEntityManager.persist(appointment);
		testEntityManager.remove(expectedAppointment);
		List<Appointment> appointments = appointmentsJpaRepository.findAll();

		assertNotNull(expectedAppointment);
		assertEquals(1, appointments.size());
	}

	@Test
	public void testFindAll() {
		Patient patient = new Patient(105L, "Nikhilwa", "Bangalore", "nikhilwa@gmail.com", "9738222973");
		Appointment appointment = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for diabetes", "blood test", patient);
		Appointment appointment1 = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for fracture", "scan", patient);
		Appointment appointment2 = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for wrist scan", "scan", patient);

		testEntityManager.persist(appointment);
		testEntityManager.persist(appointment1);
		testEntityManager.persist(appointment2);

		List<Appointment> appointments = appointmentsJpaRepository.findAll();
		assertEquals(3, appointments.size());
	}

}
