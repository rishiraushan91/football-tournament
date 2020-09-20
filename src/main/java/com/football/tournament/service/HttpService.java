package com.football.tournament.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 * Service interface to Perform HttpRequests
 */
public interface HttpService
{

    <T> ResponseEntity<T> httpPostGetAndDelete(String url,
                                           HttpMethod httpMethod,
                                           HttpEntity<?> entity,
                                               ParameterizedTypeReference<T> responseType);

    <T> ResponseEntity<T> performHttpRequest(String url,
                                         HttpMethod method,
                                         HttpEntity<?> entity,
                                             ParameterizedTypeReference<T> responseType);
}
