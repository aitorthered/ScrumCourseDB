package it.scrumcourse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class BaseIT {

	protected JPATransactor transactor;

	private static EntityManagerFactory emf;

	protected EntityManager em;

	@BeforeClass public static void setUpClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("scrumcoursePU");
	}

	@Before public void setUp() {
		em = emf.createEntityManager();
		transactor = new JPATransactor(em);
	}

	@After public void tearDown() {
		em.close();
		em = null;
	}

	@AfterClass public static void tearDownClass() throws Exception {
		emf.close();
		emf = null;
	}
}