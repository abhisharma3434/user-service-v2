package com.stanzaliving.aclv2.entity;

import com.stanzaliving.aclv2.enums.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class UserEntity extends AbstractMongoEntity{

    @Field
    @NotNull
    private String mobile;

    @Field
    private boolean mobileVerified;

    @Field
    private String email;

    @Field
    private boolean emailVerified;

    @Field
    @NotNull
    private String isoCode;

    @Field
    @NotNull
    private String firstName;

    @Field
    private String middleName;

    @Field
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Field
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;


    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Embedded
    private AddressEntity address;

    @Embedded
    private UserProfileEntity userProfileEntity;

}
