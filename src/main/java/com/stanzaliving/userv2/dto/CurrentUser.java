package com.stanzaliving.userv2.dto;

import com.stanzaliving.userv2.enums.Department;
import com.stanzaliving.userv2.enums.UserType;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {

	private String token;

	private String userId;

	private String userUuid;
	
	private String firstName;

	private String lastName;

	private String mobile;

	private String isoCode;

	private String email;
	
	private UserType userType;
	
	private Department department;

}