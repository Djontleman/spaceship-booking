package com.djontleman.spacebooking.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgresFlight")
public class FlightDataAccessService implements FlightDAO {

    private JdbcTemplate jdbcTemplate;
    private FlightRowMapper rowMapper;

    @Autowired
    public FlightDataAccessService(JdbcTemplate jdbcTemplate, FlightRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    // || ====================== Create/POST ====================== ||

    @Override
    public int createFlight(Flight flight) {
        String sql = """
                INSERT INTO flights (journey_id, spaceship_id)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(sql, flight.getJourneyId(), flight.getSpaceshipId());
    }

    // || ====================== Read/GET ====================== ||

    @Override
    public List<Flight> getAllFlights() {
        String sql = """
                SELECT * FROM flights;
                """;
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Flight> getFlightById(Long id) {
        String sql = """
                SELECT * FROM flights
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public List<Flight> getFlightsByJourneyId(Long id) {
        String sql = """
                SELECT * FROM flights
                WHERE journey_id = ?;
                """;
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    @Override
    public List<Flight> getFlightsBySpaceshipId(Long id) {
        String sql = """
                SELECT * FROM flights
                WHERE spaceship_id = ?;
                """;
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    @Override
    public int updateFlight(Long id, Flight flight) {
        String sql = """
                UPDATE flights
                SET journey_id = ?, spaceship_id = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(
                sql,
                flight.getJourneyId(), flight.getSpaceshipId(),
                id
        );
    }

    // || ====================== Delete/DELETE ====================== ||

    @Override
    public int deleteFlight(Long id) {
        String sql = """
                DELETE FROM flights
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }
}
