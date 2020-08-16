package com.practice.template.service;

import com.practice.template.dto.StandingAPIResponse;

public interface FootBallService {

    StandingAPIResponse standing(String countryName, String leagueName, String teamName);
}
