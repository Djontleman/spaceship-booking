package com.djontleman.spacebooking.spaceship;

import com.djontleman.spacebooking.exception.BadRequestException;
import com.djontleman.spacebooking.exception.ResourceNotFoundException;
import com.djontleman.spacebooking.flight.Flight;
import com.djontleman.spacebooking.flight.FlightDAO;
import com.djontleman.spacebooking.genericspaceship.GenericSpaceship;
import com.djontleman.spacebooking.genericspaceship.GenericSpaceshipDAO;
import com.djontleman.spacebooking.journey.JourneyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipService {

    private SpaceshipDAO spaceshipDAO;
    private GenericSpaceshipDAO genericSpaceshipDAO;
    private FlightDAO flightDAO;
    private JourneyDAO journeyDAO;


    @Autowired
    public SpaceshipService(@Qualifier("postgresSpaceship") SpaceshipDAO spaceshipDAO,
                            @Qualifier("postgresGenericSpaceship") GenericSpaceshipDAO genericSpaceshipDAO,
                            @Qualifier("postgresFlight") FlightDAO flightDAO,
                            @Qualifier("postgresJourney") JourneyDAO journeyDAO) {
        this.spaceshipDAO = spaceshipDAO;
        this.genericSpaceshipDAO = genericSpaceshipDAO;
        this.flightDAO = flightDAO;
        this.journeyDAO = journeyDAO;
    }

    // || ====================== Create/POST ====================== ||

    public int createSpaceship(Spaceship spaceship) {
        // field validation
        if (spaceship.getCallSign() == null || spaceship.getCallSign().length() <= 0) {
            throw new BadRequestException("Spaceship call sign cannot be empty");
        }
        if (spaceship.getGenericSpaceshipId() == null) {
            throw new BadRequestException("Generic spaceship ID cannot be empty");
        } else if (spaceship.getGenericSpaceshipId() <= 0) {
            throw new BadRequestException("Generic spaceship ID cannot be zero or less");
        } else {
            Optional<GenericSpaceship> genericSpaceshipOptional =
                    genericSpaceshipDAO.getGenericSpaceshipById(spaceship.getGenericSpaceshipId());
            if (genericSpaceshipOptional.isEmpty()) {
                throw new ResourceNotFoundException(
                        "No generic spaceship with ID: " + spaceship.getGenericSpaceshipId()
                );
            }
        }

        return spaceshipDAO.createSpaceship(spaceship);
    }

    // || ====================== Read/GET ====================== ||

    public List<Spaceship> getAllSpaceships() {
        List<Spaceship> spaceships = spaceshipDAO.getAllSpaceships();

        spaceships.forEach(spaceship -> {
            spaceship.setGenericSpaceship(
                genericSpaceshipDAO.getGenericSpaceshipById(spaceship.getGenericSpaceshipId()).get()
            );

            List<Flight> flightList = flightDAO.getFlightsBySpaceshipId(spaceship.getId());
            flightList.forEach(flight -> {
                flight.setJourney(
                        journeyDAO.getJourneyById(flight.getJourneyId()).get()
                );
            });
            spaceship.setFlightList(flightList);
        });

        return spaceships;
    }

    public Optional<Spaceship> getSpaceshipById(Long id) {
        Optional<Spaceship> spaceshipOptional = spaceshipDAO.getSpaceshipById(id);
        if (spaceshipOptional.isEmpty()) {
            throw new ResourceNotFoundException("No spaceship with ID: " + id);
        }

        Spaceship spaceship = spaceshipOptional.get();
        spaceship.setGenericSpaceship(
                genericSpaceshipDAO.getGenericSpaceshipById(spaceship.getGenericSpaceshipId()).get()
        );

        List<Flight> flightList = flightDAO.getFlightsBySpaceshipId(spaceship.getId());
        flightList.forEach(flight -> {
            flight.setJourney(
                    journeyDAO.getJourneyById(flight.getJourneyId()).get()
            );
        });
        spaceship.setFlightList(flightList);

        return Optional.of(spaceship);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    public int updateSpaceship(Long id, Spaceship spaceship) {

        // field validation
        if (spaceship.getCallSign() == null || spaceship.getCallSign().length() <= 0) {
            throw new BadRequestException("Spaceship call sign cannot be empty");
        }
        if (spaceship.getGenericSpaceshipId() == null) {
            throw new BadRequestException("Generic spaceship ID cannot be empty");
        } else if (spaceship.getGenericSpaceshipId() <= 0) {
            throw new BadRequestException("Generic spaceship ID cannot be zero or less");
        } else {
            Optional<GenericSpaceship> genericSpaceshipOptional =
                    genericSpaceshipDAO.getGenericSpaceshipById(spaceship.getGenericSpaceshipId());
            if (genericSpaceshipOptional.isEmpty()) {
                throw new ResourceNotFoundException(
                        "No generic spaceship with ID: " + spaceship.getGenericSpaceshipId()
                );
            }
        }

        // find old resource
        Optional<Spaceship> oldSpaceship = spaceshipDAO.getSpaceshipById(id);
        if (oldSpaceship.isEmpty()) {
            return spaceshipDAO.createSpaceship(spaceship);
        } else {
            return spaceshipDAO.updateSpaceship(id, spaceship);
        }
    }

    // || ====================== Delete/DELETE ====================== ||

    public int deleteSpaceship(Long id) {
        Optional<Spaceship> spaceship = spaceshipDAO.getSpaceshipById(id);
        if (spaceship.isEmpty()) {
            throw new ResourceNotFoundException("No spaceship with ID: " + id);
        }

        List<Flight> flightList = flightDAO.getFlightsBySpaceshipId(id);
        if (flightList.size() > 0) {
            throw new BadRequestException(
                    "Cannot delete as spaceship with ID: " + id + " has been assigned to flights"
            );
        }

        return spaceshipDAO.deleteSpaceship(id);
    }
}
