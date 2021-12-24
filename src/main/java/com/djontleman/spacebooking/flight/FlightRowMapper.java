package com.djontleman.spacebooking.flight;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FlightRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Flight(
                rs.getLong("id"),
                rs.getLong("journey_id"),
                null,
                rs.getLong("spaceship_id"),
                null
        );
    }
}
