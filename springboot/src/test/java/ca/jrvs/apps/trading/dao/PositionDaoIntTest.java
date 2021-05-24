package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class PositionDaoIntTest {

    @Autowired
    QuoteDao quoteDao;
    @Autowired
    PositionDao positionDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    TraderDao traderDao;
    @Autowired
    SecurityOrderDao securityOrderDao;

    private Quote quote;
    private Account account;
    private Trader trader;
    private SecurityOrder securityOrder;

    @Before
    public void setUp() throws Exception {
        traderDao.deleteAll();
        accountDao.deleteAll();
        securityOrderDao.deleteAll();
        quoteDao.deleteAll();

        quote = new Quote();
        quote.setAskPrice(1000.0);
        quote.setAskSize(10);
        quote.setBidPrice(10.0);
        quote.setBidSize(11);
        quote.setId("aapl");
        quote.setLastPrice(20.0);
        quoteDao.save(quote);

        trader = new Trader();
        trader.setFirstName("Nariman");
        trader.setLastName("Alimuradov");
        trader.setEmail("nariman@email.com");
        trader.setDob(new Date(2021, 5, 19));
        trader.setCountry("Canada");
        traderDao.save(trader);

        account = new Account();
        account.setAmount(15.5);
        account.setTraderId(trader.getId());
        accountDao.save(account);

        securityOrder = new SecurityOrder();
        securityOrder.setNotes("my notes");
        securityOrder.setPrice(33.3);
        securityOrder.setSize(1);
        securityOrder.setTicker(quote.getTicker());
        securityOrder.setStatus(new String[]{"ok"});
        securityOrder.setAccountId(account.getId());
        securityOrderDao.save(securityOrder);
    }

    @Test
    public void findById() {
        Optional<Position> position = positionDao.findById(account.getId());
        assertEquals(position.get().getAccountId(), account.getId());
    }
}