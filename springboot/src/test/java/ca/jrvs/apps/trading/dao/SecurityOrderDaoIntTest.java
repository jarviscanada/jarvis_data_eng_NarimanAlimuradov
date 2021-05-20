package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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

    private SecurityOrder securityOrder;
    private Account account;
    private Trader trader;

    @Before
    public void setUp() throws Exception {
        traderDao.deleteAll();
        accountDao.deleteAll();
        securityOrderDao.deleteAll();

        trader = new Trader();
        trader.setFirstname("Nariman");
        trader.setLastname("Alimuradov");
        trader.setEmail("nariman@email.com");
        trader.setDate(DateFormat.getDateInstance().parse("2021-05-19"));
        trader.setCountry("Canada");

        account = new Account();
        account.setAmount(15.5);
        account.setTraderId(trader.getId());

        securityOrder = new SecurityOrder();
        securityOrder.setNotes("my notes");
        securityOrder.setPrice(33.3);
        securityOrder.setSize(1);
        securityOrder.setTicker("aapl");
        securityOrder.setStatus(new String[]{"ok"});
        securityOrder.setAccountId(account.getId());
    }

    @Test
    public void deleteByAccountId() {
        Optional<SecurityOrder> order = securityOrderDao.findById(securityOrder.getId());
        assertTrue(order.isPresent());
        securityOrderDao.deleteByAccountId(account.getId());
        assertFalse(order.isPresent());

    }
}