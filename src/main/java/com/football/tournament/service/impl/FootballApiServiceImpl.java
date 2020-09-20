package com.football.tournament.service.impl;

import com.football.tournament.data.Country;
import com.football.tournament.data.Competitions;
import com.football.tournament.data.Standings;
import com.football.tournament.service.FootballApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Service
public class FootballApiServiceImpl implements FootballApiService
{
    private final String BASE_URL = "https://apiv2.apifootball.com/";
    private final String ACTION = "action";
    private final String API_KEY = "APIkey";
    private final String API_KEY_VALUE = "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";

    private final HttpServiceImpl httpService;

    @Autowired
    public FootballApiServiceImpl(HttpServiceImpl httpService)
    {
        this.httpService = httpService;
    }


    @Override
    public List<Country> getAllCountries(String apiKey)
    {
        HttpHeaders headers = getCommonHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL);
        builder.queryParam(ACTION, "get_countries");
        builder.queryParam(API_KEY, apiKey);

        ResponseEntity<List<Country>> listResponseEntity = httpService.httpPostGetAndDelete(builder.toUriString(),
                HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Country>>()
                {
                });

        log.info("all countries: {}", listResponseEntity.getBody());

        return listResponseEntity.getBody();
    }

    @Override
    public List<Competitions> getCompetitionsByCountryId(int countryId, String apiKey)
    {
        HttpHeaders headers = getCommonHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL);
        builder.queryParam(ACTION, "get_leagues");
        builder.queryParam("country_id", countryId);
        builder.queryParam(API_KEY, apiKey);

        ResponseEntity<List<Competitions>> listResponseEntity = httpService.httpPostGetAndDelete(builder.toUriString(),
                HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Competitions>>()
                {
                });

        log.info("competitions by country: {}", listResponseEntity.getBody());

        return listResponseEntity.getBody();
    }

    @Override
    public List<Standings> getStandingsByLeagueId(int leagueId, String apiKey)
    {
        HttpHeaders headers = getCommonHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL);
        builder.queryParam(ACTION, "get_standings");
        builder.queryParam("league_id", leagueId);
        builder.queryParam(API_KEY, apiKey);

        ResponseEntity<List<Standings>> listResponseEntity = httpService.httpPostGetAndDelete(builder.toUriString(),
                HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Standings>>()
                {
                });

        log.info("Standings by league: {}", listResponseEntity.getBody());

        return listResponseEntity.getBody();
    }

    private HttpHeaders getCommonHeaders()
    {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
