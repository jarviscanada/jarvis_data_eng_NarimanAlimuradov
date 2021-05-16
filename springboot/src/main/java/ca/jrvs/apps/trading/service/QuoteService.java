package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao){
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    public IexQuote findIexQuoteByTicker(String ticker){
        return marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
    }

    public void updateMarketData(){

    }

    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote){
        return null;
    }

    public List<Quote> saveQuotes(List<String> tickers){
        return null;
    }

    public Quote saveQuote(String ticker){
        return null;
    }

    public Quote saveQuote(Quote quote){
        return quoteDao.save(quote);
    }

    public List<Quote> findAllQuotes(){
        return (List<Quote>) quoteDao.findAll();
    }
}
