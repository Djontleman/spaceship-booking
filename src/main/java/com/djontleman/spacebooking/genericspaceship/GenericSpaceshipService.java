package com.djontleman.spacebooking.genericspaceship;

import com.djontleman.spacebooking.exception.BadRequestException;
import com.djontleman.spacebooking.exception.ResourceNotFoundException;
import com.djontleman.spacebooking.spaceship.Spaceship;
import com.djontleman.spacebooking.spaceship.SpaceshipDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenericSpaceshipService {

    public GenericSpaceshipDAO genericSpaceshipDAO;
    public SpaceshipDAO spaceshipDAO;

    @Autowired
    public GenericSpaceshipService(@Qualifier("postgresGenericSpaceship") GenericSpaceshipDAO genericSpaceshipDAO,
                                   @Qualifier("postgresSpaceship") SpaceshipDAO spaceshipDAO) {
        this.genericSpaceshipDAO = genericSpaceshipDAO;
        this.spaceshipDAO = spaceshipDAO;
    }

    // || ====================== Create/POST ====================== ||

    public int createGenericSpaceship(GenericSpaceship genericSpaceship) {
        // field validation
        if (genericSpaceship.getBrand() == null || genericSpaceship.getBrand().length() <= 0) {
            throw new BadRequestException("Generic spaceship brand cannot be empty");
        }
        if (genericSpaceship.getModel() == null || genericSpaceship.getModel().length() <= 0) {
            throw new BadRequestException("Generic spaceship model cannot be empty");
        }
        if (genericSpaceship.getCapacity() == null) {
            throw new BadRequestException("Generic spaceship capacity cannot be empty");
        } else if (genericSpaceship.getCapacity() <= 0) {
            throw new BadRequestException("Generic spaceship capacity cannot be zero or less");
        }

        return genericSpaceshipDAO.createGenericSpaceship(genericSpaceship);
    }

    // || ====================== Read/GET ====================== ||

    public List<GenericSpaceship> getAllGenericSpaceships() {
        List<GenericSpaceship> genericSpaceships = genericSpaceshipDAO.getAllGenericSpaceships();

        genericSpaceships.forEach(genericSpaceship -> {
            genericSpaceship.setSpaceshipsList(
                    spaceshipDAO.getSpaceshipsByGenericSpaceshipId(genericSpaceship.getId())
            );
        });

        return genericSpaceships;
    }

    public Optional<GenericSpaceship> getGenericSpaceshipById(Long id) {
        Optional<GenericSpaceship> genericSpaceshipOptional = genericSpaceshipDAO.getGenericSpaceshipById(id);
        if (genericSpaceshipOptional.isEmpty()) {
            throw new ResourceNotFoundException("No generic spaceship with ID: " + id);
        }

        GenericSpaceship genericSpaceship = genericSpaceshipOptional.get();
        genericSpaceship.setSpaceshipsList(
                spaceshipDAO.getSpaceshipsByGenericSpaceshipId(genericSpaceship.getId())
        );

        return Optional.of(genericSpaceship);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    public int updateGenericSpaceship(Long id, GenericSpaceship genericSpaceship) {

        // field validation
        if (genericSpaceship.getBrand() == null || genericSpaceship.getBrand().length() <= 0) {
            throw new BadRequestException("Generic spaceship brand cannot be empty");
        }
        if (genericSpaceship.getModel() == null || genericSpaceship.getModel().length() <= 0) {
            throw new BadRequestException("Generic spaceship model cannot be empty");
        }
        if (genericSpaceship.getCapacity() == null) {
            throw new BadRequestException("Generic spaceship capacity cannot be empty");
        } else if (genericSpaceship.getCapacity() <= 0) {
            throw new BadRequestException("Generic spaceship capacity cannot be zero or less");
        }

        // find old resource
        Optional<GenericSpaceship> oldGenericSpaceship = genericSpaceshipDAO.getGenericSpaceshipById(id);
        if (oldGenericSpaceship.isEmpty()) {
            return genericSpaceshipDAO.createGenericSpaceship(genericSpaceship);
        } else {
            return genericSpaceshipDAO.updateGenericSpaceship(id, genericSpaceship);
        }
    }

    // || ====================== Delete/DELETE ====================== ||

    public int deleteGenericSpaceship(Long id) {
        Optional<GenericSpaceship> genericSpaceship = genericSpaceshipDAO.getGenericSpaceshipById(id);
        if (genericSpaceship.isEmpty()) {
            throw new ResourceNotFoundException("No generic spaceship with ID: " + id);
        }

        List<Spaceship> spaceshipList = spaceshipDAO.getSpaceshipsByGenericSpaceshipId(id);
        if (spaceshipList.size() > 0) {
            throw new BadRequestException(
                    "Cannot delete as generic spaceship with ID: " + id + " has been assigned to spaceships"
            );
        }

        return genericSpaceshipDAO.deleteGenericSpaceship(id);
    }
}
