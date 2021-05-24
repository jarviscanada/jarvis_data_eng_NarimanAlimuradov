package ca.jrvs.apps.trading.model.domain;

public class Position {

    private Integer account_id;
    private String ticker;
    private Integer position;

    public Integer getAccountId() {
        return this.account_id;
    }

    public String getTicker() {
        return ticker;
    }

    public Integer getPosition() {
        return position;
    }
}
