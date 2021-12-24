package com.djontleman.spacebooking.journey;

import java.util.List;
import java.util.Optional;

public interface JourneyDAO {

    // || ====================== Create/POST ====================== ||

    public int createJourney(Journey journey);

    // || ====================== Read/GET ====================== ||

    public List<Journey> getAllJourneys();
    public Optional<Journey> getJourneyById(Long id);

    // || ====================== Update/PUT/PATCH ====================== ||

    public int updateJourney(Long id, Journey journey);

    // || ====================== Delete/DELETE ====================== ||

    public int deleteJourney(Long id);
}
