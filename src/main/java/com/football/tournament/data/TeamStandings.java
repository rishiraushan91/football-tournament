package com.football.tournament.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamStandings
{
    @JsonProperty("Country Id")
    private int countryId;

    @JsonProperty("Country Name")
    private String countryName;

    @JsonProperty("League Id")
    private int leagueId;

    @JsonProperty("League Name")
    private String leagueName;

    @JsonProperty("Team Id")
    private int teamId;

    @JsonProperty("Team Name")
    private String teamName;

    @JsonProperty("Overall League Position")
    private int overallLeaguePosition;

}
