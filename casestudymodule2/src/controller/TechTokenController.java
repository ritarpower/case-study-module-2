package controller;

import common.IOTechTokenFile;
import model.TechnologyToken;
import service.TechTokenService.ITechTokenService;
import service.TechTokenService.TechTokenService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TechTokenController {
    private ITechTokenService techTokenService = new TechTokenService();
    private static final Scanner sc = new Scanner(System.in);

    public void getAll() {
        List<TechnologyToken> techTokens = techTokenService.getAll();
        for (TechnologyToken techToken : techTokens) {
            if (techToken != null) {
                System.out.println(techToken);
            } else {
                return;
            }
        }
    }

    public void addTechToken() {
        String code;
        do {
            System.out.println("Nhap ma cua giao dich (TC-xxx) : ");
            code = sc.nextLine();
        } while (isExistCode(code) || !isValidCode(code));
        String symbol;
        do {
            System.out.println("Nhap ki hieu cua Token cong nghe (Eg: LINK) : ");
            symbol = sc.nextLine();
        } while (!isValidSymbol(symbol));
        String name;
        do {
            System.out.println("Nhap ten cua Token cong nghe (Eg: Chainlink) : ");
            name = sc.nextLine();
        } while (!isValidName(name));
        double price;
        do {
            System.out.println("Nhap gia cua Token($): ");
            price = Double.parseDouble(sc.nextLine());
        } while (!isValidPrice(price));
        LocalDate date;
        do {
            System.out.println("Nhap ngay mua Token (yyyy-mm-dd) : ");
            String dateStr = sc.nextLine();
            date = LocalDate.parse(dateStr);
        } while (!isValidDate(date));
        double amount;
        do {
            System.out.println("Nhap so tien mua Token ($): ");
            amount = Double.parseDouble(sc.nextLine());
        } while (!isValidAmount(amount));
        String techTokenType;
        do {
            System.out.println("Nhap cong nghe cua Token (Eg: Oracel) : ");
            techTokenType = sc.nextLine();
        } while (!isValidName(techTokenType));
        TechnologyToken token = new TechnologyToken(code, symbol, name, price, date, amount, techTokenType);
        techTokenService.add(token);
        System.out.println("Da them thanh cong giao dich!");
    }

    public void updateTechToken() {
        System.out.println("Nhap ma cua giao dich can chinh sua: ");
        String code = sc.nextLine();
        TechnologyToken techToken = techTokenService.findByCode(code);
        if (techToken != null) {
            String symbol;
            do {
                System.out.println("Nhap ki hieu moi cua Token (Eg: LINK) : ");
                symbol = sc.nextLine();
            } while (!isValidSymbol(symbol));
            techToken.setSymbol(symbol);
            String name;
            do {
                System.out.println("Nhap ten moi cua Token cong nghe (Eg: Chainlink) : ");
                name = sc.nextLine();
            } while (!isValidName(name));
            techToken.setName(name);
            double price;
            do {
                System.out.println("Nhap gia moi cua Token($): ");
                price = Double.parseDouble(sc.nextLine());
            } while (!isValidPrice(price));
            techToken.setPrice(price);
            LocalDate date;
            do {
                System.out.println("Nhap ngay mua Token (yyyy-mm-dd) : ");
                String data = sc.nextLine();
                date = LocalDate.parse(data);
            } while (!isValidDate(date));
            double amount;
            do {
                System.out.println("Nhap so tien mua moi cua Token($): ");
                amount = Double.parseDouble(sc.nextLine());
            } while (!isValidAmount(amount));
            techToken.setAmount(amount);
            String techTokenType;
            do {
                System.out.println("Nhap moi loai cong nghe cua Token (Eg: Oracel) : ");
                techTokenType = sc.nextLine();
            } while (!isValidName(techTokenType));
            techToken.setTechnologyType(techTokenType);
            techTokenService.update(techToken);
            System.out.println("Da chinh sua Token giao dich ma " + code + " thanh cong!");
        } else {
            System.out.println("Khong tim ma giao dich can chinh sua!");
        }
    }

    public void deleteTechToken() {
        System.out.println("Nhap ma cua giao dich can xoa: ");
        String code = sc.nextLine();
        TechnologyToken techToken = techTokenService.findByCode(code);
        if (techToken != null) {
            confirmDeleteTechToken(techToken);
        } else {
            System.out.println("Khong tim thay ma giao dich can xoa ");
        }
    }

    public void confirmDeleteTechToken(TechnologyToken techToken) {
        System.out.println("Ban chac chan muon xoa giao dich mua " + techToken.getName() + " ma " + techToken.getCode() + " chu?\n" +
                "1.\t Co. \t 2.Khong.\n" +
                "Chon muc: ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                techTokenService.delete(techToken);
                System.out.println("Da xoa thanh cong!");
                break;
            case 2:
                System.out.println("Chon lai muc khac!");
                break;
            default:
                System.out.println("Thao tac khong thanh cong!");
        }
    }

    public boolean isExistCode(String code) {
        List<TechnologyToken> techLists = IOTechTokenFile.readTechTokenFromFile();
        for (TechnologyToken techToken : techLists) {
            if (techToken.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidCode(String code) {
        Pattern pattern = Pattern.compile("^TC-\\d{3}$");
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    public boolean isValidSymbol(String symbol) {
        Pattern pattern = Pattern.compile("^[A-Z0-9]+$");
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z0-9\\s]*$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean isValidPrice(double price) {
        if(price >= 0.00000001) {
            return true;
        }
        return false;
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
