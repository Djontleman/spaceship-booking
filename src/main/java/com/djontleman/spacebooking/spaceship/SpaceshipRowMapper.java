package com.djontleman.spacebooking.spaceship;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SpaceshipRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Spaceship(
                rs.getLong("id"),
                rs.getString("call_sign"),
                rs.getLong("generic_spaceship_id"),
                null,
                null
        );
    }
}
