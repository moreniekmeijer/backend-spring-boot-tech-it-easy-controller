package nl.moreniekmeijer.backendspringboottechiteasycontroller.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {
    private List<String> televisions = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> addTelevision(@RequestBody String name) {
        return ResponseEntity.created(null).body(name + " aangemaakt");
    }

    @GetMapping
    public ResponseEntity<String> getTelevisions() {
        return ResponseEntity.ok("lijst van televisies terug");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevision(@PathVariable int id) {
        return ResponseEntity.ok("televisie: " + id + " terug");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> changeTelevision(@PathVariable int id, @RequestBody String name) {
        return ResponseEntity.ok("televisie aangepast");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        return ResponseEntity.noContent().build();
    }
}
