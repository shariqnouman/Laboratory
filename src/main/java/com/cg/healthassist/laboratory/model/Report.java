package com.cg.healthassist.laboratory.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "report_id")
	private Long reportId;
	
	@Column(name = "report_description", nullable = false)
	private String reportDescription;
	
	@Column(name = "report_name", nullable = false)
	private String reportName;
	
	@Column(name = "report_date", nullable = false)
	private LocalDateTime reportDate;

	public Report() {

	}

	public Report(String reportDescription, String reportName, LocalDateTime reportDate) {
		super();
		this.reportDescription = reportDescription;
		this.reportName = reportName;
		this.reportDate = reportDate;
	}

	public long getReportId() {
		return reportId;
	}

	public String getReportDescription() {
		return reportDescription;
	}

	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public LocalDateTime getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDateTime reportDate) {
		this.reportDate = reportDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "\n Report Id = " + reportId + "\n Report Description = " + reportDescription + "\n Report Name = "
				+ reportName + "\n Report Date = " + reportDate + "\n---------------------------------\n";
	}
}
