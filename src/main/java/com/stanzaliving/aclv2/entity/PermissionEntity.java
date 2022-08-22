package com.stanzaliving.aclv2.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("permission")
public class PermissionEntity extends AbstractMongoEntity{

    @Field
    protected String name;

    @Field
    protected String resourceId;


}
