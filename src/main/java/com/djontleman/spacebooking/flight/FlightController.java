package com.djontleman.spacebooking.flight;

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
    public void createFlight(@RequestBody Flight flight) {
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
    public void updateFlight(@PathVariable("id") Long id, @RequestBody Flight flight) {
        flightService.updateFlight(id, flight);
    }

    // || ====================== Delete/DELETE ====================== ||

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable("id") Long id) {
        flightService.deleteFlight(id);
    }
}
