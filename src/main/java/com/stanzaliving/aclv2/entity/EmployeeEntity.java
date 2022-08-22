package com.stanzaliving.aclv2.entity;


import com.stanzaliving.aclv2.enums.Department;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

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

    @Field
    private String employeeId;

    @Field
    private String division;

    @Enumerated(EnumType.STRING)
    @Field
    private Department department;

    @Field
    private String designation;

    @Field
    private String function;

    @Field
    private String ManagerId;

    @Field
    private boolean status;


}
