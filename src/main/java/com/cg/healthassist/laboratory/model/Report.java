package com.cg.healthassist.laboratory.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** This is an entity class for reports with getters, setters, and constructor
 * 
 * @author Sharique Nooman
 * @version 1.0
 *
 */
@Entity
@Table(name = "reports")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "report_id")
	private Long reportId;

	@Column(name = "report_description", nullable = false)
	@NotBlank
	@Size(min = 4, message = "Please provide a valid report description having min 4 characters")
	private String reportDescription;

	@Column(name = "report_name", nullable = false)
	@NotBlank
	@Size(min = 4, message = "Please provide a valid report name having min 4 characters")
	private String reportName;

	@Column(name = "report_date", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@FutureOrPresent(message = "Please provide a valid date in format yyyy-MM-dd HH:mm and it should not be a past date")
	@NotNull
	private LocalDateTime reportDate;

	public Report() {

	}

	public Report(String reportDescription, String reportName, LocalDateTime reportDate) {
		super();
		this.reportDescription = reportDescription;
		this.reportName = reportName;
		this.reportDate = reportDate;
	}

	public Long getReportId() {
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

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "\n Report Id = " + reportId + "\n Report Description = " + reportDescription + "\n Report Name = "
				+ reportName + "\n Report Date = " + reportDate + "\n---------------------------------\n";
	}
}
