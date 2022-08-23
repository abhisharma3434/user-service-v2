package com.stanzaliving.userv2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("resource")
public class  ResourceEntity extends AbstractMongoEntity{

    
    protected String name;

}
