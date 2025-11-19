package com.brooksource.say_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsController {

    @GetMapping("/points/{id}")
    public String getPointsById(@PathVariable Long id) {
        return "TO DO: set up points for " + id;
    }
}
