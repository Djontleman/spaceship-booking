package com.djontleman.spacebooking.genericspaceship;

import com.djontleman.spacebooking.exception.BadRequestException;
import com.djontleman.spacebooking.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/genericSpaceships")
public class GenericSpaceshipController {

    private GenericSpaceshipService genericSpaceshipService;

    @Autowired
    public GenericSpaceshipController(GenericSpaceshipService genericSpaceshipService) {
        this.genericSpaceshipService = genericSpaceshipService;
    }

    // || ====================== Create/POST ====================== ||

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGenericSpaceship(@RequestBody GenericSpaceship genericSpaceship) {
        genericSpaceshipService.createGenericSpaceship(genericSpaceship);
    }

    // || ====================== Read/GET ====================== ||

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenericSpaceship> getAllGenericSpaceships() {
        return genericSpaceshipService.getAllGenericSpaceships();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<GenericSpaceship> getGenericSpaceshipById(@PathVariable("id") Long id) {
        return genericSpaceshipService.getGenericSpaceshipById(id);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGenericSpaceship(@PathVariable("id") Long id, @RequestBody GenericSpaceship genericSpaceship) {
        genericSpaceshipService.updateGenericSpaceship(id, genericSpaceship);
    }

    // || ====================== Delete/DELETE ====================== ||

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenericSpaceship(@PathVariable("id") Long id) {
        genericSpaceshipService.deleteGenericSpaceship(id);
    }
}
