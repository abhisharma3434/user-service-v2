package com.stanzaliving.userv2.entity;

import com.stanzaliving.userv2.converter.LocalDateAttributeConverter;
import com.stanzaliving.userv2.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
    @Field
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    @Field
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    @Field
    private BloodGroup bloodGroup;

    @Field
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate birthday;

    @Embedded
    private AddressEntity address;

    @Embedded
    private UserProfileEntity userProfileEntity;

}
