package com.djontleman.spacebooking.genericspaceship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgresGenericSpaceship")
public class GenericSpaceshipDataAccessService implements GenericSpaceshipDAO {

    private JdbcTemplate jdbcTemplate;
    private GenericSpaceshipRowMapper rowMapper;

    @Autowired
    public GenericSpaceshipDataAccessService(JdbcTemplate jdbcTemplate, GenericSpaceshipRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    // || ====================== Create/POST ====================== ||

    @Override
    public int createGenericSpaceship(GenericSpaceship genericSpaceship) {
        String sql = """
                INSERT INTO generic_spaceships (brand, model, capacity)
                VALUES (?, ?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                genericSpaceship.getBrand(), genericSpaceship.getModel(), genericSpaceship.getCapacity()
        );
    }

    // || ====================== Read/GET ====================== ||

    @Override
    public List<GenericSpaceship> getAllGenericSpaceships() {
        String sql = """
                SELECT * FROM generic_spaceships;
                """;
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<GenericSpaceship> getGenericSpaceshipById(Long id) {
        String sql = """
                SELECT * FROM generic_spaceships
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream().findFirst();
    }

    // || ====================== Update/PUT/PATCH ====================== ||

    @Override
    public int updateGenericSpaceship(Long id, GenericSpaceship genericSpaceship) {
        String sql = """
                UPDATE generic_spaceships
                SET brand = ?, model = ?, capacity = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(
                sql,
                genericSpaceship.getBrand(), genericSpaceship.getModel(), genericSpaceship.getCapacity(),
                id);
    }

    // || ====================== Delete/DELETE ====================== ||

    @Override
    public int deleteGenericSpaceship(Long id) {
        String sql = """
                DELETE FROM generic_spaceships
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }
}
