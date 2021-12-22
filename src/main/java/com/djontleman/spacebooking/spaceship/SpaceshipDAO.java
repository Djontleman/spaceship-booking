package com.djontleman.spacebooking.spaceship;

import com.djontleman.spacebooking.genericspaceship.GenericSpaceship;

import java.util.List;
import java.util.Optional;

public interface SpaceshipDAO {

    // || ====================== Create/POST ====================== ||

    public int createSpaceship(Spaceship spaceship);

    // || ====================== Read/GET ====================== ||

    public List<Spaceship> getAllSpaceships();
    public Optional<Spaceship> getSpaceshipById(Long id);

    // || ====================== Update/PUT/PATCH ====================== ||

    public int updateSpaceship(Long id, Spaceship spaceship);

    // || ====================== Delete/DELETE ====================== ||

    public int deleteSpaceship(Long id);
}
