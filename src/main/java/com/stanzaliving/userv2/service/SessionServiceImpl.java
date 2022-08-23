package com.stanzaliving.userv2.service;

import java.util.List;
import java.util.Objects;

import com.stanzaliving.userv2.constants.SecurityConstants;
import com.stanzaliving.userv2.constants.UserErrorCodes;
import com.stanzaliving.userv2.db.service.UserSessionDbService;
import com.stanzaliving.userv2.dto.SessionRequestDto;
import com.stanzaliving.userv2.dto.UserDto;
import com.stanzaliving.userv2.entity.UserEntity;
import com.stanzaliving.userv2.entity.UserSessionEntity;
import com.stanzaliving.userv2.exception.AuthException;
import com.stanzaliving.userv2.exception.StanzaException;
import com.stanzaliving.userv2.service.UserServiceImpl;
import com.stanzaliving.userv2.utils.StanzaUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class SessionServiceImpl {

	@Value("${bcrypt.salt}")
	private String bcryptSalt;

	@Autowired
	private UserSessionDbService userSessionDbService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	public UserSessionEntity createUserSession(UserDto userDto, String token) {

		log.info("Creating Session for User: " + userDto.getUuid());

		UserSessionEntity userSessionEntity =
				UserSessionEntity.builder()
						.userId(userDto.getUuid())
						.token(getBcryptPassword(token))
//						.userType(userDto.getUserType())
						.build();

		userSessionEntity = userSessionDbService.save(userSessionEntity);

		log.info("Created Session: " + userSessionEntity.getUuid() + " for User: " + userDto.getUuid());

		return userSessionEntity;
	}


	public UserSessionEntity refreshUserSession(String token) {
		UserSessionEntity userSessionEntity = null;
		try {
			log.info("Request received to refresh user session");

			userSessionEntity = getUserSessionByToken(token);

			if (Objects.isNull(userSessionEntity)) {
				log.error("No User Session Found");
				throw new AuthException("No User Session Found!! Please Login!!", UserErrorCodes.SESSION_NOT_FOUND);
			}

			log.info("User session found for user : {} . Getting active user ...", userSessionEntity.getUserId());

			UserDto user = userServiceImpl.getActiveUserByUuid(userSessionEntity.getUserId());

			log.info("Refresh User Session: " + userSessionEntity.getUuid() + " for User: " + user.getUuid());

			String newToken = StanzaUtils.generateUniqueId();

			userSessionEntity.setToken(getBcryptPassword(newToken));
			userSessionEntity.setStatus(true);

			log.info("Updating userSessionEntity {} for user {}", userSessionEntity.getUuid(),
					userSessionEntity.getUserId());
			userSessionEntity = userSessionDbService.save(userSessionEntity);
			log.info("Successfully updated userSessionEntity {} for user {}", userSessionEntity.getUuid(),
					userSessionEntity.getUserId());

			return userSessionEntity;
		} catch (Exception e) {
			String userId = Objects.nonNull(userSessionEntity)? userSessionEntity.getUserId(): StringUtils.EMPTY;
			log.error("Exception while refreshing user session : {} for user : {}", e.getMessage(), userId);
			throw new StanzaException(e);
		}
	}


	public UserSessionEntity getUserSessionByToken(String token) {
		log.info("Request received for getUserSessionByToken");
		return userSessionDbService.getUserSessionForToken(getBcryptPassword(token));
	}


	public void removeUserSession(String token) {

		UserSessionEntity userSessionEntity = getUserSessionByToken(token);

		if (Objects.isNull(userSessionEntity)) {
			throw new AuthException("No User Session Found!! Please Login!!", UserErrorCodes.SESSION_NOT_FOUND);
		}

		log.info("Invalidating User Session: " + userSessionEntity.getUuid() + " for User: " + userSessionEntity.getUserId());

		userSessionEntity.setStatus(false);
		userSessionDbService.save(userSessionEntity);
	}


	public UserSessionEntity updateUserSession(UserSessionEntity userSessionEntity) {
		return userSessionDbService.save(userSessionEntity);
	}


    public void createSession(SessionRequestDto sessionRequestDto) {
		List<UserEntity> userEntityList = userServiceImpl.getUserByEmail(sessionRequestDto.getEmail().trim());
		if (CollectionUtils.isEmpty(userEntityList)) {
			log.info("User not found for email: " + sessionRequestDto.getEmail());			//if all users of venta will be created in user service, then throw exception
			return;
		}

		String token = sessionRequestDto.getToken().replace(SecurityConstants.VENTA_TOKEN_PREFIX, "");
		UserSessionEntity userSessionEntity = getUserSessionByToken(token);
		if (null != userSessionEntity) {
			log.info("Session already exist with token {}", token);
			return;
		}

		UserEntity userEntity = userEntityList.get(0);
		userSessionEntity = UserSessionEntity.builder()
						.userId(userEntity.getUuid())
						.token(getBcryptPassword(token))
//						.userType(userEntity.getUserType())
						.build();
		userSessionEntity = userSessionDbService.save(userSessionEntity);

		log.info("Created Manual Session: " + userSessionEntity.getUuid() + " for User: " + userEntity.getUuid());
    }

    private String getBcryptPassword(String password) {
		return BCrypt.hashpw(password, bcryptSalt);
	}

}