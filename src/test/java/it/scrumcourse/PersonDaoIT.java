package it.scrumcourse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import scrumcourse.dao.PersonDao;
import scrumcourse.entity.Person;
import scrumcourse.exception.DaoException;

public class PersonDaoIT extends BaseIT {
	/**
	 * -Se añade Una persona a la BBD - Que no se añade un duplicado - Busqueda
	 * de algo que existe - Probar el deleteAll - Busqueda de algo que nunca se
	 * metio - Busqueda de algo que se borró antes - Test busqueda con la base
	 * vacia - Se borra algo que existe - Se borra algo ya borrado - Se borra
	 * algo que no se introdujo - Se borra con la BD vacia
	 */

	private PersonDao dao;

	@Before
	public void setDao() throws Exception {
		dao = new PersonDao(em);
		transactor.perform(new UnitOfWork() {
			@Override
			public void work() throws Exception {
				dao.deleteAll();
			}
		});
	}

	@After
	public void tearDownTest() throws Exception {
		transactor.perform(new UnitOfWork() {
			@Override
			public void work() throws Exception {
				dao.deleteAll();
			}
		});
	}

	@Test
	public void testAddNewUserToDDBB() throws Exception {
		int count = dao.count();
		transactor.perform(new UnitOfWork() {
			@Override
			public void work() throws Exception {
				Person p = new Person();
				p.setName("namePatata");
				p.setEmail("mailPatata");
				dao.insert(p);
			}
		});
		assertEquals(count + 1, dao.count());
	}

	@Test
	public void testSamePersonTwiceToDDBB() throws Exception {
		int count = dao.count();
		transactor.perform(new UnitOfWork() {
			@Override
			public void work() throws Exception {
				Person p = new Person();
				p.setName("namePatata");
				p.setEmail("mailPatata");
				dao.insert(p);
				dao.insert(p);
			}
		});
		assertEquals(count + 1, dao.count());
	}

	@Test
	public void testFind() throws Exception {
		transactor.perform(new UnitOfWork() {
			@Override
			public void work() throws Exception {
				Person p = new Person();
				p.setName("namePatata");
				p.setEmail("mail3");
				dao.insert(p);
				p = dao.findByEmail("mail3");
				assertEquals("namePatata", p.getName());
			}
		});

	}

	@Test
	public void testDeleteAll() throws Exception {
		transactor.perform(new UnitOfWork() {
			@Override
			public void work() throws Exception {
				Person p = new Person();
				p.setName("namePatata");
				p.setEmail("mail3");
				dao.insert(p);
				p.setName("namePatata2");
				p.setEmail("mail33");
				dao.insert(p);
				p.setName("namePatata5");
				p.setEmail("mail36");
				dao.insert(p);
				dao.deleteAll();
				assertEquals(0, dao.count());
			}
		});

	}

	@Test(expected = DaoException.class)
	public void testSearchSomethingNeverInserted() throws Exception {
		transactor.perform(new UnitOfWork() {
			@Override
			public void work() throws Exception {
				Person p = new Person();
				p.setName("namePatata");
				p.setEmail("mail3");
				dao.insert(p);
				p = dao.findByEmail("mail4");
			}
		});
		fail();
	}

	// @Test
	// public void newUserTrxUnitOfWork() throws Exception {
	// transactor.perform(new UnitOfWork() {
	// @Override
	// public void work() throws Exception {
	// Person p = new Person();
	// p.setName("name3");
	// p.setEmail("mail3");
	// dao.insert(p);
	// assertEquals(2, dao.count());
	// }
	// });
	// assertEquals(2, dao.count());
	// }
	//
	// @Test
	// public void testFind() throws Exception {
	// transactor.perform(new UnitOfWork() {
	// @Override
	// public void work() throws Exception {
	// Person p = dao.findByEmail("mail3");
	// assertEquals("name3", p.getName());
	// }
	// });
	//
	// }
	//

}