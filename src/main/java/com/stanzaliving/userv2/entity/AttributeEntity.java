package com.stanzaliving.userv2.entity;


import com.stanzaliving.userv2.enums.AttributeType;

public class AttributeEntity extends AbstractMongoEntity{

    
    private String name;

    
    private AttributeType attributeType;

    
    private Boolean isMandatory;

    
    private String resourceId;

}
