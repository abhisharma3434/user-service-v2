package com.stanzaliving.userv2.db.service.impl;

import com.stanzaliving.userv2.db.service.UserDbService;
import com.stanzaliving.userv2.entity.UserEntity;
import com.stanzaliving.userv2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDbServiceImpl extends AbstractMongoServiceImpl<UserEntity,String, UserRepository>
        implements UserDbService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    protected UserRepository getMongoRepository() {
        return userRepository;
    }

    @Override
    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Override
    public UserEntity findByMobile(String mobile) {
        return getMongoRepository().findByMobile(mobile);
    }

    @Override
    public List<UserEntity> findByEmail(String email) {
        return getMongoRepository().findByEmail(email);
    }

    @Override
    public UserEntity findByUuidAndStatus(String userId, boolean status) {
        return getMongoRepository().findByUuidAndStatus(userId,status);
    }
}
