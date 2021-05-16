package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

    @Autowired
    private QuoteDao quoteDao;
    private Quote savedQuote;

    @Before
    public void setUp() throws Exception {
        savedQuote.setAskPrice(1000.0);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(10.0);
        savedQuote.setBidSize(11);
        savedQuote.setId("aapl");
        savedQuote.setLastPrice(20.0);
        quoteDao.save(savedQuote);
    }

    @After
    public void tearDown() throws Exception {
        quoteDao.deleteById(savedQuote.getId());
    }

    @Test
    public void save() {
        assertEquals(savedQuote.getId(), "aapl");
        assertEquals((int) savedQuote.getAskSize(), 10);
        assertEquals((int) savedQuote.getBidSize(), 11);
    }

    @Test
    public void addOne() {
    }

    @Test
    public void updateOne() {
    }

    @Test
    public void saveAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void existsById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void count() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void delete() {
    }
}