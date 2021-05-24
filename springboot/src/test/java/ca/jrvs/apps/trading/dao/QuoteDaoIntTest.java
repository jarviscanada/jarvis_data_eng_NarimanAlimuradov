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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        quoteDao.deleteAll();
        savedQuote = new Quote();
        savedQuote.setAskPrice(1000.0);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(10.0);
        savedQuote.setBidSize(11);
        savedQuote.setId("aapl");
        savedQuote.setLastPrice(20.0);
        quoteDao.save(savedQuote);
    }

    @Test
    public void save() {
        assertEquals(savedQuote.getId(), "aapl");
        assertEquals((int) savedQuote.getAskSize(), 10);
        assertEquals((int) savedQuote.getBidSize(), 11);
    }

    @Test
    public void addOne() {
        Quote newQuote = new Quote();
        newQuote.setId("aapl2");
        newQuote.setAskSize(1000);
        newQuote.setAskPrice(50.5);
        newQuote.setBidPrice(50.6);
        newQuote.setBidSize(12);
        newQuote.setLastPrice(20.2);
        quoteDao.addOne(newQuote);

        assertEquals(newQuote.getId(), "aapl2");
        assertEquals((int) newQuote.getAskSize(), 1000);
    }

    @Test
    public void updateOne() {
        savedQuote.setAskSize(1500);
        quoteDao.updateOne(savedQuote);
        assertEquals((int) savedQuote.getAskSize(), 1500);
    }

    @Test
    public void saveAll() {
        List<Quote> quotes = new ArrayList<>();

        Quote newQuote = new Quote();
        newQuote.setId("aapl2");
        newQuote.setAskSize(1000);
        newQuote.setAskPrice(50.5);
        newQuote.setBidPrice(50.6);
        newQuote.setBidSize(12);
        newQuote.setLastPrice(20.2);

        Quote newQuote2 = new Quote();
        newQuote2.setId("aapl3");
        newQuote2.setAskSize(1000);
        newQuote2.setAskPrice(50.5);
        newQuote2.setBidPrice(50.6);
        newQuote2.setBidSize(12);
        newQuote2.setLastPrice(20.2);

        quotes.add(newQuote);
        quotes.add(newQuote2);

        quoteDao.saveAll(quotes);
        assertNotNull(newQuote2);
        assertNotNull(newQuote);
    }

    @Test
    public void findById() {
        Optional<Quote> findQuote = quoteDao.findById("aapl");
        assertEquals(findQuote.get().getId(), "aapl");
        assertEquals((int) findQuote.get().getAskSize(), 10);
    }

    @Test
    public void existsById() {
        assertFalse(quoteDao.existsById("aaaapl"));
        assertTrue(quoteDao.existsById("aapl"));
    }

    @Test
    public void findAll() {
        List<Quote> quotes = (List<Quote>) quoteDao.findAll();
        assertEquals(quotes.size(), 1);
        assertEquals(quotes.get(0).getId(), "aapl");
    }

    @Test
    public void count() {
        assertEquals(quoteDao.count(),  1);
    }

    @Test
    public void deleteById() {
        assertTrue(quoteDao.existsById("aapl"));
        quoteDao.deleteById("aapl");
        assertFalse(quoteDao.existsById("aapl"));
    }
}