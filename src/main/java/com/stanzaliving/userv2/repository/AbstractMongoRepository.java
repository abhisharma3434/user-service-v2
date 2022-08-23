package com.stanzaliving.userv2.repository;

import com.stanzaliving.userv2.entity.AbstractMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@NoRepositoryBean
public interface AbstractMongoRepository<T extends AbstractMongoEntity, I extends Serializable> extends MongoRepository<T, I> {

    T findFirstByUuid(String uuid);

    List<T> findByUuidIn(Collection<String> uuids);

    List<T> findAllByStatus(Boolean status);
}
