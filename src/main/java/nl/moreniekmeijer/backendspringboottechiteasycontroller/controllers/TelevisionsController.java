package nl.moreniekmeijer.backendspringboottechiteasycontroller.controllers;

import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.IndexOutOfBoundsException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.NameTooLongException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.RecordNotFoundException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.Television;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.repositories.TelevisionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @PostMapping
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {
        televisionRepository.save(television);
        return ResponseEntity.created(null).body(television);
    }

    @GetMapping
    public ResponseEntity<List<Television>> getTelevisions() {
        List<Television> foundTelevisions = televisionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return ResponseEntity.ok(foundTelevisions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable Long id) {
        Optional<Television> foundTelevision = televisionRepository.findById(id);
        return foundTelevision.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody Television updatedTelevision) {
        Optional<Television> foundTelevision = televisionRepository.findById(id);
        return foundTelevision.map(existingTelevision -> {
            // Ik heb de eerste vier attributen aanpasbaar gemaakt, ik ga niet alles uittypen...
            existingTelevision.setType(updatedTelevision.getType());
            existingTelevision.setBrand(updatedTelevision.getBrand());
            existingTelevision.setName(updatedTelevision.getName());
            existingTelevision.setPrice(updatedTelevision.getPrice());
            Television savedTelevision = televisionRepository.save(existingTelevision);
            return ResponseEntity.ok(savedTelevision);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        televisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
