package com.stanzaliving.aclv2.entity;

import com.stanzaliving.aclv2.enums.BloodGroup;
import com.stanzaliving.aclv2.enums.Gender;
import com.stanzaliving.aclv2.enums.MaritalStatus;
import com.stanzaliving.aclv2.enums.Nationality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserProfileEntity extends AbstractMongoEntity{

    @Embedded
    private EmployeeEntity employeeEntity;


}
