package com.stanzaliving.userv2.db.service.impl;

import com.stanzaliving.userv2.db.service.UserSessionDbService;
import com.stanzaliving.userv2.entity.UserSessionEntity;
import com.stanzaliving.userv2.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserSessionDbServiceImpl extends AbstractMongoServiceImpl<UserSessionEntity,String, UserSessionRepository>
implements UserSessionDbService {

    @Autowired
    UserSessionRepository userSessionRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    protected UserSessionRepository getMongoRepository() {
        return userSessionRepository;
    }

    @Override
    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Override
    public UserSessionEntity getUserSessionForToken(String bcryptPassword) {
        return getMongoRepository().findByToken(bcryptPassword);
    }
}
