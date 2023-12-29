package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.Add;
import edu.pjatk.jcarsapi.repository.AddsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddsService {

    private final AddsRepository addsRepository;

    public AddsService(AddsRepository addsRepository) {
        this.addsRepository = addsRepository;
    }


    public List<Add> getAllAdds() {
        return addsRepository.findAll();
    }
}
