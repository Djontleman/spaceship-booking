package com.djontleman.spacebooking.journey;

import com.djontleman.spacebooking.exception.BadRequestException;
import com.djontleman.spacebooking.exception.ResourceNotFoundException;
import com.djontleman.spacebooking.flight.FlightDAO;
import com.djontleman.spacebooking.spaceship.Spaceship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JourneyService {

    private JourneyDAO journeyDAO;
    private FlightDAO flightDAO;

    @Autowired
    public JourneyService(@Qualifier("postgresJourney") JourneyDAO journeyDAO,
                          @Qualifier("postgresFlight") FlightDAO flightDAO) {
        this.journeyDAO = journeyDAO;
        this.flightDAO = flightDAO;
    }

    // || ====================== Create/POST ====================== ||

    public int createJourney(Journey journey) {
        // field validation
        if (journey.getOrigin() == null || journey.getOrigin().length() <= 0) {
            throw new BadRequestException("Journey origin cannot be empty");
        }
        if (journey.getDestination() == null || journey.getDestination().length() <= 0) {
            throw new BadRequestException("Journey destination cannot be empty");
        }

        return journeyDAO.createJourney(journey);
    }

    // || ====================== Read/GET ====================== ||

    public List<Journey> getAllJourneys() {
        return journeyDAO.getAllJourneys();
    }

    public Optional<Journey> getJourneyById(Long id) {
        Optional<Journey> journey = journeyDAO.getJourneyById(id);
        if (journey.isEmpty()) {
            throw new ResourceNotFoundException("No journey with ID: " + id);
        }
        return journeyDAO.getJourneyById(id);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    public int updateJourney(Long id, Journey journey) {
        // field validation
        if (journey.getOrigin() == null || journey.getOrigin().length() <= 0) {
            throw new BadRequestException("Journey origin cannot be empty");
        }
        if (journey.getDestination() == null || journey.getDestination().length() <= 0) {
            throw new BadRequestException("Journey destination cannot be empty");
        }

        // find old resource
        Optional<Journey> oldJourney = journeyDAO.getJourneyById(id);
        if (oldJourney.isEmpty()) {
            return journeyDAO.createJourney(journey);
        } else {
            return journeyDAO.updateJourney(id, journey);
        }
    }

    // || ====================== Delete/DELETE ====================== ||

    public int deleteJourney(Long id) {
        Optional<Journey> journey = journeyDAO.getJourneyById(id);
        if (journey.isEmpty()) {
            throw new ResourceNotFoundException("No journey with ID: " + id);
        }
        return journeyDAO.deleteJourney(id);
    }
}
