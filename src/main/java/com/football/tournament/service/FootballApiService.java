package com.football.tournament.service;

import com.football.tournament.data.Country;
import com.football.tournament.data.Competitions;
import com.football.tournament.data.Standings;

import java.util.List;

public interface FootballApiService
{
    List<Country> getAllCountries(String apiKey);

    List<Competitions> getCompetitionsByCountryId(int countryId, String apiKey);

    List<Standings> getStandingsByLeagueId(int leagueId, String apiKey);
}
