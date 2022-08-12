package br.com.pirralhos.model.dao.base.impl;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.pirralhos.model.dao.base.GenericDAO;

public class GenericDAOImpl<T, ID extends Serializable> extends
		HibernateDaoSupport implements GenericDAO<T, ID>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log LOG = LogFactory.getLog(GenericDAOImpl.class);

	@SuppressWarnings("unchecked")
	public GenericDAOImpl(String className) throws ClassNotFoundException {
		this.persistentClass = (Class<T>) Class.forName(className);

	}

	private Class<T> persistentClass;

	public Class<T> getPersistentClass() {
		return this.persistentClass;
	}

	@Override
	public void delete(T entity) {
		try {
			this.getHibernateTemplate().delete(entity);
		} catch (final HibernateException ex) {
			GenericDAOImpl.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	@Override
	public T findById(ID id) {
		try {
			return this.getHibernateTemplate().load(getPersistentClass(), id);
		} catch (final HibernateException ex) {
			GenericDAOImpl.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	@Override
	public List<T> listAll() {
		try {
			if (this.getHibernateTemplate() == null) {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("timeout.xhtml");
					return null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return this.getHibernateTemplate().loadAll(getPersistentClass());
		} catch (final HibernateException ex) {

			GenericDAOImpl.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}

	}

	@Override
	public T save(T entity) {
		try {
			this.getHibernateTemplate().saveOrUpdate(entity);
			return entity;
		} catch (final HibernateException ex) {
			GenericDAOImpl.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion... criterion) {
		Session session = null;
		try {
			session = this.getHibernateTemplate().getSessionFactory()
					.openSession();
			Criteria crit = session.createCriteria(getPersistentClass());
			for (Criterion c : criterion) {
				crit.add(c);
			}
			return crit.list();
		} catch (final HibernateException ex) {
			GenericDAOImpl.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findBySQL(String sql) {
		Session session = null;
		try {
			if (this.getHibernateTemplate() == null) {
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("timeout.xhtml");
					return null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			session = this.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query q = session.createQuery(sql);
			return q.list();
		} catch (final HibernateException ex) {
			GenericDAOImpl.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void update(T entity) {
		try {
			Session session = this.getHibernateTemplate().getSessionFactory()
					.openSession();
			session.update(entity);
			session.flush();
		} catch (final HibernateException ex) {
			GenericDAOImpl.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

}
