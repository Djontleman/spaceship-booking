package com.djontleman.spacebooking.journey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgresJourney")
public class JourneyDataAccessService implements JourneyDAO {

    private JdbcTemplate jdbcTemplate;
    private JourneyRowMapper rowMapper;

    @Autowired
    public JourneyDataAccessService(JdbcTemplate jdbcTemplate, JourneyRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    // || ====================== Create/POST ====================== ||

    @Override
    public int createJourney(Journey journey) {
        String sql = """
                INSERT INTO journeys (origin, destination)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(sql, journey.getOrigin(), journey.getDestination());
    }

    // || ====================== Read/GET ====================== ||

    @Override
    public List<Journey> getAllJourneys() {
        String sql = """
                SELECT * FROM journeys;
                """;
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Journey> getJourneyById(Long id) {
        String sql = """
                SELECT * FROM journeys
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream().findFirst();
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    @Override
    public int updateJourney(Long id, Journey journey) {
        String sql = """
                UPDATE journeys
                SET origin = ?, destination = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(
                sql,
                journey.getOrigin(), journey.getDestination(),
                id);
    }

    // || ====================== Delete/DELETE ====================== ||

    @Override
    public int deleteJourney(Long id) {
        String sql = """
                DELETE FROM journeys
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }
}
