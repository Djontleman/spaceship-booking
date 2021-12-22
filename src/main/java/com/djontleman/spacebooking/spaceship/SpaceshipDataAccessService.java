package com.djontleman.spacebooking.spaceship;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgresSpaceship")
public class SpaceshipDataAccessService implements SpaceshipDAO {

    private JdbcTemplate jdbcTemplate;
    private SpaceshipRowMapper rowMapper;

    public SpaceshipDataAccessService(JdbcTemplate jdbcTemplate, SpaceshipRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    // || ====================== Create/POST ====================== ||

    @Override
    public int createSpaceship(Spaceship spaceship) {
        String sql = """
                INSERT INTO spaceships (call_sign, generic_spaceship_id)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(sql, spaceship.getCallSign(), spaceship.getGenericSpaceshipId());
    }

    // || ====================== Read/GET ====================== ||

    @Override
    public List<Spaceship> getAllSpaceships() {
        String sql = """
                SELECT * FROM spaceships;
                """;
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Spaceship> getSpaceshipById(Long id) {
        String sql = """
                SELECT * FROM spaceships
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream()
                .findFirst();
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    @Override
    public int updateSpaceship(Long id, Spaceship spaceship) {
        String sql = """
                UPDATE spaceships
                SET call_sign = ?, generic_spaceship_id = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(
                sql,
                spaceship.getCallSign(), spaceship.getGenericSpaceshipId(),
                id);
    }

    // || ====================== Delete/DELETE ====================== ||

    @Override
    public int deleteSpaceship(Long id) {
        String sql = """
                DELETE FROM spaceships
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }
}
