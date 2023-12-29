package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.model.Add;
import edu.pjatk.jcarsapi.service.AddsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddsController {

    private final AddsService addsService;

    public AddsController(AddsService addsService) {
        this.addsService = addsService;
    }

    @GetMapping(path = "/getAllAdds")
    public ResponseEntity<List<Add>> getAllAdds() {
        return new ResponseEntity<>(addsService.getAllAdds(), HttpStatus.OK);
    }
}
