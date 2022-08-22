package com.stanzaliving.aclv2.entity;

import com.stanzaliving.aclv2.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("user_session")
public class UserSessionEntity extends AbstractMongoEntity {
    @Field
    private String userId;

    @Field
    private String token;

    @Enumerated(EnumType.STRING)
    @Field
    private UserType userType;

    @Field
    private String os;

    @Field
    private String osVerison;

    @Field
    private String browser;

    @Field
    private String browserVersion;

    @Field
    private String ip;

    @Field
    private String imei;

    @Field
    private String device;

    @Field
    private String model;

    @Field
    private String appVersion;
}
