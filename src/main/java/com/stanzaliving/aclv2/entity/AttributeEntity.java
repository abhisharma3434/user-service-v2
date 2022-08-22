package com.stanzaliving.aclv2.entity;


import com.stanzaliving.aclv2.enums.AttributeType;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class AttributeEntity extends AbstractMongoEntity{

    @Field
    private String name;

    @Field
    private AttributeType attributeType;

    @Field
    private Boolean isMandatory;

    @Field
    private String resourceId;

}
