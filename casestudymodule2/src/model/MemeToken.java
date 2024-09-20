package model;

import java.time.LocalDate;

public class MemeToken extends Token {
    private String memeType;

    public MemeToken(int number, String symbol, String name, double price, LocalDate date, double amount, String memeType) {
        super(number, symbol, name, price, date, amount);
        this.memeType = memeType;
    }

    public String getMemeType() {
        return memeType;
    }

    public void setMemeType(String memeType) {
        this.memeType = memeType;
    }

    @Override
    public String toString() {
        return "MemeToken{" + super.toString() +
                "memeType='" + memeType + '\'' +
                '}';
    }
}
