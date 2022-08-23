package com.stanzaliving.userv2.entity;

import com.stanzaliving.userv2.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document("user_session")
public class UserSessionEntity extends AbstractMongoEntity {
    
    private String userId;

    
    private String token;

    @Enumerated(EnumType.STRING)
    
    private UserType userType;

    
    private String os;

    
    private String osVerison;

    
    private String browser;

    
    private String browserVersion;

    
    private String ip;

    
    private String imei;

    
    private String device;

    
    private String model;

    
    private String appVersion;
}
