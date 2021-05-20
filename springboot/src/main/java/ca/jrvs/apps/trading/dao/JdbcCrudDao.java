package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);
    abstract public JdbcTemplate getJdbcTemplate();
    abstract public SimpleJdbcInsert getSimpleJdbcInsert();
    abstract public String getTableName();
    abstract public String getIdColumnName();
    abstract Class<T> getEntityClass();

    @Override
    public <S extends T> S save(S entity) {
        if (existsById(entity.getId())){
            if (updateOne(entity) != 1){
                throw new DataRetrievalFailureException("Unable to update quote");
            }
        } else {
            addOne(entity);
        }
        return entity;
    }

    private <S extends T> void addOne(S entity){
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);
        Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
        entity.setId(newId.intValue());
    }

    abstract public int updateOne(T entity);

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Optional<T> findById(Integer id) {
        Optional<T> entity = Optional.empty();
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
        try {
            entity = Optional.ofNullable((T) getJdbcTemplate()
                    .queryForObject(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()), id));
        } catch (IncorrectResultSizeDataAccessException e){
            logger.debug("Can't find trader id: " + id, e);
        }
        return entity;
    }

    @Override
    public boolean existsById(Integer id) {
        if (findById(id).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Iterable<T> findAll() {
        String query = "SELECT * FROM " + getTableName();
        return getJdbcTemplate().query(query, BeanPropertyRowMapper.newInstance(getEntityClass()));
    }

    @Override
    public Iterable<T> findAllById(Iterable<Integer> ids) {
        List<T> queries = new ArrayList<>();
        for (int id : ids){
            Optional<T> item = findById(id);
            queries.add(item.get());
        }
        return queries;
    }

    @Override
    public long count() {
        String query = "SELECT COUNT(*) FROM " + getTableName();
        long count = getJdbcTemplate().queryForObject(query, long.class);
        return count;
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
        getJdbcTemplate().update(query, id);
    }

    @Override
    public void delete(T t) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM " + getTableName();
        getJdbcTemplate().update(query);
    }
}
