package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
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
public class AccountDao extends JdbcCrudDao<Account> {

    private static final String TABLE_NAME = "account";
    private static final String ID_COLUMN_NAME = "id";
    private static final String TRADER_ID = "trader_id";
    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public AccountDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN_NAME);
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
        return ID_COLUMN_NAME;
    }

    public static String getTraderId() {
        return TRADER_ID;
    }

    @Override
    Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public int updateOne(Account account) {
        String updateSql = "UPDATE " + getTableName() + " SET amount=? WHERE " + getIdColumnName() + "=?";
        return jdbcTemplate.update(updateSql, account.getAmount(), account.getTraderId());
    }

    public Optional<Account> findByTraderId(Integer id){
        String query = "SELECT * FROM " + getTableName() + " WHERE " + getTraderId() + " =?";
        try {
            Account account = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Account.class), id);
            if (account != null){
                return Optional.of(account);
            }
        } catch(Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
