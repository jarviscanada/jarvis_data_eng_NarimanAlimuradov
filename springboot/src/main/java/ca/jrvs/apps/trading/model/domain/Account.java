package ca.jrvs.apps.trading.model.domain;

public class Account implements Entity<Integer>{

    private int id;
    private int traderId;
    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer integer) {
        this.id = integer;
    }

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
