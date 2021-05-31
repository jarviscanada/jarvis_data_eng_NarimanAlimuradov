package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;
    private HttpClientConnectionManager httpClientConnectionManager;
    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);

    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> quote;
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0){
            return Optional.empty();
        } else if (quotes.size() == 1){
            quote = Optional.of(quotes.get(0));
        } else {
            throw new DataRetrievalFailureException("Unexpected amount of quotes");
        }

        return quote;
    }

    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers) {
        List<IexQuote> quotes = new ArrayList<>();

        for (String ticker : tickers){
            String url = String.format(IEX_BATCH_URL, ticker);
            String response = null;
            try {
                response = executeHttpGet(url).orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));
            } catch (IOException e) {
                throw new DataRetrievalFailureException("Unsuccessful HTTP request");
            }
            JSONObject IexQuotesJson = new JSONObject(response);

            if (IexQuotesJson.length() == 0) {
                throw new IllegalArgumentException("Invalid ticker");
            }

            JSONObject json = IexQuotesJson.getJSONObject(ticker);
            ObjectMapper mapper = new ObjectMapper();
            try {
                IexQuote quote = mapper.readValue(json.get("quote").toString(), IexQuote.class);
                quotes.add(quote);
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid ticker");
            }
        }

        return quotes;
    }

    private Optional<String> executeHttpGet(String url) throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpRequest request = new HttpGet(url);
        HttpResponse response = httpClient.execute((HttpUriRequest) request);
        return Optional.of(EntityUtils.toString(response.getEntity()));
    }

    private CloseableHttpClient getHttpClient(){
        return HttpClients.custom().setConnectionManager(httpClientConnectionManager).setConnectionManagerShared(true).build();
    }

    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<IexQuote> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> S save(S s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
