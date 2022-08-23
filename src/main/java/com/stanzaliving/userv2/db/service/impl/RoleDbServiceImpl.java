package com.stanzaliving.userv2.db.service.impl;

import com.stanzaliving.userv2.db.service.RoleDbService;
import com.stanzaliving.userv2.entity.RoleEntity;
import com.stanzaliving.userv2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoleDbServiceImpl extends AbstractMongoServiceImpl<RoleEntity,String, RoleRepository>
        implements RoleDbService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    protected RoleRepository getMongoRepository() {
        return roleRepository;
    }

    @Override
    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
