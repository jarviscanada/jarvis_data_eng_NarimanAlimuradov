package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {

    private TraderDao traderDao;
    private AccountDao accountDao;
    private PositionDao positionDao;
    private SecurityOrderDao securityOrderDao;

    @Autowired
    public TraderAccountService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao, SecurityOrderDao securityOrderDao) {
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.securityOrderDao = securityOrderDao;
    }

    public TraderAccountView createTraderAndAccount(Trader myTrader){

        if (myTrader.getId() == null){
            throw new DataRetrievalFailureException("Invalid trader");
        }

        Trader trader = traderDao.save(myTrader);
        Account account = new Account();
        account.setTraderId(trader.getId());
        account.setId(trader.getId());
        account.setAmount((double) 0);

        return new TraderAccountView(trader, account);
    }

    public void deleteTraderById(Integer traderId){
        if (traderId == null){
            throw new IllegalArgumentException("Null trader ID");
        }

        Account account = accountDao.findById(traderId).get();
        if (account.getAmount() != 0){
            throw new IllegalArgumentException("Non zero balance, unable to delete");
        }

        if (!positionDao.findByAccountId(account.getId()).isPresent()) {
            throw new IllegalArgumentException("Position does not exist");
        }

        securityOrderDao.deleteByAccountId(account.getId());
        accountDao.deleteById(traderId);
        traderDao.deleteById(traderId);
    }

    public Account deposit(Integer traderId, Double fund){
        if (fund < 0 || traderId == null){
            throw new IllegalArgumentException("Invalid input");
        }

        Account account = accountDao.findByTraderId(traderId).get();
        account.setAmount(account.getAmount() + fund);
        accountDao.updateOne(account);
        return account;
    }

    public Account withdraw(Integer traderId, Double fund){
        if (fund < 0 || traderId == null){
            throw new IllegalArgumentException("Invalid input");
        }
        Account account = accountDao.findByTraderId(traderId).get();

        if (fund > account.getAmount()){
            throw new IllegalArgumentException("Not enough money in account");
        }
        account.setAmount(account.getAmount() - fund);
        accountDao.updateOne(account);
        return account;
    }
}
