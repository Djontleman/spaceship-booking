package com.djontleman.spacebooking.flight;

import com.djontleman.spacebooking.exception.BadRequestException;
import com.djontleman.spacebooking.exception.ResourceNotFoundException;
import com.djontleman.spacebooking.genericspaceship.GenericSpaceshipDAO;
import com.djontleman.spacebooking.journey.Journey;
import com.djontleman.spacebooking.journey.JourneyDAO;
import com.djontleman.spacebooking.spaceship.Spaceship;
import com.djontleman.spacebooking.spaceship.SpaceshipDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private FlightDAO flightDAO;
    private JourneyDAO journeyDAO;
    private SpaceshipDAO spaceshipDAO;
    private GenericSpaceshipDAO genericSpaceshipDAO;

    @Autowired
    public FlightService(@Qualifier("postgresFlight") FlightDAO flightDAO,
                         @Qualifier("postgresJourney") JourneyDAO journeyDAO,
                         @Qualifier("postgresSpaceship") SpaceshipDAO spaceshipDAO,
                         @Qualifier("postgresGenericSpaceship") GenericSpaceshipDAO genericSpaceshipDAO) {
        this.flightDAO = flightDAO;
        this.journeyDAO = journeyDAO;
        this.spaceshipDAO = spaceshipDAO;
        this.genericSpaceshipDAO = genericSpaceshipDAO;
    }

    // || ====================== Create/POST ====================== ||

    public int createFlight(Flight flight) {
        // field validation
        if (flight.getJourneyId() == null) {
            throw new BadRequestException("Journey ID cannot be empty");
        } else if (flight.getJourneyId() <= 0) {
            throw new BadRequestException("Journey ID cannot be zero or less");
        } else {
            Optional<Journey> journeyOptional = journeyDAO.getJourneyById(flight.getJourneyId());
            if (journeyOptional.isEmpty()) {
                throw new ResourceNotFoundException("No journey with ID: " + flight.getJourneyId());
            }
        }

        if (flight.getSpaceshipId() == null) {
            throw new BadRequestException("Spaceship ID cannot be empty");
        } else if (flight.getSpaceshipId() <= 0) {
            throw new BadRequestException("Spaceship ID cannot be zero or less");
        } else {
            Optional<Spaceship> spaceshipOptional = spaceshipDAO.getSpaceshipById(flight.getSpaceshipId());
            if (spaceshipOptional.isEmpty()) {
                throw new ResourceNotFoundException("No spaceship with ID: " + flight.getSpaceshipId());
            }
        }

        return flightDAO.createFlight(flight);
    }

    // || ====================== Read/GET ====================== ||

    public List<Flight> getAllFlights() {
        List<Flight> flights = flightDAO.getAllFlights();

        flights.forEach(flight -> {
            flight.setJourney(
                    journeyDAO.getJourneyById(flight.getJourneyId()).get()
            );

            Spaceship spaceship = spaceshipDAO.getSpaceshipById(flight.getSpaceshipId()).get();
            spaceship.setGenericSpaceship(
                    genericSpaceshipDAO.getGenericSpaceshipById(spaceship.getGenericSpaceshipId()).get()
            );
            flight.setSpaceship(spaceship);
        });

        return flights;
    }

    public Optional<Flight> getFlightById(Long id) {
        Optional<Flight> flightOptional = flightDAO.getFlightById(id);
        if (flightOptional.isEmpty()) {
            throw new ResourceNotFoundException("No flight with ID: " + id);
        }

        Flight flight = flightOptional.get();
        flight.setJourney(
                journeyDAO.getJourneyById(flight.getJourneyId()).get()
        );

        Spaceship spaceship = spaceshipDAO.getSpaceshipById(flight.getSpaceshipId()).get();
        spaceship.setGenericSpaceship(
                genericSpaceshipDAO.getGenericSpaceshipById(spaceship.getGenericSpaceshipId()).get()
        );
        flight.setSpaceship(spaceship);

        return Optional.of(flight);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    public int updateFlight(Long id, Flight flight) {
        // field validation
        if (flight.getJourneyId() == null) {
            throw new BadRequestException("Journey ID cannot be empty");
        } else if (flight.getJourneyId() <= 0) {
            throw new BadRequestException("Journey ID cannot be zero or less");
        } else {
            Optional<Journey> journeyOptional = journeyDAO.getJourneyById(flight.getJourneyId());
            if (journeyOptional.isEmpty()) {
                throw new ResourceNotFoundException("No journey with ID: " + flight.getJourneyId());
            }
        }

        if (flight.getSpaceshipId() == null) {
            throw new BadRequestException("Spaceship ID cannot be empty");
        } else if (flight.getSpaceshipId() <= 0) {
            throw new BadRequestException("Spaceship ID cannot be zero or less");
        } else {
            Optional<Spaceship> spaceshipOptional = spaceshipDAO.getSpaceshipById(flight.getSpaceshipId());
            if (spaceshipOptional.isEmpty()) {
                throw new ResourceNotFoundException("No spaceship with ID: " + flight.getSpaceshipId());
            }
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
