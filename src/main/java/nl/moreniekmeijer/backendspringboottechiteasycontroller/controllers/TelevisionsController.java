package nl.moreniekmeijer.backendspringboottechiteasycontroller.controllers;

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
        televisionDataBase.add(name);
        return ResponseEntity.created(null).body(name + " aangemaakt");
    }

    @GetMapping
    public ResponseEntity<List<String>> getTelevisions() {
        return ResponseEntity.ok(televisionDataBase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevision(@PathVariable int id) {
        String specificTelevision = televisionDataBase.get(id);
        return ResponseEntity.ok(specificTelevision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> changeTelevision(@PathVariable int id, @RequestParam String name) {
        televisionDataBase.set(id, name);
        return ResponseEntity.ok("televisie " + id + " aangepast naar: " + name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        televisionDataBase.remove(id);
        return ResponseEntity.noContent().build();
    }
}
