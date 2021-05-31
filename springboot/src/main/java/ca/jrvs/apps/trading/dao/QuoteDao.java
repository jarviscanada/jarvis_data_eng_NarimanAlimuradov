package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";
    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
    }

    @Override
    public Quote save(Quote quote) {
        if (existsById(quote.getId())){
            if (updateOne(quote) != 1){
                throw new DataRetrievalFailureException("Cannot update quote");
            }
        } else {
            addOne(quote);
        }
        return quote;
    }

    public void addOne(Quote quote){
        SqlParameterSource source = new BeanPropertySqlParameterSource(quote);
        int row = simpleJdbcInsert.execute(source);
        if (row != 1){
            throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
        }
    }

    public int updateOne(Quote quote){
        String updateSql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
        return jdbcTemplate.update(updateSql, makeUpdateValues(quote));
    }

    private Object[] makeUpdateValues(Quote quote){
        Object[] values = {quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(), quote.getAskPrice(), quote.getAskSize(), quote.getTicker()};
        return values;
    }

    @Override
    public <S extends Quote> Iterable<S> saveAll(Iterable<S> iterable) {
        for (Quote quote : iterable){
            save(quote);
        }
        return iterable;
    }

    @Override
    public Optional<Quote> findById(String id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
        try {
            Quote quote = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Quote.class), id);
            if (quote != null){
                return Optional.of(quote);
            }
        } catch(Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(String id) {
        if (findById(id).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Iterable<Quote> findAll() {
        String query = "SELECT * FROM " + TABLE_NAME;
        List<Quote> quotes =  jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Quote.class));
        return quotes;
    }

    @Override
    public long count() {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
        long count = jdbcTemplate.queryForObject(query, long.class);
        return count;
    }

    @Override
    public void deleteById(String id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.update(query);
    }

    @Override
    public void delete(Quote quote) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<Quote> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
