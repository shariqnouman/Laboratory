package com.cg.healthassist.laboratory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.healthassist.laboratory.model.Appointment;

public interface AppointmentsJpaRepository extends JpaRepository<Appointment, Long>{

}
