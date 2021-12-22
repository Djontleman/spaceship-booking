package com.djontleman.spacebooking.genericspaceship;

import java.util.List;
import java.util.Optional;

public interface GenericSpaceshipDAO {

    // || ====================== Create/POST ====================== ||

    public int createGenericSpaceship(GenericSpaceship genericSpaceship);

    // || ====================== Read/GET ====================== ||

    public List<GenericSpaceship> getAllGenericSpaceships();
    public Optional<GenericSpaceship> getGenericSpaceshipById(Long id);

    // || ====================== Update/PUT/PATCH ====================== ||

    public int updateGenericSpaceship(Long id, GenericSpaceship genericSpaceship);

    // || ====================== Delete/DELETE ====================== ||

    public int deleteGenericSpaceship(Long id);

}
