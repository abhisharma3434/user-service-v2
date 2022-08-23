package com.stanzaliving.userv2.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    EMPLOYEE("Employee"),
    RESIDENT("Resident"),
    BROKER("Broker"),
    LANDLORD("Landlord"),
    VENDOR("Vendor");


    public String typeName;
}
