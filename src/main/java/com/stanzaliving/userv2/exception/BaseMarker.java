package com.stanzaliving.userv2.exception;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public interface BaseMarker {

    Marker APPLICATION_ERROR = MarkerManager.getMarker("APPLICATION_ERROR");
    Marker API_VALIDATION_ERROR = MarkerManager.getMarker("API_VALIDATION_ERROR");
    Marker REDIS_ERROR = MarkerManager.getMarker("REDIS_ERROR");
    Marker CONNECTION_ERROR = MarkerManager.getMarker("CONNECTION_ERROR");
    Marker NOT_FOUND_ERROR = MarkerManager.getMarker("NOT_FOUND_ERROR");
    Marker OPERATIONS_ERROR = MarkerManager.getMarker("OPERATIONS_ERROR");
    Marker WANDA_API_ERROR = MarkerManager.getMarker("WANDA_API_ERROR");
}
