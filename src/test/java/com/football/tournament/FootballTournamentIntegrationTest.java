package com.football.tournament;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FootballTournamentIntegrationTest
{
    private final Logger logger = LoggerFactory.getLogger(FootballTournamentIntegrationTest.class);

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Value("${api.key}")
    private String APIkey;

    private final String BASE_URL = "http://localhost:48080/football-tournament/standings/league/%s/country/%s/team/%s";
    private final String API_KEY = "APIkey";

    @BeforeEach
    public void init()
    {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testFootballTournamentAllLayer() throws Exception
    {
        String league = "Championship";
        String country = "England";
        String team = "Luton";
        MvcResult mvcResult = mockMvc.perform(get(getBaseUrl(league, country, team)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        logger.info("Team Overall League Position: {}", mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void testFootballTournamentAllLayerCountryNotFound() throws Exception
    {
        String league = "Championship";
        String country = "Polland";
        String team = "Luton";
        MvcResult mvcResult = mockMvc.perform(get(getBaseUrl(league, country, team)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        logger.info("Team Overall League Position: {}", mvcResult.getResponse().getContentAsString());
    }


    public String getBaseUrl(String league, String country, String team)
    {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format(BASE_URL, league, country, team));
        builder.queryParam(API_KEY, APIkey);

        return builder.toUriString();
    }

}
