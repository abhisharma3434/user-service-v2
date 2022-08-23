package com.stanzaliving.userv2.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressEntity implements Serializable {


    
    private String addressLine1;

    
    private String addressLine2;

    
    private String landmark;

    
    private String cityName;

    
    private String stateName;

    
    private String postalCode;

    
    private String countryName;

}
