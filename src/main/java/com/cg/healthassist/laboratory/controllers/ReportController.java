package com.cg.healthassist.laboratory.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassist.laboratory.model.Report;
import com.cg.healthassist.laboratory.services.ReportService;

/** The ReportController class provides restful services to client like
 *  GET, POST, DELETE, and PUT
 * 
 * @author Sharique Nooman
 *
 */
@RestController
@Validated
@RequestMapping("/api/v1/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	/** GET method for viewing all reports
	 * 
	 * @return list of all reports
	 */
	@GetMapping
	public List<Report> viewAllReports() {
		return reportService.viewAllReports();
	}

	/** POST method to add report
	 * 
	 * @param report
	 * @return Report object
	 */
	@PostMapping
	public Report addReport(@Valid @RequestBody Report report) {
		return reportService.addReport(report);
	}

	/** DELETE method to remove report
	 * 
	 * @param reportId
	 */
	@DeleteMapping("{reportId}")
	public void removeReport(@PathVariable @Min(1) Long reportId) {
		reportService.removeReport(reportId);
	}

	/** GET method to view report by Id
	 * 
	 * @param reportId
	 * @return Report object
	 */
	@GetMapping("{reportId}")
	public Report viewReportById(@PathVariable @Min(1) Long reportId) {
		return reportService.viewReportById(reportId);
	}
	/** PUT method to update report
	 * 
	 * @param reportId
	 * @param report
	 * @return Report object
	 */
	@PutMapping("{reportId}")
	public Report updateReport(@PathVariable @Min(1) Long reportId, @Valid @RequestBody Report report) {
		return reportService.updateReport(reportId, report);
	}
}
