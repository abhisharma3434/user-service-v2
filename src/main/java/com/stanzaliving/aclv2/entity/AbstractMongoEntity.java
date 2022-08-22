package com.stanzaliving.aclv2.entity;

import com.stanzaliving.aclv2.listeners.MongoEventListener;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.EntityListeners;
import java.io.Serializable;
import java.util.Date;

/**
 * Abstract class with generic ID. All models will be extending this class
 *
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({ MongoEventListener.class })
//@SuppressWarnings("serial")
public abstract class AbstractMongoEntity implements Serializable {

    @Id
    protected String id;

    protected String uuid;

    protected Date createdAt;

    protected String createdBy;

    @LastModifiedDate
    protected Date updatedAt;

    protected String updatedBy;

    @Builder.Default
    protected boolean status = true;




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractMongoEntity other=(AbstractMongoEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (uuid == null) {
            if (other.uuid != null) {
                return false;
            }
        } else if (!uuid.equals(other.uuid)) {
            return false;
        }
        return true;
    }

}
