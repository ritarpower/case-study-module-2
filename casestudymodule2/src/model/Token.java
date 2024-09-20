package model;

import java.time.LocalDate;

public class Token {
    private int number;
    private String symbol;
    private String name;
    private double price;
    private LocalDate date;
    private double amount;

    public Token(int number, String symbol, String name, double price, LocalDate date, double amount) {
        this.number = number;
        this.symbol = symbol;
        this.amount = amount;
        this.date = date;
        this.price = price;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        return  "number=" + number +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", amount=" + amount +
                ", ";
    }
}
