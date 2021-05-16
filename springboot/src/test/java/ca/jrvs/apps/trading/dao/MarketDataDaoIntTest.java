package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MarketDataDaoIntTest {

    private MarketDataDao dao;

    @org.junit.Before
    public void setUp() throws Exception {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(50);
        manager.setDefaultMaxPerRoute(50);

        MarketDataConfig config = new MarketDataConfig();
        config.setHost("https://cloud.iexapis.com/v1/");
        config.setToken(System.getenv("IEX_PUB_TOKEN"));

        dao = new MarketDataDao(manager, config);
    }

    @org.junit.Test
    public void findAllById() {
        List<IexQuote> quotes = dao.findAllById(Arrays.asList("AAPL", "FB"));
        assertEquals(quotes.size(), 2);
        assertEquals(quotes.get(0).getSymbol(), "AAPL");
        assertEquals(quotes.get(1).getSymbol(), "FB");
    }

    @org.junit.Test
    public void findById() {
        IexQuote quote = dao.findById("AAPL").get();
        assertEquals(quote.getSymbol(), "AAPL");
    }
}