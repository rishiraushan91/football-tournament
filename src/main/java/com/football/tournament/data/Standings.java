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
public class Standings
{
    @JsonProperty("country_name")
    private String country_name;

    @JsonProperty("league_id")
    private int league_id;

    @JsonProperty("league_name")
    private String league_name;

    @JsonProperty("team_id")
    private int team_id;

    @JsonProperty("team_name")
    private String team_name;

    @JsonProperty("overall_league_position")
    private int overall_league_position;
}
