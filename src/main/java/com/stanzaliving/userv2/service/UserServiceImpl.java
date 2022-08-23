package com.stanzaliving.userv2.service;

import com.stanzaliving.userv2.adapters.UserAdapter;
import com.stanzaliving.userv2.db.service.UserDbService;
import com.stanzaliving.userv2.dto.AddUserRequestDto;
import com.stanzaliving.userv2.dto.UserDto;
import com.stanzaliving.userv2.entity.UserEntity;
import com.stanzaliving.userv2.entity.UserProfileEntity;
import com.stanzaliving.userv2.exception.ApiValidationException;
import com.stanzaliving.userv2.exception.UserValidationException;
import com.stanzaliving.userv2.utils.PhoneNumberUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class UserServiceImpl {

    @Autowired
    UserDbService userDbService;

    public UserDto addUser(AddUserRequestDto addUserRequestDto) {

            if (!PhoneNumberUtils.isValidMobileForCountry(addUserRequestDto.getMobile(), addUserRequestDto.getIsoCode())) {
                log.error("Number: " + addUserRequestDto.getMobile() + " and ISO: " + addUserRequestDto.getIsoCode()
                        + " doesn't appear to be a valid mobile combination");
                throw new ApiValidationException("Mobile Number and ISO Code combination not valid");
            }

            UserEntity userEntity = userDbService.findByMobile(addUserRequestDto.getMobile());
            if(Objects.nonNull(userEntity))
            {
                UserProfileEntity userProfileEntity = userEntity.getUserProfileEntity();
                if(Objects.nonNull(userProfileEntity) && !Objects.isNull(userProfileEntity.getEmployeeEntity())) {
                    if(Objects.isNull(addUserRequestDto.getUserProfileEntity()) || Objects.isNull(addUserRequestDto.getUserProfileEntity().getEmployeeEntity()))
                    {
                        throw new ApiValidationException("No new profile to be added");
                    }
                    userProfileEntity.setEmployeeEntity(addUserRequestDto.getUserProfileEntity().getEmployeeEntity());
                    userDbService.save(userEntity);
                }
                else
                    throw new ApiValidationException("User already exists");
            }
            userEntity = userDbService.save(UserAdapter.getUserProfileEntity(addUserRequestDto));
            return UserAdapter.getUserDto(userEntity);
    }

    public UserDto getActiveUserByUuid(String userUuid) {

        UserEntity userEntity = userDbService.findByUuid(userUuid);

        if (Objects.isNull(userEntity)) {
            log.error("User Not Found with Uuid: {}", userUuid);
            throw new UserValidationException("User Not Found with Uuid: " + userUuid);
        }

        if (!userEntity.isStatus()) {
            log.error("User Account is Disabled for Uuid : {}", userUuid);
            throw new UserValidationException("User Account is Disabled for Uuid " + userUuid);
        }

        log.info("Found User: " + userEntity.getUuid() );

        return UserAdapter.getUserDto(userEntity);
    }

    public List<UserEntity> getUserByEmail(String email) {
        return userDbService.findByEmail(email);
    }

    public boolean updateUserStatus(String userId, boolean status) {
        UserEntity user = userDbService.findByUuidAndStatus(userId, !status);
        if (user == null) {
            throw new ApiValidationException("User either does not exist or user is already in desired state.");
        }

        user.setStatus(status);
        userDbService.save(user);
        return true;
    }
}
