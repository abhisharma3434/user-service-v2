package com.stanzaliving.userv2.repository;

import com.stanzaliving.userv2.entity.PermissionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends AbstractMongoRepository<PermissionEntity,String> {
}
