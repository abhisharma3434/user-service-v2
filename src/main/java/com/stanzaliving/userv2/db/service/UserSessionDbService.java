package com.stanzaliving.userv2.db.service;

import com.stanzaliving.userv2.entity.UserSessionEntity;

public interface UserSessionDbService extends AbstractMongoService<UserSessionEntity,String>{
    UserSessionEntity getUserSessionForToken(String bcryptPassword);
}
