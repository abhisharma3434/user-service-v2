package com.stanzaliving.userv2.entity;


import com.stanzaliving.userv2.enums.Department;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EmployeeEntity {

    
    private String employeeId;

    
    private String division;

    @Enumerated(EnumType.STRING)
    private Department department;

    
    private String designation;

    
    private String function;

    
    private String ManagerId;

    
    private boolean status;


}
