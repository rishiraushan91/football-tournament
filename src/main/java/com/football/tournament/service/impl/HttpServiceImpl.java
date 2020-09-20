package com.football.tournament.service.impl;


import com.football.tournament.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Service to Perform HttpRequests
 */
@Slf4j
@Service
public class HttpServiceImpl implements HttpService
{
    private final RestTemplate restTemplate;

    @Autowired
    public HttpServiceImpl()
    {
        this.restTemplate = new RestTemplate();
    }


    @Override
    public <T> ResponseEntity<T> httpPostGetAndDelete(String url, HttpMethod httpMethod,
                                                      HttpEntity<?> entity, ParameterizedTypeReference<T> responseType)
    {
        log.info("url: {}", url);
        log.info("entity.getHeaders(): {}", entity.getHeaders());
        log.info("entity.getBody(): {}", entity.getBody());
        return performHttpRequest(url, httpMethod, entity, responseType);
    }

    @Override
    public <T> ResponseEntity<T> performHttpRequest(String url, HttpMethod method, HttpEntity<?> entity,
                                                    ParameterizedTypeReference<T> responseType)
    {
        return restTemplate.exchange(url, method, entity, responseType);
    }
}
