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

import com.cg.healthassist.laboratory.model.Report;
import com.cg.healthassist.laboratory.repositories.ReportsRepository;

/** The ReportServiceImplTest class has test methods for the report service layer
 * 
 * @author Sharique Nooman
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ReportServiceImpl.class)
public class ReportServiceImplTest {

	@MockBean
	private ReportsRepository reportsRepository;

	@Autowired
	private ReportService reportService;

	@Test
	public void testAddReport() {
		Report report = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 10, 30, 16, 9));

		Mockito.when(reportsRepository.saveAndFlush(report)).thenReturn(report);
		assertThat(reportService.addReport(report)).isEqualTo(report);

		verify(reportsRepository).saveAndFlush(report);
	}

	@Test
	public void testRemoveReport() {
		Report report = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 10, 30, 16, 9));
		report.setReportId(20L);

		reportService.removeReport(report.getReportId());
		verify(reportsRepository, times(1)).deleteById(report.getReportId());
	}

	@Test
	public void testViewAllReports() {
		Report report = new Report("report for diabetes", "blood test", LocalDateTime.of(2021, 10, 30, 16, 9));
		Report report1 = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 02, 15, 15, 10));
		Report report2 = new Report("report for eye scan", "scan test", LocalDateTime.of(2021, 05, 22, 14, 20));

		List<Report> reports = new ArrayList<>();
		reports.add(report);
		reports.add(report1);
		reports.add(report2);

		Mockito.when(reportsRepository.findAll()).thenReturn(reports);
		assertThat(reportService.viewAllReports()).isEqualTo(reports);
		assertEquals(3, reportService.viewAllReports().size());

		verify(reportsRepository, times(2)).findAll();
	}

	@Test
	public void testViewReportById() {
		Report report = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 10, 30, 16, 9));
		report.setReportId(20L);

		Mockito.when(reportsRepository.findById(report.getReportId())).thenReturn(Optional.of(report));
		assertThat(reportService.viewReportById(20L)).isEqualTo(report);

		verify(reportsRepository).findById(report.getReportId());
	}

	@Test
	public void testUpdateReport() {
		Report report = new Report("report for diabetes", "blood test", LocalDateTime.of(2021, 10, 30, 16, 9));
		report.setReportId(30L);

		Report updatedReport = report;
		updatedReport.setReportId(30L);
		updatedReport.setReportDate(LocalDateTime.now());
		updatedReport.setReportDescription("report for fracture");
		updatedReport.setReportName("scan");

		Mockito.when(reportsRepository.saveAndFlush(updatedReport)).thenReturn(updatedReport);
		Mockito.when(reportsRepository.findById(report.getReportId())).thenReturn(Optional.of(report));
		reportService.updateReport(report.getReportId(), updatedReport);

		assertThat(report.getReportDescription()).isEqualTo(updatedReport.getReportDescription());
		verify(reportsRepository).saveAndFlush(updatedReport);
	}

}
