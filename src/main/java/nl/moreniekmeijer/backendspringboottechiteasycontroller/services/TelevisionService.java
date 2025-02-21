package nl.moreniekmeijer.backendspringboottechiteasycontroller.services;

import jakarta.persistence.EntityNotFoundException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.Television;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public Television saveTelevision(Television television) {
        return televisionRepository.save(television);
    }

    public List<Television> getAllTelevisions() {
        return televisionRepository.findAll();
    }

    public Television getTelevisionById(Long id) {
        return televisionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Television with ID " + id + " not found."));
    }

    // alternative
    public Optional<Television> getTelevisionById2(Long id) {
        return televisionRepository.findById(id);
    }

    public Optional<Television> updateTelevision(Long id, Television television) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isPresent()) {
            Television updatedTelevision = optionalTelevision.get();
            updatedTelevision.setName(television.getName());
            updatedTelevision.setPrice(television.getPrice());
            return Optional.of(televisionRepository.save(updatedTelevision));
        }
        return Optional.empty();
    }

    public boolean deleteTelevision(Long id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
