package nl.moreniekmeijer.backendspringboottechiteasycontroller.controllers;

import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.IndexOutOfBoundsException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.NameTooLongException;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {
    private List<String> televisionDataBase = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> addTelevision(@RequestBody String name) {
        if (name.length() > 20) {
            throw new NameTooLongException("Naam mag uit niet meer dan 20 karakters bestaan");
        } else {
            televisionDataBase.add(name);
        }
        return ResponseEntity.created(null).body(name + " aangemaakt");
    }

    @GetMapping
    public ResponseEntity<List<String>> getTelevisions() {
        return ResponseEntity.ok(televisionDataBase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevision(@PathVariable int id) {
        if (id < 0 || id >= televisionDataBase.size()) {
            throw new IndexOutOfBoundsException(id + " valt buiten de lijst");
            // Bovenstaande werkt blijkbaar niet (geeft 500 error), geen idee hoe het wel moet...
        }

        String television = televisionDataBase.get(id);
        if (television == null) {
            throw new RecordNotFoundException("Televisie bestaat niet");
        }

        return ResponseEntity.ok(television);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> changeTelevision(@PathVariable int id, @RequestParam String name) {
        if (televisionDataBase.isEmpty() || id > televisionDataBase.size()) {
            throw new RecordNotFoundException("Record met id: " + id + " niet gevonden in de database.");
        } else {
            televisionDataBase.set(id, name);
            return ResponseEntity.ok("televisie " + id + " aangepast naar: " + name);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        televisionDataBase.set(id, null);
        return ResponseEntity.noContent().build();
    }
}
