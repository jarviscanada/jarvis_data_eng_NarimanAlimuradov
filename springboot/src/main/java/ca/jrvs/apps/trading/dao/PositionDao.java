package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class PositionDao {

    private static final String TABLE_NAME = "position";
    private static final String ACCOUNT_ID = "account_id";
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public PositionDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ACCOUNT_ID);
    }

    public Optional<Position> findById(int id){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ACCOUNT_ID + "=?";
        try {
            Position position = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Position.class), id);
            return Optional.of(position);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
