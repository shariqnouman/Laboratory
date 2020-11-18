package com.cg.healthassist.laboratory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.healthassist.laboratory.model.Report;

public interface ReportsJpaRepository extends JpaRepository<Report, Long>{

}
