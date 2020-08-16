package com.practice.template.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LeagueData {

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("country_id")
    private Integer countryId;

    @JsonProperty("league_name")
    private String leagueName;

    @JsonProperty("league_id")
    private Integer leagueID;

}
