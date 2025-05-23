package com.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class GooglePlacesApiDownException extends RuntimeException {
    public GooglePlacesApiDownException(String googlePlacesApiIsDown) {
    }
}
