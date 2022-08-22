package com.stanzaliving.aclv2.exception;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter
@ToString
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserValidationException extends RuntimeException {

   private static final  long  serialVersionUID = -407525021863698707L;

   private final String message;
   private final String code;
   private final Throwable error;

   public UserValidationException() {
      this(StringUtils.EMPTY, StringUtils.EMPTY, null);
   }

   public UserValidationException(String message) {
      this(message, StringUtils.EMPTY, null);
   }

   public UserValidationException(Throwable cause) {
      this(StringUtils.EMPTY, StringUtils.EMPTY, cause);
   }

   public UserValidationException(String message, String code) {
      this(message, code, null);
   }

   public UserValidationException(String message, Throwable cause) {
      this(message, StringUtils.EMPTY, cause);
   }

   public UserValidationException(String message, String code, Throwable error) {
      this.code = code;
      this.message = message;
      this.error = error;
   }
}