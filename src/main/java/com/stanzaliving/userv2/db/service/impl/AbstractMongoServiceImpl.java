package com.stanzaliving.userv2.db.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.stanzaliving.userv2.db.service.AbstractMongoService;
import com.stanzaliving.userv2.entity.AbstractMongoEntity;
import com.stanzaliving.userv2.repository.AbstractMongoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
@Log4j2
public abstract class AbstractMongoServiceImpl<T extends AbstractMongoEntity, I extends Serializable, R extends AbstractMongoRepository<T,I>>
		implements AbstractMongoService<T, I> {

	protected abstract R getMongoRepository();

	protected abstract MongoTemplate getMongoTemplate();

	@Override
	public T save(T entity) {
		return getMongoRepository().save(entity);
	}

	@Override
	public List<T> save(Iterable<T> entities) {
		return getMongoRepository().saveAll(entities);
	}

	@Override
	public List<T> saveAll(Iterable<T> entities) {
		return getMongoRepository().saveAll(entities);
	}

	@Override
	public T insert(T entity) {
		return getMongoRepository().insert(entity);
	}

	@Override
	public List<T> insert(Collection<T> entities) {
		return getMongoRepository().saveAll(entities);
	}

	@Override
	public T find(I id) {
		return getMongoRepository().findById(id).orElse(null);
	}

	@Override
	public List<T> findAll() {
		return getMongoRepository().findAll();
	}

	@Override
	public List<T> findAllByStatus(Boolean status) {
		return getMongoRepository().findAllByStatus(status);
	}

	@Override
	public T findByUuid(String uuid) {
		return getMongoRepository().findFirstByUuid(uuid);
	}

	@Override
	public List<T> findByUuidIn(Collection<String> uuids) {
		return getMongoRepository().findByUuidIn(uuids);
	}

	@Override
	public boolean delete(T entity) {
		DeleteResult result = getMongoTemplate().remove(entity);
		if(result.getDeletedCount()==0) return false;
		return true;
	}

	@Override
	public void delete(I id) {
		getMongoRepository().deleteById(id);
	}

	@Override
	public void deleteAll(Iterable<T> entities) {
		getMongoRepository().deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		getMongoRepository().deleteAll();
	}
}