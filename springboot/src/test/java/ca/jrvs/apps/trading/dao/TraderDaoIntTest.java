package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {

    @Autowired
    private TraderDao traderDao;

    private Trader savedTrader;

    @Before
    public void setUp() throws Exception {
    }

    public void findAllById(){
        List<Trader> traders = Lists.newArrayList(traderDao.findAllById(Arrays.asList(savedTrader.getId() - 1)));
        assertEquals(traders.size(), 1);
        assertEquals(savedTrader.getCountry(), traders.get(0).getCountry());
    }

    @Test
    public void getJdbcTemplate() {
    }

    @Test
    public void getSimpleJdbcInsert() {
    }

    @Test
    public void getTableName() {
    }

    @Test
    public void getIdColumnName() {
    }

    @Test
    public void getEntityClass() {
    }

    @Test
    public void updateOne() {
    }
}