package com.stanzaliving.userv2.controller;

import com.stanzaliving.userv2.dto.AddUserRequestDto;
import com.stanzaliving.userv2.dto.ResponseDto;
import com.stanzaliving.userv2.dto.UserDto;
import com.stanzaliving.userv2.service.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("internal/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("add")
    public ResponseDto<UserDto> addUser(@RequestBody @Valid AddUserRequestDto addUserRequestDto) {

        UserDto userDto = userServiceImpl.addUser(addUserRequestDto);

        log.info("Added new user with id: " + userDto.getUuid());

        return ResponseDto.success("New User Created", userDto);
    }

    @PostMapping("deactivate/{userId}")
    public ResponseDto<Boolean> addUser(@PathVariable String userId) {

        boolean status = userServiceImpl.updateUserStatus(userId, false);

        log.info("Deactivated user with id: " + userId);

        return (status) ? ResponseDto.success(status) : ResponseDto.failure("Unable to deactivate user");
    }



}
