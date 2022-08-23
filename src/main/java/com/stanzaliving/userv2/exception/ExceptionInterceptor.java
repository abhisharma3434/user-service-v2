package com.stanzaliving.userv2.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.stanzaliving.userv2.annotations.SendExceptionToSlack;
import com.stanzaliving.userv2.constants.StanzaConstants;
import com.stanzaliving.userv2.dto.ResponseDto;


import com.stanzaliving.userv2.utils.StanzaUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.PropertyAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@Log4j2
@RestControllerAdvice
public class ExceptionInterceptor {

	/************************ Framework Specific Exceptions ************************/

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		String exceptionId = getExceptionId();
		log.error("Got MethodArgumentNotValidException for exceptionId: {} with Message: {}", exceptionId, e.getMessage());

		FieldError fieldError = e.getBindingResult().getFieldError();

		String errorMessage = fieldError.getDefaultMessage();

		if (StringUtils.isBlank(errorMessage)) {
			errorMessage = "Invalid Value: " + fieldError.getRejectedValue() + " for Field: " + fieldError.getField();
		}

		return ResponseDto.failure(errorMessage, exceptionId);

	}

	@ExceptionHandler(PropertyAccessException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handlePropertyAccessException(PropertyAccessException e) {

		String exceptionId = getExceptionId();
		log.error("Got PropertyAccessException for exceptionId: {}", exceptionId, e);

		String errorMessgae = "Incorrect Value For Parameter: " + e.getPropertyName() + " in Request. Dirty Value: " + e.getValue();

		return ResponseDto.failure(errorMessgae, exceptionId);
	}

	@ExceptionHandler(StanzaSecurityException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleStanzaSecurityException(StanzaSecurityException e) {

		String exceptionId = StanzaUtils.generateUniqueId();
		log.error("Got StanzaSecurityException for exceptionId: " + exceptionId, e);

		String errorMessgae = e.getMessage();

		return ResponseDto.failure(errorMessgae, exceptionId);
	}



	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {

		String exceptionId = getExceptionId();
		log.error("Got MethodArgumentTypeMismatchException for exceptionId: {} with Message: {}", exceptionId, e.getMessage());

		String errorMessage = "Incorrect Type For Parameter: " + e.getName() + " in Request. Expected: " + e.getRequiredType() + ", Found: " + e.getValue();

		return ResponseDto.failure(errorMessage, exceptionId);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {

		String exceptionId = getExceptionId();
		log.error("Got MissingServletRequestParameterException for exceptionId: {} ", exceptionId, e);

		String errorMessage = "Missing Parameter: " + e.getParameterName() + " in Request";

		return ResponseDto.failure(errorMessage, exceptionId);
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleBindException(BindException e) {

		String exceptionId = getExceptionId();
		log.error("Got MissingServletRequestParameterException for exceptionId: " + exceptionId, e);

		BindingResult bindingResult = e.getBindingResult();

		StringBuilder fieldErrors = new StringBuilder();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			fieldErrors = fieldErrors.append("{FieldName: ").append(fieldError.getField()).append(", RejectedValue: ").append(fieldError.getRejectedValue()).append("} ");
		}

		String errorMessage = "Found: " + bindingResult.getErrorCount() + " Errors in Request. Fields With Error: [" + fieldErrors.toString() + "]";

		return ResponseDto.failure(errorMessage, exceptionId);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

		String exceptionId = getExceptionId();
		log.error("Got HttpMessageNotReadableException for exceptionId: {}", exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleInvalidFormatException(InvalidFormatException e) {

		String exceptionId = getExceptionId();
		log.error("Got InvalidFormatException for exceptionId: " + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {

		String exceptionId = getExceptionId();
		log.error("Got SQLIntegrityConstraintViolationException for exceptionId: {}", exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleConstraintViolationException(ConstraintViolationException e) {

		String exceptionId = getExceptionId();
		log.error("Got ConstraintViolationException for exceptionId: {}", exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleDataIntegrityViolationException(DataIntegrityViolationException e) {

		String exceptionId = getExceptionId();
		log.error("Got DataIntegrityViolationException for exceptionId: {}", exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleMultipartException(MultipartException e) {

		String exceptionId = getExceptionId();
		log.error("Got MultipartException for exceptionId: {} with Message:", exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	/************************ Application Specific Exceptions ************************/

	@ExceptionHandler(NoRecordException.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public <T> ResponseDto<T> handleNoRecordException(NoRecordException e) {

		String exceptionId = getExceptionId();
		log.error("Got NoRecordException for exceptionId: {} with Message: {}", exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(MappingNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public <T> ResponseDto<T> handleMappingNotFoundException(MappingNotFoundException e) {

		String exceptionId = getExceptionId();
		log.error("Got MappingNotFoundException for exceptionId: {} with Message: {}", exceptionId, e.getMessage());

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(RecordExistsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public <T> ResponseDto<T> handleRecordExistsException(RecordExistsException e) {

		String exceptionId = getExceptionId();
		log.error("Got RecordExistsException for exceptionId: {} and message: {}", exceptionId, e.getMessage());

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(PreconditionFailedException.class)
	@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
	public <T> ResponseDto<T> handlePreconditionFailedException(PreconditionFailedException e) {

		String exceptionId = getExceptionId();
		log.error("Got PreconditionFailedException for exceptionId: {} and message: {}", exceptionId, e.getMessage());

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(RequestAlreadyApprovedException.class)
	@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
	public <T> ResponseDto<T> handleRequestAlreadyApprovedException(RequestAlreadyApprovedException e) {

		String exceptionId = getExceptionId();
		log.error("Got RequestAlreadyApprovedException for exceptionId: {} and message: {}", exceptionId, e.getMessage());

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(RequestAlreadyRejectedException.class)
	@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
	public <T> ResponseDto<T> handleRequestAlreadyRejectedException(RequestAlreadyRejectedException e) {

		String exceptionId = getExceptionId();
		log.error("Got RequestAlreadyRejectedException for exceptionId: {} and message: {}", exceptionId, e.getMessage());

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(RequestInProgressException.class)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public <T> ResponseDto<T> handleRequestInProgressException(RequestInProgressException e) {
		String exceptionId = getExceptionId();

		log.error("Got RequestInProgressException for exceptionId: {} with Message: {}", exceptionId, e.getMessage());
		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(TransactionFailException.class)
	@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
	public <T> ResponseDto<T> handleTransactionFailException(TransactionFailException e) {
		String exceptionId = getExceptionId();

		log.error("Got TransactionFailException for exceptionId: {} with Message: {}", exceptionId, e.getMessage());
		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(TransactionValidationException.class)
	@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
	public <T> ResponseDto<T> handleTransactionValidationException(TransactionValidationException e) {
		String exceptionId = getExceptionId();

		log.error("Got TransactionFailException for exceptionId: {} with Message: {}", exceptionId, e.getMessage());
		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleInvalidDataAccessException(InvalidDataAccessApiUsageException e) {
		String exceptionId = getExceptionId();
		log.error("Got InvalidDataAccessApiUsageException for exceptionId: " + exceptionId, e);
		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	/************************ Stanza Specific Exceptions ************************/

	@ExceptionHandler(AuthException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public <T> ResponseDto<T> handleAuthException(AuthException e) {

		String exceptionId = getExceptionId();
		log.error("Got AuthException for exceptionId: {}", exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(StanzaException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleStanzaException(StanzaException e) {

		String exceptionId = getExceptionId();
		log.error("Got StanzaException for exceptionId: {}", exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleIllegalArgumentException(IllegalArgumentException e) {

		String exceptionId = getExceptionId();
		log.error("Got IllegalArgumentException for exceptionId: {} with Message {}", exceptionId, e.getMessage());

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(ApiValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleApiValidationException(ApiValidationException e) {

		String exceptionId = getExceptionId();
		log.error("Got ApiValidationException for exceptionId: {} With Message: {}", exceptionId, e.getMessage());

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}
	
	@ExceptionHandler(UserValidationException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public <T> ResponseDto<T> handleUserValidationException(UserValidationException e) {

		String exceptionId = getExceptionId();
		log.error("Got UserValidationException for exceptionId: {} With Message: {}", exceptionId, e.getMessage());

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(StanzaHttpException.class)
	@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleStanzaHttpException(StanzaHttpException e) {

		String exceptionId = getExceptionId();
		log.error("Got StanzaHttpException for exceptionId: {}", exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@SendExceptionToSlack
	public <T> ResponseDto<T> handleException(Exception ex) {

		String exceptionId = getExceptionId();
		log.error("Got un-handled exception for exceptionId: {}", exceptionId, ex);

		return ResponseDto.failure(ex.getMessage(), exceptionId);
	}

	private String getExceptionId() {
		String exceptionId = MDC.get(StanzaConstants.GUID);

		if (StringUtils.isBlank(exceptionId)) {
			exceptionId = StanzaUtils.generateUniqueId();
		}

		return exceptionId;
	}

}
