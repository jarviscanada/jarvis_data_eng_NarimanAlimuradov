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
public class PositionDao extends JdbcCrudDao<Position>{

    private static final String TABLE_NAME = "position";
    private static final String ACCOUNT_ID = "account_id";
    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;


    @Autowired
    public PositionDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ACCOUNT_ID);
    }


    public Optional<Position> findByAccountId(int id){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ACCOUNT_ID + " =?";
        try {
            Position position = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Position.class), id);
            if (position != null){
                return Optional.of(position);
            }
        } catch(Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }


    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return simpleJdbcInsert;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return ACCOUNT_ID;
    }

    @Override
    Class<Position> getEntityClass() {
        return Position.class;
    }

    @Override
    public int updateOne(Position entity) {
        return 0;
    }
}
