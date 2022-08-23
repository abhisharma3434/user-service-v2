package com.stanzaliving.userv2.repository;

import com.stanzaliving.userv2.entity.ResourceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends AbstractMongoRepository<ResourceEntity,String> {
}
