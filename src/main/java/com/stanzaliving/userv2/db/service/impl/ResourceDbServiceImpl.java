package com.stanzaliving.userv2.db.service.impl;

import com.stanzaliving.userv2.db.service.ResourceDbService;
import com.stanzaliving.userv2.entity.ResourceEntity;
import com.stanzaliving.userv2.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ResourceDbServiceImpl extends AbstractMongoServiceImpl<ResourceEntity,String, ResourceRepository>
        implements ResourceDbService {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    protected ResourceRepository getMongoRepository() {
        return resourceRepository;
    }

    @Override
    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
