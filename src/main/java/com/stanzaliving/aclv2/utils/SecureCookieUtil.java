package com.stanzaliving.aclv2.utils;

import com.stanzaliving.aclv2.constants.SecurityConstants;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Log4j2
@UtilityClass
public class SecureCookieUtil {

	public static Cookie create(String key, String value, Optional<Boolean> isLocalFrontEnd) {
		return create(key, value, isLocalFrontEnd, Optional.of(false));
	}

	public static Cookie create(String key, String value, Optional<Boolean> isLocalFrontEnd, Optional<Boolean> isApp) {
		Cookie cookie = new Cookie(key, value);

		if (isLocalFrontEnd.isPresent() && isLocalFrontEnd.get()) {
			// Setting this externally because secure cookies are not accessible in local/Cross Site JavaScript
			cookie.setSecure(false);
			cookie.setHttpOnly(false);

		} else {

			if (!isApp.isPresent() || !isApp.get()) {
				cookie.setDomain(SecurityConstants.STANZA_DOMAIN);
			}

			cookie.setMaxAge(-1);
			cookie.setSecure(true);
			cookie.setHttpOnly(true);
		}

		cookie.setPath("/");

		log.trace("Adding Cookie [Name: " + cookie.getName() + ", Value: " + cookie.getValue() + ", Domain: " + cookie.getDomain() + "]");

		return cookie;
	}

	public static Cookie expire(Cookie cookie, boolean isLocalFrontEnd) {
		cookie.setMaxAge(0);
		cookie.setValue(null);
		cookie.setPath("/");

		if (!isLocalFrontEnd) {
			cookie.setDomain(SecurityConstants.STANZA_DOMAIN);
		}

		return cookie;
	}

	public static void handleLogOutResponse(HttpServletRequest request, HttpServletResponse response) {

		String frontEnv = request.getHeader(SecurityConstants.FRONT_ENVIRONMENT);
		boolean isLocalFrontEnd = StringUtils.isNotBlank(frontEnv) && SecurityConstants.FRONT_ENVIRONMENT_LOCAL.equals(frontEnv);

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {

				if (SecurityConstants.TOKEN_HEADER_NAME.equals(cookie.getName())) {
					response.addCookie(SecureCookieUtil.expire(cookie, isLocalFrontEnd));
				}
			}
		}
	}

}