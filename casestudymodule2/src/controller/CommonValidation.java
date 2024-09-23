package controller;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidation {
    public static boolean isValidCode(String code) {
        Pattern pattern = Pattern.compile("^MC-\\d{3}$");
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    public static boolean isValidSymbol(String symbol) {
        Pattern pattern = Pattern.compile("^[A-Z0-9]+$");
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^([A-Z][a-z0-9]*(\\s|$))*$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidPrice(String price) {
        Pattern pattern = Pattern.compile("^\\d*.\\d*$");
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    public static boolean isValidDate(LocalDate date) {
        Pattern pattern = Pattern.compile("^\\d{4}[-|/]\\d{2}[-|/]\\d{2}$");
        Matcher matcher = pattern.matcher(date.toString());
        return matcher.matches();
    }
}
