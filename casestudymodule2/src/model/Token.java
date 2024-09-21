package model;

import java.time.LocalDate;

public class Token {
    private String code;
    private String symbol;
    private String name;
    private double price;
    private LocalDate date;
    private double amount;

    public Token(String code, String symbol, String name, double price, LocalDate date, double amount) {
        this.code = code;
        this.symbol = symbol;
        this.amount = amount;
        this.date = date;
        this.price = price;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  "code=" + code +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price + "$" +
                ", date=" + date +
                ", amount=" + amount + "$" +
                ", ";
    }
}
