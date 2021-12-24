package com.djontleman.spacebooking.flight;

import com.djontleman.spacebooking.journey.Journey;
import com.djontleman.spacebooking.spaceship.Spaceship;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/flights")
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // || ====================== Create/POST ====================== ||

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFlight(@RequestBody FlightDTO flightDTO) {
        Flight flight = new Flight(
                flightDTO.getId(),
                flightDTO.getJourneyId(), null,
                flightDTO.getSpaceshipId(), null
        );
        flightService.createFlight(flight);
    }

    // || ====================== Read/GET ====================== ||

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Flight> getFlightById(@PathVariable("id") Long id) {
        return flightService.getFlightById(id);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFlight(@PathVariable("id") Long id, @RequestBody FlightDTO flightDTO) {
        Flight flight = new Flight(
                flightDTO.getId(),
                flightDTO.getJourneyId(), null,
                flightDTO.getSpaceshipId(), null
        );
        flightService.updateFlight(id, flight);
    }

    // || ====================== Delete/DELETE ====================== ||

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable("id") Long id) {
        flightService.deleteFlight(id);
    }
}

@Data
class FlightDTO {
    private Long id;
    private Long journeyId;
    private Long spaceshipId;
}
