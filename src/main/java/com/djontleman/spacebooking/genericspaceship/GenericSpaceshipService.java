package com.djontleman.spacebooking.genericspaceship;

import com.djontleman.spacebooking.exception.BadRequestException;
import com.djontleman.spacebooking.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenericSpaceshipService {

    public GenericSpaceshipDAO genericSpaceshipDAO;

    @Autowired
    public GenericSpaceshipService(@Qualifier("postgresGenericSpaceship") GenericSpaceshipDAO genericSpaceshipDAO) {
        this.genericSpaceshipDAO = genericSpaceshipDAO;
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
        return genericSpaceshipDAO.getAllGenericSpaceships();
    }

    public Optional<GenericSpaceship> getGenericSpaceshipById(Long id) {
        Optional<GenericSpaceship> genericSpaceship = genericSpaceshipDAO.getGenericSpaceshipById(id);
        if (genericSpaceship.isEmpty()) {
            throw new ResourceNotFoundException("No generic spaceship with ID: " + id);
        }
        return genericSpaceship;
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
        return genericSpaceshipDAO.deleteGenericSpaceship(id);
    }
}
