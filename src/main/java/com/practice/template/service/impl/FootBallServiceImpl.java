package com.practice.template.service.impl;

import com.practice.template.config.FootBallAPIConfig;
import com.practice.template.dto.LeagueData;
import com.practice.template.dto.StandingAPIResponse;
import com.practice.template.exception.SearchNotException;
import com.practice.template.service.FootBallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class FootBallServiceImpl implements FootBallService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FootBallAPIConfig footBallAPIConfig;

    @Override
    public StandingAPIResponse standing(String countryName, String leagueName, String teamName) {
        StandingAPIResponse standingsResponse = new StandingAPIResponse();
        Integer countryId = 0;
        Integer leagueId = 0;
        String url = "https://apiv2.apifootball.com/?action=get_leagues&country_id=41&APIkey=" + footBallAPIConfig.getKey();
        log.info("test");
        ResponseEntity<List<LeagueData>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LeagueData>>() {
                });
        List<LeagueData> leagueDataList = response.getBody();

        for (LeagueData leagueData : leagueDataList) {
            if (leagueData.getLeagueName().equalsIgnoreCase(leagueName) &&
                    leagueData.getCountryName().equalsIgnoreCase(countryName))
                leagueId = leagueData.getLeagueID();
            countryId = leagueData.getCountryId();
        }

        if (leagueId == 0) {
            log.info("No Matching league found with the league name {} and country name {}", leagueName,
                    countryName);
            throw new SearchNotException("No Matching league found");
        } else {

            String urlStanding = "https://apiv2.apifootball.com/?action=get_standings&league_id=" + leagueId + "&APIkey=" + footBallAPIConfig.getKey();
            ResponseEntity<List<StandingAPIResponse>> responseStandingAPI = restTemplate.exchange(
                    urlStanding,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<StandingAPIResponse>>() {
                    });
            List<StandingAPIResponse> standingAPIResponseList = responseStandingAPI.getBody();
            for (StandingAPIResponse standingAPIResponse : standingAPIResponseList) {
                if (standingAPIResponse.getTeamName().equalsIgnoreCase(teamName))
                    standingsResponse = standingAPIResponse;
            }

            standingsResponse.setCountryId(countryId);
            log.info("league id {} ", leagueId);
        }
        return standingsResponse;

    }
}
