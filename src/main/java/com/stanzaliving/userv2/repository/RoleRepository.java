package com.stanzaliving.userv2.repository;

import com.stanzaliving.userv2.entity.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AbstractMongoRepository<RoleEntity,String>{
}
