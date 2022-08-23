package com.stanzaliving.userv2.adapters;

import com.stanzaliving.userv2.dto.AddUserRequestDto;
import com.stanzaliving.userv2.dto.UserDto;
import com.stanzaliving.userv2.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserAdapter {

    public UserDto getUserDto(UserEntity userEntity)
    {
        return UserDto
                .builder()
                .uuid(userEntity.getUuid())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .isoCode(userEntity.getIsoCode())
                .mobile(userEntity.getMobile())
                .mobileVerified(userEntity.isMobileVerified())
                .email(userEntity.getEmail())
                .emailVerified(userEntity.isEmailVerified())
                .firstName(userEntity.getFirstName())
                .middleName(userEntity.getMiddleName())
                .lastName(userEntity.getLastName())
                .gender(userEntity.getGender())
                .maritalStatus(userEntity.getMaritalStatus())
                .nationality(userEntity.getNationality())
                .bloodGroup(userEntity.getBloodGroup())
                .address(userEntity.getAddress())
                .userProfileEntity(userEntity.getUserProfileEntity())
                .build();
    }

    public UserEntity getUserProfileEntity(AddUserRequestDto addUserRequestDto)
    {
        return UserEntity
                .builder()
                .isoCode(addUserRequestDto.getIsoCode())
                .mobile(addUserRequestDto.getMobile())
                .email(addUserRequestDto.getEmail())
                .firstName(addUserRequestDto.getFirstName())
                .middleName(addUserRequestDto.getMiddleName())
                .lastName(addUserRequestDto.getLastName())
                .gender(addUserRequestDto.getGender())
                .maritalStatus(addUserRequestDto.getMaritalStatus())
                .nationality(addUserRequestDto.getNationality())
                .bloodGroup(addUserRequestDto.getBloodGroup())
                .birthday(addUserRequestDto.getBirthday())
                .address(addUserRequestDto.getAddress())
                .userProfileEntity(addUserRequestDto.getUserProfileEntity())
                .build();
    }
}
