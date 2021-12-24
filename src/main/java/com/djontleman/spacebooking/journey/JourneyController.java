package com.djontleman.spacebooking.journey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/journeys")
public class JourneyController {

    private JourneyService journeyService;

    @Autowired
    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    // || ====================== Create/POST ====================== ||

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createJourney(@RequestBody Journey journey) {
        journeyService.createJourney(journey);
    }

    // || ====================== Read/GET ====================== ||

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Journey> getAllJourneys() {
        return journeyService.getAllJourneys();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Journey> getJourneyById(@PathVariable("id") Long id) {
        return journeyService.getJourneyById(id);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateJourney(@PathVariable("id") Long id, @RequestBody Journey journey) {
        journeyService.updateJourney(id, journey);
    }

    // || ====================== Delete/DELETE ====================== ||

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJourney(@PathVariable("id") Long id) {
        journeyService.deleteJourney(id);
    }
}
