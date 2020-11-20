package com.cg.healthassist.laboratory.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.healthassist.laboratory.model.Report;
import com.cg.healthassist.laboratory.repositories.ReportsRepository;

/** The ReportService class provides access to JPA methods to add,
 * 	remove, view, and update reports of patients
 * 
 * @author Sharique Nooman
 * @version 1.0
 * 
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportsRepository reportsRepository;

	/** This method takes report details and adds report to patient
	 * 
	 * @param report - report entity details
	 * 
	 * */
	@Override
	public Report addReport(Report report) {
		return reportsRepository.saveAndFlush(report);
	}

	/** This method takes report Id and removes report
	 * 
	 * @param reportId - long value to remove report based on Id
	 * 
	 * */
	@Override
	public void removeReport(Long reportId) {
		reportsRepository.deleteById(reportId);
	}

	/** This method returns list of all reports
	 * 
	 * @return list of all reports
	 * 
	 * */
	@Override
	public List<Report> viewAllReports() {
		return reportsRepository.findAll();
	}

	/** This method returns report by searching with specific report Id
	 * 
	 * @param reportId - long value to view report based on Id
	 * @return report details if found or else throws exception
	 * 
	 * */
	@Override
	public Report viewReportById(Long reportId) {
		return reportsRepository.getOne(reportId);
	}

	/** This method updates the report details by searching with id
	 * 
	 * @param reportId - long value to update report based on Id
	 * 
	 * */
	@Override
	public Report updateReport(Long reportId, Report report) {
		Report existingReport = reportsRepository.getOne(reportId);
		BeanUtils.copyProperties(report, existingReport, "reportId");
		return reportsRepository.saveAndFlush(existingReport);
	}
}
