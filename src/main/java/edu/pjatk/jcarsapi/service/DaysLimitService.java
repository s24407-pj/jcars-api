package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.DaysLimit;
import edu.pjatk.jcarsapi.repository.DaysLimitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaysLimitService {

    private final DaysLimitRepository daysLimitRepository;

    public DaysLimitService(DaysLimitRepository daysLimitRepository) {
        this.daysLimitRepository = daysLimitRepository;
    }

    public List<DaysLimit> getAllLimits() {
        return daysLimitRepository.findAll();
    }
}
