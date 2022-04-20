package nl.novi.backend.techiteasy.controllers;

import nl.novi.backend.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.backend.techiteasy.models.Television;
import nl.novi.backend.techiteasy.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionController(TelevisionRepository televisionRepository){
        this.televisionRepository = televisionRepository;
    }

    @GetMapping //get all
    public ResponseEntity<List<Television>> getAllTelevisions(){
        final List<Television> allTelevisions = televisionRepository.findAll();
        return ResponseEntity.ok(allTelevisions);
    }

    @GetMapping("/{id}") //get by id
        public ResponseEntity<Television> getTelevision(@PathVariable("id") Long id) {
        Optional<Television> television = televisionRepository.findById(id);
        if (television.isEmpty()){
//            return ResponseEntity.notFound().build();
            throw new RecordNotFoundException("This ID can't be found");
        }
        return ResponseEntity.ok(television.get());
    }

    @DeleteMapping() //delete all
    public ResponseEntity<Television> deleteTelevision() {
        televisionRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}") //delete by id
    public ResponseEntity<Television> deleteTelevision(@PathVariable("id") Long id) {
        televisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}") //update by id
    public ResponseEntity<Television> updateTelevision(@PathVariable(value = "id") Long id, @RequestBody Television televisionDetails) throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television not found for this id :: " + id));
        television.setName(televisionDetails.getName());
        television.setType(televisionDetails.getType());
        television.setBrand(televisionDetails.getBrand());
        final Television updateTelevision = televisionRepository.save(television);
        return ResponseEntity.ok(updateTelevision);
    }

    @PostMapping("/create") //create television
    public Television createTelevision(@RequestBody Television television) {
        return televisionRepository.save(television);
    }

}
