package com.djontleman.spacebooking.journey;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JourneyRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Journey(
                rs.getLong("id"),
                rs.getString("origin"),
                rs.getString("destination")
        );
    }
}
