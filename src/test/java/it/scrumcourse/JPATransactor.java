package it.scrumcourse;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JPATransactor {
	private final EntityManager em;

	public JPATransactor(EntityManager em) {
		this.em = em;
	}

	public void perform(UnitOfWork unitOfWork) throws Exception {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			unitOfWork.work();
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}
}