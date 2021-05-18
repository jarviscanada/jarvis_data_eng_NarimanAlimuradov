package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private QuoteDao quoteDao;

    private Quote quote;

    @Before
    public void setUp() throws Exception {
        quoteDao.deleteAll();
    }

    @Test
    public void findIexQuoteByTicker() {
        IexQuote iexQuote = quoteService.findIexQuoteByTicker("aapl");
        assertEquals(iexQuote.getSymbol(), "aapl");
    }

    @Test
    public void updateMarketData() {
        quoteService.updateMarketData();
        assertNotNull(quoteDao.findById("aapl"));
        assertEquals(quoteDao.findById("aapl"), "aapl");
    }

    @Test
    public void buildQuoteFromIexQuote() {
        IexQuote iexQuote = quoteService.findIexQuoteByTicker("aapl");
        Quote buildQuote = quoteService.buildQuoteFromIexQuote(iexQuote);
        assertNotNull(buildQuote);
        assertEquals(buildQuote.getId(), "aapl");
    }

    @Test
    public void saveQuotes() {
    }

    @Test
    public void saveQuote() {
    }

    @Test
    public void testSaveQuote() {
    }

    @Test
    public void findAllQuotes() {
    }
}