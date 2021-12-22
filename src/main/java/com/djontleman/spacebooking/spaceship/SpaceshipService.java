package com.djontleman.spacebooking.spaceship;

import com.djontleman.spacebooking.exception.BadRequestException;
import com.djontleman.spacebooking.exception.ResourceNotFoundException;
import com.djontleman.spacebooking.genericspaceship.GenericSpaceship;
import com.djontleman.spacebooking.genericspaceship.GenericSpaceshipDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipService {

    public SpaceshipDAO spaceshipDAO;
    public GenericSpaceshipDAO genericSpaceshipDAO;

    @Autowired
    public SpaceshipService(@Qualifier("postgresSpaceship") SpaceshipDAO spaceshipDAO,
                            @Qualifier("postgresGenericSpaceship") GenericSpaceshipDAO genericSpaceshipDAO) {
        this.spaceshipDAO = spaceshipDAO;
        this.genericSpaceshipDAO = genericSpaceshipDAO;
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
            genericSpaceshipDAO.getGenericSpaceshipById(spaceship.getGenericSpaceshipId());
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
            genericSpaceshipDAO.getGenericSpaceshipById(spaceship.getGenericSpaceshipId());
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

    public int deleteGenericSpaceship(Long id) {
        Optional<Spaceship> spaceship = spaceshipDAO.getSpaceshipById(id);
        if (spaceship.isEmpty()) {
            throw new ResourceNotFoundException("No spaceship with ID: " + id);
        }
        return spaceshipDAO.deleteSpaceship(id);
    }
}
