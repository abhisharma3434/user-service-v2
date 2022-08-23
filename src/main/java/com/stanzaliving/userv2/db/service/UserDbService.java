package com.stanzaliving.userv2.db.service;

import com.stanzaliving.userv2.entity.UserEntity;

import java.util.List;

public interface UserDbService extends AbstractMongoService<UserEntity,String>{
    UserEntity findByMobile(String mobile);

    List<UserEntity> findByEmail(String email);

    UserEntity findByUuidAndStatus(String userId, boolean b);
}
