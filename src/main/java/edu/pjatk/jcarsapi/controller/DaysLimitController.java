package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.model.DaysLimit;
import edu.pjatk.jcarsapi.service.DaysLimitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DaysLimitController {

    private final DaysLimitService days_limit_service;

    public DaysLimitController(DaysLimitService days_limit_service) {
        this.days_limit_service = days_limit_service;
    }

    @GetMapping(path = "/getlimits")
    public ResponseEntity<List<DaysLimit>> getAllLimits() {
        return new ResponseEntity<>(days_limit_service.getAllLimits(), HttpStatus.OK);
    }

}
