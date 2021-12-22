package com.djontleman.spacebooking.genericspaceship;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GenericSpaceshipRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GenericSpaceship(
                rs.getLong("id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getInt("capacity"),
                null
        );
    }
}
