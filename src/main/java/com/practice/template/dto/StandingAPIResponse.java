package com.practice.template.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StandingAPIResponse {

    @JsonProperty("country_id")
    private Integer countryId;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("league_name")
    private String leagueName;

    @JsonProperty("league_id")
    private String leagueId;

    @JsonProperty("team_id")
    private String teamId;

    @JsonProperty("team_name")
    private String teamName;

    @JsonProperty("overall_league_position")
    private Integer overallLeaguePosition;
}
