package com.stanzaliving.aclv2.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.mongodb.core.mapping.event.*;

public abstract class AbstractMongoEventListener<E> implements ApplicationListener<MongoMappingEvent<?>> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMongoEventListener.class);
    private final Class<?> domainClass;

    public AbstractMongoEventListener() {
        Class<?> typeArgument = GenericTypeResolver.resolveTypeArgument(this.getClass(), AbstractMongoEventListener.class);
        this.domainClass = typeArgument == null ? Object.class : typeArgument;
    }

    public void onApplicationEvent(MongoMappingEvent<?> event) {
        if (event instanceof AfterLoadEvent) {
            AfterLoadEvent<?> afterLoadEvent = (AfterLoadEvent)event;
            if (this.domainClass.isAssignableFrom(afterLoadEvent.getType())) {
                this.onAfterLoad((AfterLoadEvent)event);
            }

        } else if (event instanceof AbstractDeleteEvent) {
            Class<?> eventDomainType = ((AbstractDeleteEvent)event).getType();
            if (eventDomainType != null && this.domainClass.isAssignableFrom(eventDomainType)) {
                if (event instanceof BeforeDeleteEvent) {
                    this.onBeforeDelete((BeforeDeleteEvent)event);
                }

                if (event instanceof AfterDeleteEvent) {
                    this.onAfterDelete((AfterDeleteEvent)event);
                }
            }

        } else {
            Object source = event.getSource();
            if (source == null || this.domainClass.isAssignableFrom(source.getClass())) {
                if (event instanceof BeforeConvertEvent) {
                    this.onBeforeConvert((BeforeConvertEvent)event);
                } else if (event instanceof BeforeSaveEvent) {
                    this.onBeforeSave((BeforeSaveEvent)event);
                } else if (event instanceof AfterSaveEvent) {
                    this.onAfterSave((AfterSaveEvent)event);
                } else if (event instanceof AfterConvertEvent) {
                    this.onAfterConvert((AfterConvertEvent)event);
                }

            }
        }
    }

    public void onBeforeConvert(BeforeConvertEvent<E> event) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onBeforeConvert({})", event.getSource());
        }

    }

    public void onBeforeSave(BeforeSaveEvent<E> event) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onBeforeSave({}, {})", event.getSource(), event.getDocument());
        }

    }

    public void onAfterSave(AfterSaveEvent<E> event) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onAfterSave({}, {})", event.getSource(), event.getDocument());
        }

    }

    public void onAfterLoad(AfterLoadEvent<E> event) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onAfterLoad({})", event.getDocument());
        }

    }

    public void onAfterConvert(AfterConvertEvent<E> event) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onAfterConvert({}, {})", event.getDocument(), event.getSource());
        }

    }

    public void onAfterDelete(AfterDeleteEvent<E> event) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onAfterDelete({})", event.getDocument());
        }

    }

    public void onBeforeDelete(BeforeDeleteEvent<E> event) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onBeforeDelete({})", event.getDocument());
        }

    }
}

