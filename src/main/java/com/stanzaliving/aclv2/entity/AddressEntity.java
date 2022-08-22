package com.stanzaliving.aclv2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressEntity implements Serializable {


    @Field
    private String addressLine1;

    @Field
    private String addressLine2;

    @Field
    private String landmark;

    @Field
    private String cityName;

    @Field
    private String stateName;

    @Field
    private String postalCode;

    @Field
    private String countryName;

}
