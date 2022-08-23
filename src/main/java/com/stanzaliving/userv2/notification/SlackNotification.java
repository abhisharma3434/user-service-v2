package com.stanzaliving.userv2.notification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stanzaliving.userv2.http.StanzaRestClient;
import com.stanzaliving.userv2.utils.SlackUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;


import lombok.extern.log4j.Log4j2;


@Log4j2
public class SlackNotification {

	private StanzaRestClient restClient;

	public SlackNotification(StanzaRestClient stanzaRestClient) {
		this.restClient = stanzaRestClient;
	}

	public String sendPushNotificationRequest(String message, String endUrl) {

		String path = UriComponentsBuilder.fromPath(endUrl).toUriString();

		final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

		final HttpHeaders headerParams = new HttpHeaders();

		final String[] accepts = { "*/*" };
		final List<MediaType> accept = restClient.selectHeaderAccept(accepts);

		ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {
		};

		Map<String, String> map = new HashMap<>();
		map.put("text", message);

		try {
			return restClient.invokeAPI(path, HttpMethod.POST, queryParams, map, headerParams, accept, returnType);
		} catch (Exception e) {
			log.error("Exception caught while sending message on Slack : ", e);
		}

		return null;
	}

	public String sendExceptionNotificationRequest(Exception exception, String endUrl) {

		if (log.isTraceEnabled()) {
			log.trace("Send exception notification on Slack request exception: {}", exception);
		}

		StringBuilder slackMessage = SlackUtil.createMessage(exception);

		return sendPushNotificationRequest(slackMessage.toString(), endUrl);
	}

	public String sendExceptionNotificationRequest(String springApplicationName, Exception exception, String endUrl) {

		if (log.isTraceEnabled()) {
			log.trace("Send exception notification on Slack request exception: {}", exception);
		}

		StringBuilder slackMessage = new StringBuilder();
		slackMessage.append("Application Name:")
				.append(springApplicationName)
				.append("\n ").append(SlackUtil.createMessage(exception));

		return sendPushNotificationRequest(slackMessage.toString(), endUrl);
	}
}