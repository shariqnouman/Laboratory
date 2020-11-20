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

import com.cg.healthassist.laboratory.model.Report;

/** The ReportsJpaRepositoryTest class has test methods for
 *  methods in ReportsJpaRepository class
 * 
 * @author Sharique Nooman
 * @version 1.0
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ReportsRepositoryTest {

	@Autowired
	private ReportsRepository reportsJpaRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void testSaveAndFlush() {
		Report report = new Report("report for diabetes", "blood test", LocalDateTime.of(2021, 10, 30, 16, 9));
		Long reportId = testEntityManager.persistAndGetId(report, Long.class);
		Report expectedReport = reportsJpaRepository.getOne(report.getReportId());

		assertNotNull(reportId);
		assertNotNull(expectedReport);
		assertEquals(expectedReport.getReportDate(), report.getReportDate());
	}

	@Test
	public void testGetOne() {
		Report report = new Report("report for diabetes", "blood test", LocalDateTime.of(2021, 10, 30, 16, 9));
		Report expectedReport = testEntityManager.persist(report);
		Report actualReport = reportsJpaRepository.getOne(report.getReportId());

		assertNotNull(expectedReport);
		assertNotNull(actualReport);
		assertThat(expectedReport).isEqualTo(actualReport);
	}

	@Test
	public void testDeleteById() {
		Report report = new Report("report for diabetes", "blood test", LocalDateTime.of(2021, 10, 30, 16, 9));
		Report report1 = new Report("report for fracture", "scan", LocalDateTime.of(2021, 10, 30, 16, 9));

		testEntityManager.persist(report);
		Report expectedReport = testEntityManager.persist(report1);
		testEntityManager.remove(expectedReport);
		List<Report> reports = reportsJpaRepository.findAll();

		assertNotNull(expectedReport);
		assertEquals(1, reports.size());
	}

	@Test
	public void testFindAll() {
		Report report = new Report("report for diabetes", "blood test", LocalDateTime.of(2021, 10, 30, 16, 9));
		Report report1 = new Report("report for fracture", "scan test", LocalDateTime.of(2021, 10, 30, 16, 9));
		Report report2 = new Report("report for eye", "eye scan", LocalDateTime.of(2021, 10, 30, 16, 9));

		testEntityManager.persist(report);
		testEntityManager.persist(report1);
		testEntityManager.persist(report2);

		List<Report> reports = reportsJpaRepository.findAll();
		assertEquals(3, reports.size());
	}

}
