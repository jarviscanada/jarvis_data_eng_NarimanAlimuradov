package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
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
public class SecurityOrderDaoIntTest {

    @Autowired
    SecurityOrderDao securityOrderDao;

    @Autowired
    AccountDao accountDao;

    @Autowired
    TraderDao traderDao;

    @Autowired
    QuoteDao quoteDao;

    private SecurityOrder securityOrder;
    private Account account;
    private Trader trader;
    private Quote quote;

    @Before
    public void setUp() throws Exception {
        quoteDao.deleteAll();
        traderDao.deleteAll();
        accountDao.deleteAll();
        securityOrderDao.deleteAll();

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
    public void deleteByAccountId() {
        assertTrue(securityOrderDao.findById(account.getId()).isPresent());
        securityOrderDao.deleteByAccountId(account.getId());
        assertFalse(securityOrderDao.findById(account.getId()).isPresent());
    }
}