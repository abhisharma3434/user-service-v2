package com.stanzaliving.userv2.db.service;

import com.stanzaliving.userv2.entity.AbstractMongoEntity;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface AbstractMongoService<T extends AbstractMongoEntity, I extends Serializable> {

	T save(T entity);

	List<T> save(Iterable<T> entities);

	List<T> saveAll(Iterable<T> entities);

	T insert(T entity);

	List<T> insert(Collection<T> entities);

	T find(I id);

	List<T> findAll();

	List<T> findAllByStatus(Boolean status);

	T findByUuid(String uuid);

	List<T> findByUuidIn(Collection<String> uuids);

	boolean delete(T entity);

	void delete(I id);

	void deleteAll(Iterable<T> entities);

	void deleteAll();
}
