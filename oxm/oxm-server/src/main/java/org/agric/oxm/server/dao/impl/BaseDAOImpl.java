package org.agric.oxm.server.dao.impl;

import java.util.List;

import org.agric.oxm.model.BaseData;
import org.agric.oxm.model.RecordStatus;
import org.agric.oxm.server.dao.BaseDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;

public abstract class BaseDAOImpl<T> extends GenericDAOImpl<T, String>
		implements BaseDAO<T> {

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
	super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<T> searchByPropertyEqual(String property, Object value) {
	Search search = new Search();
	search.addFilterEqual(property, value);
	search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
	return search(search);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T searchUniqueByPropertyEqual(String property, Object value) {
	Search search = new Search();
	search.addFilterEqual(property, value);
	search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
	return (T) searchUnique(search);
    }

    @Override
    public void delete(T entity) {
	if (entity != null) {
	    if (entity instanceof BaseData) {
		((BaseData) entity).setRecordStatus(RecordStatus.DELETED);
		super.save(entity);
	    }
	}
    }

    @Override
    public void update(T entity) {
	super.save(entity);
    }

    @Override
    public void add(T entity) {
	super.save(entity);
    }

    @Override
    public List<T> searchByPropertyEqual(String property, Object value,
			RecordStatus recordStatus) {
	Search search = new Search();
	search.addFilterEqual(property, value);
	search.addFilterEqual("recordStatus", recordStatus);
	return search(search);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T searchUniqueByPropertyEqual(String property, Object value,
			RecordStatus recordStatus) {
	Search search = new Search();
	search.addFilterEqual(property, value);
	search.addFilterEqual("recordStatus", recordStatus);
	return (T) searchUnique(search);
    }

    @Override
    public List<T> searchByRecordStatus(RecordStatus recordStatus) {
	Search search = new Search();
	search.addFilterEqual("recordStatus", recordStatus);
	return search(search);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T searchUniqueByPropertyEqual(String property, String property2, String property3,
	    Object value, Object value2, Object value3, RecordStatus recordStatus) {
	Search search = new Search();
	search.addFilterEqual(property, value);
	search.addFilterEqual(property2, value2);
	search.addFilterEqual(property3, value3);
	search.addFilterEqual("recordStatus", recordStatus);
	return (T) searchUnique(search);
    }
}