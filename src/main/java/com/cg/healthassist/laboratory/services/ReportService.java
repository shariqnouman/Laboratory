package com.cg.healthassist.laboratory.services;

import java.util.List;

import com.cg.healthassist.laboratory.model.Report;

/** This is an interface for ReportService class, it has all the abstract methods
 * 
 * @author Sharique Nooman
 *
 */
public interface ReportService {

	/** This method takes report details and adds report to patient
	 * 
	 * @param report - report entity details
	 * 
	 * */
	Report addReport(Report report);

	/** This method takes report Id and removes report
	 * 
	 * @param reportId - long value to remove report based on Id
	 * 
	 * */
	void removeReport(Long reportId);

	/** This method returns list of all reports
	 * 
	 * @return list of all reports
	 * 
	 * */
	List<Report> viewAllReports();

	/** This method returns report by searching with specific report Id
	 * 
	 * @param reportId - long value to view report based on Id
	 * @return report details if found or else throws exception
	 * 
	 * */
	Report viewReportById(Long reportId);

	/** This method updates the report details by searching with id
	 * 
	 * @param reportId - long value to update report based on Id
	 * 
	 * */
	Report updateReport(Long reportId, Report report);

}