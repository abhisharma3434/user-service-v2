package com.stanzaliving.userv2.dto;

import com.stanzaliving.userv2.annotations.Email;
import com.stanzaliving.userv2.annotations.EnsureNumber;
import com.stanzaliving.userv2.entity.AddressEntity;
import com.stanzaliving.userv2.entity.UserProfileEntity;
import com.stanzaliving.userv2.enums.BloodGroup;
import com.stanzaliving.userv2.enums.Gender;
import com.stanzaliving.userv2.enums.MaritalStatus;
import com.stanzaliving.userv2.enums.Nationality;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequestDto {


    @NotBlank(message = "Mobile is mandatory to add new user")
    @EnsureNumber(message = "Mobile Number must contain only numbers", fieldName = "mobile")
    private String mobile;

    @NotBlank(message = "Email is mandatory to add new user")
    @Email
    private String email;

    @NotBlank(message = "ISO Code is mandatory to add new user")
    @Size(min = 2, max = 3, message = "ISO Code must be of 2-4 characters")
    private String isoCode;

    @NotBlank(message = "First Name is mandatory to add new user")
    private String firstName;

    private String middleName;

    private String lastName;
    
    private Gender gender;
    
    private MaritalStatus maritalStatus;
    
    private Nationality nationality;
    
    private BloodGroup bloodGroup;
    
    private LocalDate birthday;

    private AddressEntity address;

    private UserProfileEntity userProfileEntity;
}
