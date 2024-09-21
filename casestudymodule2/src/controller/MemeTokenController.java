package controller;

import common.IOMemeTokenFile;
import model.MemeToken;
import service.MeMeTokenService.IMemeTokenService;
import service.MeMeTokenService.MemeTokenService;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemeTokenController {
    private IMemeTokenService memeTokenService = new MemeTokenService();
    private static final Scanner sc = new Scanner(System.in);

    public void getAll() {
        List<MemeToken> memeTokens = memeTokenService.getAll();
        for (MemeToken memeToken : memeTokens) {
            if (memeToken != null) {
                System.out.println(memeToken);
            } else {
                return;
            }
        }
    }

    public void addMemeToken() {
        String code;
        do {
            System.out.println("Nhap ma cua giao dich (Eg: MC-xxx) : ");
            code = sc.nextLine();
        } while (!isValidCode(code) || isExistCode(code));
        String symbol;
        do {
            System.out.println("Nhap ki hieu cua meme Token (Eg: PEPE) : ");
            symbol = sc.nextLine();
        } while (!isValidSymbol(symbol));
        String name;
        do {
            System.out.println("Nhap ten cua meme Token (Eg: Pepe) : ");
            name = sc.nextLine();
        } while (!isValidName(name));
        double price;
//        do {
            System.out.println("Nhap gia cua Token($): ");
            price = Double.parseDouble(sc.nextLine());
//        } while (!isValidPrice(price));
        LocalDate date;
        do {
            System.out.println("Nhap ngay mua Token (yyyy-mm-dd) : ");
            date = LocalDate.parse(sc.nextLine());
        } while (!isValidDate(date));
        double amount;
        do {
            System.out.println("Nhap so tien mua meme Token ($): ");
            amount = Double.parseDouble(sc.nextLine());
        } while (!isValidAmount(amount));
        String memeType;
        do {
            System.out.println("Nhap he cua meme Token (Eg: Frog) : ");
            memeType = sc.nextLine();
        } while (!isValidName(memeType));
        MemeToken memeToken = new MemeToken(code, symbol, name, price, date, amount, memeType);
        memeTokenService.add(memeToken);
        System.out.println("Da them thanh cong giao dich moi!");
    }

    public void updateMemeToken() {
        System.out.println("Nhap ma cua giao dich can chinh sua: ");
        String code = sc.nextLine();
        MemeToken memeToken = memeTokenService.findByCode(code);
        if (memeToken != null) {
            String symbol;
            do {
                System.out.println("Nhap ki hieu moi cua meme Token  (Eg: PEPE) : ");
                symbol = sc.nextLine();
            } while (!isValidSymbol(symbol));
            memeToken.setSymbol(symbol);
            String name;
            do {
                System.out.println("Nhap ten moi cua meme Token  (Eg: Pepe) : ");
                name = sc.nextLine();
            } while (!isValidName(name));
            memeToken.setName(name);
            double price;
//            do {
                System.out.println("Nhap gia moi cua Token($): ");
                price = Double.parseDouble(sc.nextLine());
//            } while (!isValidPrice(price));
            memeToken.setPrice(price);
            LocalDate date;
            do {
                System.out.println("Nhap ngay mua moi Token (yyyy-mm-dd) : ");
                date = LocalDate.parse(sc.nextLine());
            } while (!isValidDate(date));
            memeToken.setDate(date);
            double amount;
            do {
                System.out.println("Nhap so tien mua moi meme Token ($): ");
                amount = Double.parseDouble(sc.nextLine());
            } while (!isValidAmount(amount));
            memeToken.setAmount(amount);
            String memeType;
            do {
                System.out.println("Nhap he moi cua meme Token  (Eg: Frog) : ");
                memeType = sc.nextLine();
            } while (!isValidName(memeType));
            memeToken.setMemeType(memeType);
            memeTokenService.update(memeToken);
            System.out.println("Da chinh sua Token giao dich ma " + code + " thanh cong!");
        } else {
            System.out.println("Khong tim thay giao dich can chinh sua!");
        }
    }

    public void deleteMemeToken() {
        System.out.println("Nhap ma giao dich can xoa: ");
        String code = sc.nextLine();
        MemeToken memeToken = memeTokenService.findByCode(code);
        if (memeToken != null) {
            confirmDeleteMemeToken(memeToken);
        } else {
            System.out.println("Khong tim thay giao dich can xoa!");
        }
    }

    public void confirmDeleteMemeToken(MemeToken memeToken) {
        System.out.println("Ban chac chan muon xoa giao dich mua " + memeToken.getName() + " ma " + memeToken.getCode() + " chu?\n" +
                "1.\t Co. \t 2.Khong.\n" +
                "Chon muc: ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                memeTokenService.delete(memeToken);
                System.out.println("Da xoa thanh cong!");
                break;
            case 2:
                System.out.println("Chon lai muc khac!");
                break;
            default:
                System.out.println("Thao tac khong thanh cong!");
        }
    }

    public boolean isExistCode(String code){
        List<MemeToken> memeLists =  IOMemeTokenFile.readMemeTokenFromFile();
        for(MemeToken memeToken : memeLists){
            if(memeToken.getCode().equals(code)){
                return true;
            }
        }
        return false;
    }

    public boolean isValidCode(String code) {
        Pattern pattern = Pattern.compile("^MC-\\d{3}$");
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    public boolean isValidSymbol(String symbol) {
        Pattern pattern = Pattern.compile("^[A-Z0-9]+$");
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^([A-Z][a-z0-9]*(\\s|$))*$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidPrice(BigDecimal price){
        Pattern pattern = Pattern.compile("^(0\\.(0{0,7}[1-9]\\d*|[1-9]\\d*)|([1-9]\\d*)(\\.\\d+)?)$");
        Matcher matcher = pattern.matcher(String.valueOf(price));
        return matcher.matches();
    }

    public boolean isValidDate(LocalDate date) {
        Pattern pattern = Pattern.compile("^\\d{4}[-|/]\\d{2}[-|/]\\d{2}$");
        Matcher matcher = pattern.matcher(date.toString());
        return matcher.matches();
    }

    public boolean isValidAmount(double amount) {
        Pattern pattern = Pattern.compile("^(0\\.\\d+|([1-9]\\d*)(\\.\\d+)?)$");
        Matcher matcher = pattern.matcher(String.valueOf(amount));
        return matcher.matches();
    }
}
