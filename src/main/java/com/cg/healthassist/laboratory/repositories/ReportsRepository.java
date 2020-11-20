package com.cg.healthassist.laboratory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.healthassist.laboratory.model.Report;

/** This is an interface which defines CRUD methods for report JPA repository
 * 
 * @author Sharique Nooman
 *
 */
public interface ReportsRepository extends JpaRepository<Report, Long>{

}
