package com.djontleman.spacebooking.spaceship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/spaceships")
public class SpaceshipController {

    private SpaceshipService spaceshipService;

    @Autowired
    public SpaceshipController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }

    // || ====================== Create/POST ====================== ||

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createSpaceship(@RequestBody Spaceship spaceship) {
        spaceshipService.createSpaceship(spaceship);
    }

    // || ====================== Read/GET ====================== ||

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Spaceship> getAllSpaceships() {
        return spaceshipService.getAllSpaceships();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Spaceship> getSpaceshipById(Long id) {
        return spaceshipService.getSpaceshipById(id);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSpaceship(Long id, Spaceship spaceship) {
        spaceshipService.updateSpaceship(id, spaceship);
    }

    // || ====================== Delete/DELETE ====================== ||

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpaceship(Long id) {
        spaceshipService.deleteSpaceship(id);
    }
}
