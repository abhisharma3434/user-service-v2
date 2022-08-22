package com.stanzaliving.aclv2.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    EMPLOYEE("Employee"),
    RESIDENT("Resident"),
    BROKER("Broker"),
    LANDLORD("Landlord"),
    Vendor("Vendor");


    public String typeName;
}
