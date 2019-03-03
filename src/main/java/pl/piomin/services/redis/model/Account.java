package pl.piomin.services.redis.model;

import org.springframework.data.redis.core.index.Indexed;

public class Account {

    @Indexed private Long id;
    private String number;
    private int balance;

    public Account(Long id, String number, int balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
