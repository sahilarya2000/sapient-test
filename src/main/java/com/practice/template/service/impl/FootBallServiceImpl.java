package com.practice.template.service.impl;

import com.practice.template.dto.LeagueData;
import com.practice.template.dto.StandingAPIResponse;
import com.practice.template.service.FootBallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class FootBallServiceImpl implements FootBallService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public StandingAPIResponse standing(String countryName, String leagueName, String teamName) {
        StandingAPIResponse standingsResponse = null;
        Integer countryId = 0;
        Integer leagueId = 0;
        String url = "https://apiv2.apifootball.com/?action=get_leagues&country_id=41&APIkey=4ea7cecefdc19b9e27b4807744fb3d77a246c1eacc3f9e8fd6c88d06a714d46b";
        //LeagueData response = restTemplate.getForObject(url, LeagueData.class);
        log.info("test");
        ResponseEntity<List<LeagueData>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LeagueData>>() {
                });
        List<LeagueData> leagueDataList = response.getBody();

        for (LeagueData leagueData : leagueDataList) {
            if (leagueData.getLeagueName().equalsIgnoreCase(leagueName))
                leagueId = leagueData.getLeagueID();
                countryId = leagueData.getCountryId();
        }
        log.info("league id {} ", leagueId);

        String urlStanding = "https://apiv2.apifootball.com/?action=get_standings&league_id=149&APIkey=4ea7cecefdc19b9e27b4807744fb3d77a246c1eacc3f9e8fd6c88d06a714d46b";
        log.info("test");
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
       return standingsResponse;
    }


}
