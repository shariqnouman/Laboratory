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

import com.cg.healthassist.laboratory.model.Report;
import com.cg.healthassist.laboratory.services.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;

/** The ReportControllerTest class has test methods for the report controller layer
 * 
 * @author Sharique Nooman
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ReportController.class)
public class ReportControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ReportService reportService;

	@Test
	public void testViewAllReports() throws Exception {
		String URI = "/api/v1/report";
		Report report = new Report("report for diabetes", "blood test", LocalDateTime.of(2021, 10, 30, 16, 9));
		Report report1 = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 02, 15, 15, 10));
		Report report2 = new Report("report for eye scan", "scan test", LocalDateTime.of(2021, 05, 22, 14, 20));

		List<Report> reports = new ArrayList<>();
		reports.add(report);
		reports.add(report1);
		reports.add(report2);

		Mockito.when(reportService.viewAllReports()).thenReturn(reports);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(reports)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testAddReport() throws Exception {
		String URI = "/api/v1/report";
		Report report = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 10, 30, 16, 9));

		Mockito.when(reportService.addReport(Mockito.any(Report.class))).thenReturn(report);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(report))	
				.contentType(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(report)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testRemoveReport() throws Exception {
		String URI = "/api/v1/report/{reportId}";
		Report report = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 10, 30, 16, 9));
		report.setReportId(10L);

		doNothing().when(reportService).removeReport(Mockito.anyLong());
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 10L)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testViewReportById() throws Exception {
		String URI = "/api/v1/report/{reportId}";
		Report report = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 10, 30, 16, 9));
		report.setReportId(20L);

		Mockito.when(reportService.viewReportById(Mockito.anyLong())).thenReturn(report);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 20L)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(report)).isEqualTo(jsonOutput);
	}

	@Test
	public void testUpdateReport() throws Exception {
		String URI = "/api/v1/report/{reportId}";
		Report report = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 10, 30, 16, 9));
		report.setReportId(30L);

		Mockito.when(reportService.updateReport(Mockito.anyLong(), Mockito.any(Report.class))).thenReturn(report);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 30L)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(report))	
				.contentType(MediaType.APPLICATION_JSON)
				.with(csrf()))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(report)).isEqualTo(jsonOutput);
	}

}
