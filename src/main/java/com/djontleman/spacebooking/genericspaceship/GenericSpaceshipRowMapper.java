package com.djontleman.spacebooking.genericspaceship;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericSpaceshipRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GenericSpaceship(
                (long) rs.getInt("id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getInt("capacity")
        );
    }
}
