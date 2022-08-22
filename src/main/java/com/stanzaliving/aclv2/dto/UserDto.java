package com.stanzaliving.aclv2.dto;

import com.stanzaliving.location.enums.Department;
import com.stanzaliving.location.enums.UserType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends AbstractDto {

	private String isoCode;

	private String mobile;

	private Boolean mobileVerified;

	private String email;

	private Boolean emailVerified;

	private UserType userType;

	private Department department;

	private String departmentName;
	
	private String firstName;

	private String lastName;
	
	private int otp;

}