package com.djontleman.spacebooking.flight;

import com.djontleman.spacebooking.exception.BadRequestException;
import com.djontleman.spacebooking.exception.ResourceNotFoundException;
import com.djontleman.spacebooking.journey.JourneyDAO;
import com.djontleman.spacebooking.spaceship.Spaceship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private FlightDAO flightDAO;
    private JourneyDAO journeyDAO;

    @Autowired
    public FlightService(@Qualifier("postgresFlight") FlightDAO flightDAO,
                         @Qualifier("postgresJourney") JourneyDAO journeyDAO) {
        this.flightDAO = flightDAO;
        this.journeyDAO = journeyDAO;
    }

    // || ====================== Create/POST ====================== ||

    public int createFlight(Flight flight) {
        // field validation
        if (flight.getJourneyId() == null) {
            throw new BadRequestException("Journey ID cannot be empty");
        } else if (flight.getJourneyId() <= 0) {
            throw new BadRequestException("Journey ID cannot be zero or less");
        }

        if (flight.getSpaceshipId() == null) {
            throw new BadRequestException("Spaceship ID cannot be empty");
        } else if (flight.getSpaceshipId() <= 0) {
            throw new BadRequestException("Spaceship ID cannot be zero or less");
        }

        return flightDAO.createFlight(flight);
    }

    // || ====================== Read/GET ====================== ||

    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }

    public Optional<Flight> getFlightById(Long id) {
        Optional<Flight> flight = flightDAO.getFlightById(id);
        if (flight.isEmpty()) {
            throw new ResourceNotFoundException("No flight with ID: " + id);
        }

        return flightDAO.getFlightById(id);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    public int updateFlight(Long id, Flight flight) {
        // field validation
        if (flight.getJourneyId() == null) {
            throw new BadRequestException("Journey ID cannot be empty");
        } else if (flight.getJourneyId() <= 0) {
            throw new BadRequestException("Journey ID cannot be zero or less");
        }

        if (flight.getSpaceshipId() == null) {
            throw new BadRequestException("Spaceship ID cannot be empty");
        } else if (flight.getSpaceshipId() <= 0) {
            throw new BadRequestException("Spaceship ID cannot be zero or less");
        }

        // find old resource
        Optional<Flight> oldFlight = flightDAO.getFlightById(id);
        if (oldFlight.isEmpty()) {
            return flightDAO.createFlight(flight);
        } else {
            return flightDAO.updateFlight(id, flight);
        }
    }

    // || ====================== Delete/DELETE ====================== ||

    public int deleteFlight(Long id) {
        Optional<Flight> flight = flightDAO.getFlightById(id);
        if (flight.isEmpty()) {
            throw new ResourceNotFoundException("No flight with ID: " + id);
        }

        return flightDAO.deleteFlight(id);
    }
}
