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

    @Autowired
    public SpaceshipService(@Qualifier("postgresSpaceship") SpaceshipDAO spaceshipDAO) {
        this.spaceshipDAO = spaceshipDAO;
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
        }

        return spaceshipDAO.createSpaceship(spaceship);
    }

    // || ====================== Read/GET ====================== ||

    public List<Spaceship> getAllSpaceships() {
        return spaceshipDAO.getAllSpaceships();
    }

    public Optional<Spaceship> getSpaceshipById(Long id) {
        Optional<Spaceship> spaceship = spaceshipDAO.getSpaceshipById(id);
        if (spaceship.isEmpty()) {
            throw new ResourceNotFoundException("No spaceship with ID: " + id);
        }
        return spaceship;
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
