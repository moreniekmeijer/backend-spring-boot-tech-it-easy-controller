package nl.moreniekmeijer.backendspringboottechiteasycontroller.services;

import nl.moreniekmeijer.backendspringboottechiteasycontroller.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;

    public TelevisionService (TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }


}
