package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class PositionDaoIntTest {

    @Autowired
    PositionDao positionDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    TraderDao traderDao;

    private Position position;
    private Account account;
    private Trader trader;

    @Before
    public void setUp() throws Exception {
        traderDao.deleteAll();
        accountDao.deleteAll();
        positionDao.deleteAll();

        trader = new Trader();
        trader.setFirstname("Nariman");
        trader.setLastname("Alimuradov");
        trader.setEmail("nariman@email.com");
        trader.setDate(DateFormat.getDateInstance().parse("2021-05-19"));
        trader.setCountry("Canada");

        account = new Account();
        account.setAmount(15.5);
        account.setTraderId(trader.getId());

        position = new Position();
        position.setPosition(1);
        position.setTicker("aapl");
        position.setAccountId(account.getId());
    }

    @Test
    public void findByAccountId() {
        Position myPosition = positionDao.findByAccountId(account.getId()).get();
        assertEquals(myPosition.getTicker(), "aapl");
    }
}