package com.stanzaliving.aclv2.listeners;


import com.stanzaliving.aclv2.entity.AbstractMongoEntity;

public interface AfterSaveListener<T extends AbstractMongoEntity> {

    void afterSave(T entity);

    Class getEntity();
}
