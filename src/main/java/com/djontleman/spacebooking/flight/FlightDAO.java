package com.djontleman.spacebooking.flight;

import java.util.List;
import java.util.Optional;

public interface FlightDAO {

    // || ====================== Create/POST ====================== ||

    public int createFlight(Flight flight);

    // || ====================== Read/GET ====================== ||

    public List<Flight> getAllFlights();
    public Optional<Flight> getFlightById(Long id);
    public List<Flight> getFlightsByJourneyId(Long id);
    public List<Flight> getFlightsBySpaceshipId(Long id);

    // || ====================== Update/PUT/PATCH ====================== ||

    public int updateFlight(Long id, Flight flight);

    // || ====================== Delete/DELETE ====================== ||

    public int deleteFlight(Long id);
}
