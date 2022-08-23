package com.stanzaliving.userv2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("role")
public class RoleEntity extends AbstractMongoEntity{

    
    @NotNull
    protected String name;

    
    protected String description;

}
