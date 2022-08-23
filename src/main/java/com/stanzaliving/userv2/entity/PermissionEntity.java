package com.stanzaliving.userv2.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("permission")
public class PermissionEntity extends AbstractMongoEntity{

    
    protected String name;

    
    protected String resourceId;


}
