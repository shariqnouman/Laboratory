package com.cg.healthassist.laboratory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.healthassist.laboratory.model.Appointment;

/** This is an interface which defines CRUD methods for appointment JPA repository
 * 
 * @author Sharique Nooman
 *
 */
public interface AppointmentsRepository extends JpaRepository<Appointment, Long>{

}
