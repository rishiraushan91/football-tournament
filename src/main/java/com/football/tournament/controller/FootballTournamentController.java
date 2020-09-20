package com.football.tournament.controller;

import com.football.tournament.data.TeamStandings;
import com.football.tournament.service.impl.FootballTournamentServiceImpl;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/football-tournament")
public class FootballTournamentController
{
    private final FootballTournamentServiceImpl footballTournamentService;

    @Autowired
    public FootballTournamentController(FootballTournamentServiceImpl footballTournamentService)
    {
        this.footballTournamentService = footballTournamentService;
    }


    @RequestMapping(value = "standings/league/{league_name}/country/{country_name}/team/{team_name}")
    public ResponseEntity<TeamStandings> getOverallTeamPositionByCountryAndLeagueAndTeam(
            @RequestParam(name = "APIkey") String apiKey,
            @PathVariable(name = "league_name") String leagueName,
            @PathVariable(name = "country_name") String countryName,
            @PathVariable(name = "team_name") String teamName)
    {
        log.info("apiKey: {}", apiKey);
        log.info("leagueName: {}", leagueName);
        log.info("countryName: {}", countryName);
        log.info("teamName: {}", teamName);
        Preconditions.checkArgument(StringUtils.isNotBlank(apiKey), "APIkey cannot be null or empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(leagueName), "league_name cannot be null or empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(countryName), "country_name cannot be null or empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(teamName), "team_name cannot be null or empty");

        return new ResponseEntity<>(footballTournamentService.getOverallTeamPositionByCountryAndLeagueAndTeam(
                leagueName, countryName, teamName, apiKey), HttpStatus.OK);
    }
}
