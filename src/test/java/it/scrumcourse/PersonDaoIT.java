package it.scrumcourse;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import scrumcourse.dao.PersonDao;
import scrumcourse.entity.Person;

public class PersonDaoIT extends BaseIT {

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

	@After
	public void tearDownTest() throws Exception {
		transactor.perform(new UnitOfWork() {
			@Override
			public void work() throws Exception {
				dao.deleteAll();
			}
		});
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