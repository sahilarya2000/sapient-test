package com.practice.template.controller;

import com.practice.template.dto.StandingAPIResponse;
import com.practice.template.service.FootBallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/football")
@Slf4j
public class FootBallController {

    @Autowired
    private FootBallService footBallService;


    @GetMapping("/standing")
    public ResponseEntity<StandingAPIResponse> checkStandings(@RequestParam("country_name") String countryName,
                                                              @RequestParam("league_name")  String leagueName,
                                                              @RequestParam("team_name") String teamName){
        log.info("[checkStandings]called for countryName {} , leagueName {} and teamName ",
                countryName,leagueName,teamName);
        return new ResponseEntity<StandingAPIResponse>(footBallService.standing(countryName,leagueName,teamName),HttpStatus.OK);
    }

}
