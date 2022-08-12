package br.com.pirralhos.model.dao.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Criterion;

/**
 * 
 * @author L842359
 */
public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id);

	List<T> listAll();

	T save(T entity);

	void delete(T entity);

	List<T> findByCriteria(Criterion... criterion);

	List<T> findBySQL(String sql);

	void update(T entity);

}
