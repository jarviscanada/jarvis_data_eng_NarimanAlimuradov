package ca.jrvs.apps.trading.model.domain;

public class Position implements Entity<Integer> {

    private int accountId;
    private String ticker;
    private int position;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String size) {
        this.ticker = size;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
