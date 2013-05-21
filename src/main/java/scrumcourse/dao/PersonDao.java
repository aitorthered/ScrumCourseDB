package scrumcourse.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import scrumcourse.entity.Person;
import scrumcourse.exception.DaoException;

public class PersonDao implements BaseDao<Person, Long> {

	private final EntityManager em;

	public PersonDao(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public void insert(Person entity) throws DaoException {
		try {
			em.persist(entity);
		} catch (Exception e) {
			throw DaoException.exception(e);
		}
	}

	@Override
	public Person update(Person entity) throws DaoException {
		try {
			return em.merge(entity);
		} catch (Exception e) {
			throw DaoException.exception(e);
		}
	}

	@Override
	public void delete(Person entity) throws DaoException {
		try {
			em.remove(entity);
		} catch (Exception e) {
			throw DaoException.exception(e);
		}
	}

	@Override
	public Person findById(Long id) throws DaoException {
		try {
			return em.find(Person.class, id);
		} catch (Exception e) {
			throw DaoException.exception(e);
		}
	}

	@Override
	public int count() throws DaoException {
		try {
			return (int) em
					.createQuery("SELECT COUNT(*) FROM Person o", Long.class)
					.getSingleResult().longValue();
		} catch (Exception e) {
			throw DaoException.exception(e);
		}
	}

	@Override
	public List<Person> findAll() throws DaoException {
		try {
			return findAll(-1, -1);
		} catch (Exception e) {
			throw DaoException.exception(e);
		}
	}

	@Override
	public List<Person> findAll(int index, int size) throws DaoException {
		try {
			TypedQuery<Person> q = em.createQuery("SELECT o FROM Person o",
					Person.class);
			if (index >= 0) {
				q.setFirstResult(index);
			}
			if (size > 0) {
				q.setMaxResults(size);
			}
			return q.getResultList();
		} catch (Exception e) {
			throw DaoException.exception(e);
		}
	}

	public Person findByEmail(String email) throws DaoException {
		try {
			TypedQuery<Person> q = em.createQuery(
					"SELECT o FROM Person o WHERE o.email = :email",
					Person.class);
			q.setParameter("email", email);
			return q.getSingleResult();
		} catch (Exception e) {
			throw DaoException.exception(e);
		}
	}

	@Override
	public void deleteAll() throws DaoException {
		try {
			String deleteQuery = "DELETE Person o";
			Query query = em.createQuery(deleteQuery);
			query.executeUpdate();
		} catch (Exception e) {
			throw DaoException.exception(e);
		}
	}

}
