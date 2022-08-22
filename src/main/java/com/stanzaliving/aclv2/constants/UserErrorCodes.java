/**
 * 
 */
package com.stanzaliving.aclv2.constants;

import lombok.experimental.UtilityClass;


@UtilityClass
public class UserErrorCodes {

	public static final String USER_NOT_EXISTS = "USER_NOT_EXISTS";
	public static final String USER_ACCOUNT_INACTIVE = "USER_ACCOUNT_INACTIVE";

	public static final String SESSION_EXPIRED = "SESSION_EXPIRED";
	public static final String SESSION_NOT_FOUND = "SESSION_NOT_FOUND";
	public static final String ERROR_SENDING_SMS = "ERROR_SENDING_SMS";

	@UtilityClass
	public class Otp {

		public static final String INVALID_OTP = "INVALID_OTP";
		public static final String OTP_NOT_FOUND = "OTP_NOT_FOUND";
		public static final String ERROR_SENDING_OTP = "ERROR_SENDING_OTP";
		public static final String OTP_RESEND_NOT_PERMITTED = "OTP_RESEND_NOT_PERMITTED";
		public static final String OTP_RESEND_LIMIT_EXHAUSTED = "OTP_RESEND_LIMIT_EXHAUSTED";
		public static final String OTP_VALIDATE_LIMIT_EXHAUSTED = "OTP_VALIDATE_LIMIT_EXHAUSTED";
	}
}