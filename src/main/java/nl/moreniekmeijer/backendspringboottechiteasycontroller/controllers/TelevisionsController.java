package nl.moreniekmeijer.backendspringboottechiteasycontroller.controllers;

import jakarta.validation.Valid;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.dtos.TelevisionInputDto;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.dtos.TelevisionResponseDto;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.IndexOutOfBoundsException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.NameTooLongException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.RecordNotFoundException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.mappers.TelevisionMapper;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.Television;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @PostMapping
    public ResponseEntity<TelevisionResponseDto> addTelevision(@Valid @RequestBody TelevisionInputDto television) {
//        if (television.name.length() > 20) {
//            throw new NameTooLongException(20);
//        } else {
        Television savedTelevision = televisionService.saveTelevision(TelevisionMapper.toEntity(television));
            return ResponseEntity.created(null).body(TelevisionMapper.toResponseDto(savedTelevision));
    }

    @GetMapping
    public ResponseEntity<List<TelevisionResponseDto>> getTelevisions() {
        List<Television> foundTelevisions = televisionService.getAllTelevisions();
        return ResponseEntity.ok(TelevisionMapper.toResponseDtoList(foundTelevisions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionResponseDto> getTelevisionById(@PathVariable Long id) {
        if (id < 0) {
            throw new IndexOutOfBoundsException("ID cannot be negative: " + id);
        }

        return televisionService.getTelevisionById2(id)
                .map(television -> ResponseEntity.ok(TelevisionMapper.toResponseDto(television)))
                .orElseThrow(() -> new RecordNotFoundException("Television with ID " + id + " not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionResponseDto> updateTelevision(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto televisionDetails) {
//        if (televisionDetails.name.length() > 20) {
//            throw new NameTooLongException(20);
//        }

        Television updatedTelevision = televisionService.updateTelevision(id, TelevisionMapper.toEntity(televisionDetails));

        return ResponseEntity.ok(TelevisionMapper.toResponseDto(updatedTelevision));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }
}
