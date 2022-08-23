package com.stanzaliving.userv2.listeners;


import com.stanzaliving.userv2.entity.AbstractMongoEntity;

public interface AfterSaveListener<T extends AbstractMongoEntity> {

    void afterSave(T entity);

    Class getEntity();
}
