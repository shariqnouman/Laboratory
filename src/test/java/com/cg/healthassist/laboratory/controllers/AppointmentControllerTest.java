package com.cg.healthassist.laboratory.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.healthassist.laboratory.model.Appointment;
import com.cg.healthassist.laboratory.model.Patient;
import com.cg.healthassist.laboratory.services.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

/** The AppointmentControllerTest class has test methods for the appointment controller layer
 * 
 * @author Sharique Nooman
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AppointmentService appointmentService;

	@Test
	public void testViewAllAppointments() throws Exception {
		String URI = "/api/v1/appointment";
		Patient patient = new Patient(105L, "Nikhilwa", "Bangalore", "nikhilwa@gmail.com", "9738222973");
		Appointment appointment = new Appointment(LocalDateTime.now(), "appointment for diabetes", "blood test", patient);
		Appointment appointment1 = new Appointment(LocalDateTime.now(), "appointment for fracture", "scan", patient);
		Appointment appointment2 = new Appointment(LocalDateTime.now(), "appointment for wrist scan", "scan", patient);

		List<Appointment> appointments = new ArrayList<>();
		appointments.add(appointment);
		appointments.add(appointment1);
		appointments.add(appointment2);

		Mockito.when(appointmentService.viewAllAppointments()).thenReturn(appointments);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(appointments)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testBookAppointment() throws Exception {
		String URI = "/api/v1/appointment";
		Patient patient = new Patient(101L, "Shariq", "Bangalore", "shariq@gmail.com", "9901738247");
		Appointment appointment = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for diabetes", "blood test", patient);

		Mockito.when(appointmentService.bookAppointment(Mockito.any(Appointment.class))).thenReturn(appointment);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(appointment))	
				.contentType(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(appointment)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testRemoveAppointment() throws Exception {
		String URI = "/api/v1/appointment/{appointmentId}";
		Patient patient = new Patient(102L, "Adil", "Bangalore", "adil@gmail.com", "9337256565");
		Appointment appointment = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for fracture", "scan", patient);
		appointment.setAppointmentId(10L);

		doNothing().when(appointmentService).removeAppointment(Mockito.anyLong());
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 10L)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testViewAppointmentById() throws Exception {
		String URI = "/api/v1/appointment/{appointmentId}";
		Patient patient = new Patient(101L, "Shariq", "Bangalore", "shariq@gmail.com", "9901738247");
		Appointment appointment = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for fracture", "scan", patient);
		appointment.setAppointmentId(20L);

		Mockito.when(appointmentService.viewAppointmentById(Mockito.anyLong())).thenReturn(appointment);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 20L)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(appointment)).isEqualTo(jsonOutput);
	}

	@Test
	public void testUpdateAppointment() throws Exception {
		String URI = "/api/v1/appointment/{appointmentId}";
		Patient patient = new Patient(103L, "Nikhilwa", "Bangalore", "nikhilwa@gmail.com", "9738222973");
		Appointment appointment = new Appointment(LocalDateTime.of(2021, 10, 30, 16, 9), "appointment for diabetes", "blood test", patient);
		appointment.setAppointmentId(30L);

		Mockito.when(appointmentService.updateAppointment(Mockito.anyLong(), Mockito.any(Appointment.class))).thenReturn(appointment);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 30L)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(appointment))	
				.contentType(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(appointment)).isEqualTo(jsonOutput);
	}

}
