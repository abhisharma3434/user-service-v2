package com.stanzaliving.userv2.db.service.impl;

import com.stanzaliving.userv2.db.service.PermissionDbService;
import com.stanzaliving.userv2.entity.PermissionEntity;
import com.stanzaliving.userv2.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class PermissionDbServiceImpl extends AbstractMongoServiceImpl<PermissionEntity,String, PermissionRepository>
        implements PermissionDbService {
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    protected PermissionRepository getMongoRepository() {
        return permissionRepository;
    }

    @Override
    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
