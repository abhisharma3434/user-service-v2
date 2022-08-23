package com.stanzaliving.userv2.dto;

import com.stanzaliving.userv2.entity.AddressEntity;
import com.stanzaliving.userv2.entity.UserProfileEntity;
import com.stanzaliving.userv2.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto  extends  AbstractDto{

	private String isoCode;

	private String mobile;

	private Boolean mobileVerified;

	private String email;

	private Boolean emailVerified;

	private String firstName;

	private String middleName;

	private String lastName;

	private Gender gender;

	private MaritalStatus maritalStatus;

	private Nationality nationality;

	private BloodGroup bloodGroup;

	private AddressEntity address;

	private UserProfileEntity userProfileEntity;

}