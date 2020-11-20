package com.cg.healthassist.laboratory.repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/** Test suite class for appointment and report JPA repositories
 * 
 * @author Sharique Nooman
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AppointmentsRepositoryTest.class, ReportsRepositoryTest.class })
public class RepositorySuiteTest {

}
