package com.stanzaliving.userv2.repository;

import com.stanzaliving.userv2.entity.UserSessionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends AbstractMongoRepository<UserSessionEntity,String> {
    UserSessionEntity findByToken(String bcryptPassword);
}
