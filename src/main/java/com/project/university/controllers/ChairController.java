package com.project.university.controllers;

import com.project.university.models.Chair;
import com.project.university.services.ChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chairs")
public class ChairController {

    @Autowired
    private ChairService chairService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Chair> getAllChairs() {
        return chairService.getAllChairs();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Chair getChair(@PathVariable int id) {
        return chairService.getChair(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Chair addChair(@RequestBody Chair chair) {
        return chairService.saveChair(chair);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateChair(@RequestBody Chair chair) {
        chairService.updateChair(chair);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChair(@PathVariable int id) {
        chairService.deleteChair(id);
    }
}
