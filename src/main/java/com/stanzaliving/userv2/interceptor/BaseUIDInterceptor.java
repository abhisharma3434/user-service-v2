package com.stanzaliving.userv2.interceptor;

import com.stanzaliving.userv2.constants.StanzaConstants;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Log4j2
public class BaseUIDInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		String guid = UUID.randomUUID().toString().replace("-", ""); // globally unique identifier
		String luid = UUID.randomUUID().toString().replace("-", ""); // locally unique identifier

		guid = (null != request.getHeader(StanzaConstants.GUID)) ? request.getHeader(StanzaConstants.GUID) : guid;
		MDC.put(StanzaConstants.GUID, guid);
		MDC.put(StanzaConstants.LUID, luid);
		MDC.put(StanzaConstants.REQUEST_PATH, request.getRequestURI());
		MDC.put(StanzaConstants.QUERY_STRING, request.getQueryString());

		// Add Device Info in MDC from request Headers
		MDC.put(StanzaConstants.APP_NAME, request.getHeader("appName"));
		MDC.put(StanzaConstants.APP_VERSION, request.getHeader("appVersion"));
		MDC.put(StanzaConstants.DEVICE, request.getHeader("device"));
		MDC.put(StanzaConstants.PLATFORM, request.getHeader("platform"));
		MDC.put(StanzaConstants.PLATFORM_VERSION, request.getHeader("platformVersion"));

		log.info("RequestReceived URI {} QueryString {} AppVersion {}", request.getRequestURI(), request.getQueryString(), request.getHeader("appversion"));

		request.setAttribute(StanzaConstants.GUID, guid);
		request.setAttribute(StanzaConstants.LUID, luid);

		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		MDC.remove(StanzaConstants.GUID);
		MDC.remove(StanzaConstants.LUID);
		MDC.remove(StanzaConstants.REQUEST_PATH);
		MDC.remove(StanzaConstants.QUERY_STRING);
		MDC.remove(StanzaConstants.REQ_UID);
		MDC.remove(StanzaConstants.REQ_MOBILE);

		// Remove Device Info from MDC
		MDC.remove(StanzaConstants.APP_NAME);
		MDC.remove(StanzaConstants.APP_VERSION);
		MDC.remove(StanzaConstants.DEVICE);
		MDC.remove(StanzaConstants.PLATFORM);
		MDC.remove(StanzaConstants.PLATFORM_VERSION);
	}

}
