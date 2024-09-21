package model;

import java.time.LocalDate;

public class MemeToken extends Token {
    private String memeType;

    public MemeToken(String code, String symbol, String name, double price, LocalDate date, double amount, String memeType) {
        super(code, symbol, name, price, date, amount);
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
        return "MemeToken\t\t{" + super.toString() +
                "memeType='" + memeType + '\'' +
                '}';
    }
}
