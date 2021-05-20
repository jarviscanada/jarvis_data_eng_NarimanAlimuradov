package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class AccountDaoIntTest {

    @Autowired
    AccountDao accountDao;
    @Autowired
    TraderDao traderDao;
    private Account account;
    private Trader trader;

    @Before
    public void setUp() throws Exception {
        traderDao.deleteAll();
        accountDao.deleteAll();

        trader = new Trader();
        trader.setFirstname("Nariman");
        trader.setLastname("Alimuradov");
        trader.setEmail("nariman@email.com");
        trader.setDate(DateFormat.getDateInstance().parse("2021-05-19"));
        trader.setCountry("Canada");

        account = new Account();
        account.setAmount(15.5);
        account.setTraderId(trader.getId());
    }

    @Test
    public void updateOne() {
        account.setAmount(15.6);
        accountDao.updateOne(account);
        double newAmount = accountDao.findById(account.getId()).get().getAmount();
        assert(Math.abs(newAmount - 15.6) < 0.1);
    }

    @Test
    public void findByTraderId() {
        double amount = accountDao.findByTraderId(trader.getId()).get().getAmount();
        assert(Math.abs(amount - 15.5) < 0.1);
    }
}