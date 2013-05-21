package scrumcourse.dao;

import java.io.Serializable;
import java.util.List;

import scrumcourse.entity.BaseEntity;
import scrumcourse.exception.DaoException;

public interface BaseDao<T extends BaseEntity, ID extends Serializable> {

	void insert(T entity) throws DaoException;

	T update(T entity) throws DaoException;

	void delete(T entity) throws DaoException;

	/**
	 * Returns null if not found
	 */
	T findById(ID id) throws DaoException;

	int count() throws DaoException;

	/**
	 * Never returns null. Returns empty list if no elements are found
	 */
	List<T> findAll() throws DaoException;

	/**
	 * For paging, returns from 0-based index, size elements
	 */
	List<T> findAll(int index, int size) throws DaoException;

	void deleteAll() throws DaoException;
}
