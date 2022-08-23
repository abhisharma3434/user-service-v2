package com.stanzaliving.userv2.repository;

import com.stanzaliving.userv2.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends AbstractMongoRepository<UserEntity,String> {
    UserEntity findByMobile(String mobile);

    List<UserEntity> findByEmail(String email);

    UserEntity findByUuidAndStatus(String userId, boolean status);
}
