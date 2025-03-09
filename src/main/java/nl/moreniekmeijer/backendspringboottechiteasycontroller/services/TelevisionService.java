package nl.moreniekmeijer.backendspringboottechiteasycontroller.services;
import jakarta.validation.Valid;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.dtos.IdInputDto;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.dtos.TelevisionResponseDto;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.IndexOutOfBoundsException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.RecordNotFoundException;

import nl.moreniekmeijer.backendspringboottechiteasycontroller.mappers.TelevisionMapper;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.RemoteController;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.Television;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.repositories.RemoteControllerRepository;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.repositories.TelevisionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;

    public TelevisionService (TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public Television saveTelevision(Television television) {
        return televisionRepository.save(television);
    }

    public List<Television> getAllTelevisions(Optional<String> brand) {
        if (brand.isPresent()) {
            return televisionRepository.findByBrand(brand.get(), Sort.by(Sort.Direction.ASC, "id"));
        }
        return televisionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Television getTelevisionById(Long id) {
        if (id < 0) {
            throw new IndexOutOfBoundsException("ID cannot be negative: " + id);
        }
        return televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with ID " + id + " not found."));
    }

    public Television updateTelevision(Long id, Television televisionDetails) {
        return televisionRepository.findById(id)
                .map(existingTelevision -> {
                    existingTelevision.setName(televisionDetails.getName());
                    existingTelevision.setPrice(televisionDetails.getPrice());
                    existingTelevision.setBrand(televisionDetails.getBrand());
                    return televisionRepository.save(existingTelevision);
                })
                .orElseThrow(() -> new RecordNotFoundException("Television with ID " + id + " not found."));
    }

    public void deleteTelevision(Long id) {
        if (!televisionRepository.existsById(id)) {
            throw new RecordNotFoundException("Television with ID " + id + " not found.");
        }
        televisionRepository.deleteById(id);
    }

    public TelevisionResponseDto assignRemoteControllerToTelevision(Long televisionId, Long remoteControllerId) {
        RemoteController remoteController = remoteControllerRepository.findById(remoteControllerId)
                .orElseThrow(() -> new RecordNotFoundException("Remote Controller with ID " + remoteControllerId + " not found."));

        Television television = televisionRepository.findById(televisionId)
                .orElseThrow(() -> new RecordNotFoundException("Television with ID " + televisionId + " not found."));

        television.setRemoteController(remoteController);
        televisionRepository.save(television);
        return TelevisionMapper.toResponseDto(television);
    }
}
