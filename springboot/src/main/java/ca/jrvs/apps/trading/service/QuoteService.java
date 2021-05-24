package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao){
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    public IexQuote findIexQuoteByTicker(String ticker){
        return marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
    }

    public void updateMarketData(){
        List<Quote> quotes = findAllQuotes();
        for (Quote quote : quotes){
            String ticker = quote.getTicker();
            IexQuote iexQuote = marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
            Quote resultQuote = buildQuoteFromIexQuote(iexQuote);
            quoteDao.save(resultQuote);
        }
    }

    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote){
        Quote quote = new Quote();
        quote.setId(iexQuote.getSymbol());
        quote.setAskPrice((double) iexQuote.getIexAskPrice());
        quote.setAskSize((int) iexQuote.getIexAskSize());
        quote.setBidPrice((double) iexQuote.getIexBidPrice());
        quote.setBidSize((int) iexQuote.getIexBidSize());
        quote.setLastPrice((double) iexQuote.getIexLastUpdated());

        return quote;
    }

    public List<Quote> saveQuotes(List<String> tickers){
        List<Quote> quotes = new ArrayList<>();
        for (String ticker : tickers){
            quotes.add(saveQuote(ticker));
        }
        return quotes;
    }

    public Quote saveQuote(String ticker){
        Optional<IexQuote> iexQuote = marketDataDao.findById(ticker);
        Quote quote = buildQuoteFromIexQuote(iexQuote.orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid")));
        return quote;
    }

    public Quote saveQuote(Quote quote){
        return quoteDao.save(quote);
    }

    public List<Quote> findAllQuotes(){
        return (List<Quote>) quoteDao.findAll();
    }
}
