package Controller;

import Repository.DataRepository;
import model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    DataRepository dataRepository;
    @GetMapping("/Datas")
    public ResponseEntity<List<Data>> getAllTutorials(@RequestParam(required = false) String city) {
        try {
            List<Data> datas = new ArrayList<Data>();
            if (city == null)
                dataRepository.findAll().forEach(datas::add);
            else
                dataRepository.findByCityContaining(city).forEach(datas::add);
            if (city.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(datas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/datas/{id}")
    public ResponseEntity<Data> getTutorialById(@PathVariable("id") long id) {
        Optional<Data> tutorialData = dataRepository.findById(id);
        return tutorialData.map(data -> new ResponseEntity<>(data, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @PostMapping("/datas")
    public ResponseEntity<Data> createTutorial(@RequestBody Data tutorial) {
        try {
            Data _tutorial = dataRepository
                    .save(new Data(tutorial.getCity(), tutorial.getDistrict()));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/datas/{id}")
    public ResponseEntity<Data> updateTutorial(@PathVariable("id") long id, @RequestBody Data tutorial) {
        Optional<Data> tutorialData = dataRepository.findById(id);
        if (tutorialData.isPresent()) {
            Data _tutorial = tutorialData.get();
            _tutorial.setCity(tutorial.getCity());
            _tutorial.setDistrict(tutorial.getDistrict());
            return new ResponseEntity<>(dataRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/datas/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            dataRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/datas")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            dataRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
