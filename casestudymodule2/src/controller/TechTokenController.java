package controller;

import common.IOTechTokenFile;
import model.TechnologyToken;
import service.TechTokenService.ITechTokenService;
import service.TechTokenService.TechTokenService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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
            if (isExistCode(code)) {
                System.out.println("Ma giao dich da ton tai!");
            } else if (!CommonValidation.isValidCode(code)) {
                System.out.println("Ma giao dich chua dung!");
            }
        } while (isExistCode(code) || !CommonValidation.isValidCode(code));
        String symbol;
        do {
            System.out.println("Nhap ki hieu cua Token cong nghe (Eg: LINK) : ");
            symbol = sc.nextLine();
            if (!CommonValidation.isValidSymbol(symbol)) {
                System.out.println("Vui long nhap lai ki hieu!");
            }
        } while (!CommonValidation.isValidSymbol(symbol));
        String name;
        do {
            System.out.println("Nhap ten cua Token cong nghe (Eg: Chainlink) : ");
            name = sc.nextLine();
            if (!CommonValidation.isValidName(name)) {
                System.out.println("Vui long nhap lai ten!");
            }
        } while (!CommonValidation.isValidName(name));
        String price1;
        do {
            System.out.println("Nhap gia cua Token($): ");
            price1 = sc.nextLine();
            if (!CommonValidation.isValidPrice(price1)) {
                System.out.println("Vui long nhap lai gia!");
            }
        } while (!CommonValidation.isValidPrice(price1));
        double price = Double.parseDouble(price1);
        LocalDate date;
        do {
            System.out.println("Nhap ngay mua Token (yyyy-mm-dd) : ");
            String dateStr = sc.nextLine();
            date = LocalDate.parse(dateStr);
            if (!CommonValidation.isValidDate(date)) {
                System.out.println("Vui long nhap lai ngay mua!");
            }
        } while (!CommonValidation.isValidDate(date));
        String amount1;
        do {
            System.out.println("Nhap so tien mua Token ($): ");
            amount1 = sc.nextLine();
            if (!CommonValidation.isValidPrice(amount1)) {
                System.out.println("Vui long nhap lai tien mua!");
            }
        } while (!CommonValidation.isValidPrice(amount1));
        double amount = Double.parseDouble(amount1);
        String techTokenType;
        do {
            System.out.println("Nhap cong nghe cua Token (Eg: Oracel) : ");
            techTokenType = sc.nextLine();
            if (!CommonValidation.isValidName(techTokenType)) {
                System.out.println("Vui long nhap lai cong nghe cua Token!");
            }
        } while (!CommonValidation.isValidName(techTokenType));
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
                if (!CommonValidation.isValidSymbol(symbol)) {
                    System.out.println("Vui long nhap lai ki hieu!");
                }
            } while (!CommonValidation.isValidSymbol(symbol));
            techToken.setSymbol(symbol);
            String name;
            do {
                System.out.println("Nhap ten moi cua Token cong nghe (Eg: Chainlink) : ");
                name = sc.nextLine();
                if (!CommonValidation.isValidName(name)) {
                    System.out.println("Vui long nhap lai ten!");
                }
            } while (!CommonValidation.isValidName(name));
            techToken.setName(name);
            String price1;
            do {
                System.out.println("Nhap gia moi cua Token($): ");
                price1 = sc.nextLine();
                if (!CommonValidation.isValidPrice(price1)) {
                    System.out.println("Vui long nhap lai gia!");
                }
            } while (!CommonValidation.isValidPrice(price1));
            double price = Double.parseDouble(price1);
            techToken.setPrice(price);
            LocalDate date;
            do {
                System.out.println("Nhap ngay mua Token (yyyy-mm-dd) : ");
                String data = sc.nextLine();
                date = LocalDate.parse(data);
                if (!CommonValidation.isValidDate(date)) {
                    System.out.println("Vui long nhap lai ngay mua!");
                }
            } while (!CommonValidation.isValidDate(date));
            String amount1;
            do {
                System.out.println("Nhap so tien mua moi cua Token($): ");
                amount1 = sc.nextLine();
                if (!CommonValidation.isValidPrice(amount1)) {
                    System.out.println("Vui long nhap lai tien mua!");
                }
            } while (!CommonValidation.isValidPrice(amount1));
            double amount = Double.parseDouble(amount1);
            techToken.setAmount(amount);
            String techTokenType;
            do {
                System.out.println("Nhap moi loai cong nghe cua Token (Eg: Oracel) : ");
                techTokenType = sc.nextLine();
                if (!CommonValidation.isValidName(techTokenType)) {
                    System.out.println("Vui long nhap lai cong nghe cua Token!");
                }
            } while (!CommonValidation.isValidName(techTokenType));
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
            System.out.println("Khong tim thay ma giao dich can xoa! ");
        }
    }

    public void confirmDeleteTechToken(TechnologyToken techToken) {
        System.out.println("Ban chac chan muon xoa giao dich mua " + techToken.getName() + " ma " + techToken.getCode() + " chu?\n" +
                "\t 1.Co \t 2.Khong\n" +
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
}
