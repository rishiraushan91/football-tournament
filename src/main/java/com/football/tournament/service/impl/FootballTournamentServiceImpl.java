package com.football.tournament.service.impl;

import com.football.tournament.data.Competitions;
import com.football.tournament.data.Country;
import com.football.tournament.data.Standings;
import com.football.tournament.data.TeamStandings;
import com.football.tournament.exception.CustomException;
import com.football.tournament.service.FootballApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FootballTournamentServiceImpl
{
    private final FootballApiService footballApiService;

    @Autowired
    public FootballTournamentServiceImpl(FootballApiService footballApiService)
    {
        this.footballApiService = footballApiService;
    }

    public TeamStandings getOverallTeamPositionByCountryAndLeagueAndTeam(String leagueName, String countryName,
                                                                         String teamName, String apiKey)
    {
        log.info("[Started] getOverallTeamPositionByCountryAndLeagueAndTeam");

        List<Country> countries = footballApiService.getAllCountries(apiKey);
        Country country = countries.stream().filter(e -> e.getCountry_name().equalsIgnoreCase(countryName))
                .findAny()
                .orElse(null);
        if (country == null)
        {
            throw new CustomException(String.format("No country : %s found (please check your plan)!!", countryName));
        }

        List<Competitions> competitions = footballApiService.getCompetitionsByCountryId(country.getCountry_id(),
                apiKey);
        Competitions competition = competitions.stream().filter(e -> e.getLeague_name().equalsIgnoreCase(leagueName))
                .findAny()
                .orElse(null);
        if (competition == null)
        {
            throw new CustomException(String.format("No league : %s found for country: %s (please check your plan)!!"
                    , leagueName, countryName));
        }

        List<Standings> standings = footballApiService.getStandingsByLeagueId(competition.getLeague_id(), apiKey);
        Standings standing = standings.stream().filter(e -> e.getTeam_name().equalsIgnoreCase(teamName))
                .findAny()
                .orElse(null);
        if (standing == null)
        {
            throw new CustomException(String.format("No team : %s found for league: %s & country: %s (please check " +
                    "your plan)!!", teamName, leagueName, countryName));
        }

        return TeamStandings.builder()
                .countryId(country.getCountry_id())
                .countryName(country.getCountry_name())
                .leagueId(competition.getLeague_id())
                .leagueName(competition.getLeague_name())
                .teamId(standing.getTeam_id())
                .teamName(standing.getTeam_name())
                .overallLeaguePosition(standing.getOverall_league_position())
                .build();
    }
}
