package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderAccountServiceIntTest {

    private TraderAccountView savedView;

    @Autowired
    private TraderAccountService traderAccountService;

    @Autowired
    private TraderDao traderDao;

    @Autowired
    private AccountDao accountDao;

    private Trader trader;

    @Before
    public void setUp() throws Exception {
        traderDao.deleteAll();
        accountDao.deleteAll();

        trader = new Trader();
        trader.setFirstName("Nariman");
        trader.setLastName("Alimuradov");
        trader.setEmail("nariman@email.com");
        trader.setDob(new Date(2021, 5, 19));
        trader.setCountry("Canada");
    }

    @Test
    public void createTraderAndAccount() {
        traderAccountService.createTraderAndAccount(trader);
        assertTrue(traderDao.existsById(trader.getId()));
    }

    @Test
    public void deleteTraderById() {
        traderAccountService.createTraderAndAccount(trader);
        assertTrue(traderDao.existsById(trader.getId()));
        traderAccountService.deleteTraderById(trader.getId());
        assertFalse(traderDao.existsById(trader.getId()));
    }

    @Test
    public void deposit() {
        traderAccountService.createTraderAndAccount(trader);
        Account account = traderAccountService.deposit(trader.getId(),1500.0);
        assert(Math.abs(account.getAmount() -  1500) < 0.1);
    }

    @Test
    public void withdraw() {
        traderAccountService.createTraderAndAccount(trader);
        Account account = traderAccountService.deposit(trader.getId(),1500.0);
        account = traderAccountService.withdraw(trader.getId(), 1000.0);
        assert(Math.abs(account.getAmount() -  500) < 0.1);
    }
}