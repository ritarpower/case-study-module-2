package controller;

import common.IOMemeTokenFile;
import model.MemeToken;
import service.MeMeTokenService.IMemeTokenService;
import service.MeMeTokenService.MemeTokenService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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
            if (!CommonValidation.isValidCode(code)) {
                System.out.println("Ma giao dich chua dung!");
            } else if (isExistCode(code)) {
                System.out.println("Ma giao dich da ton tai!");
            }
        } while (!CommonValidation.isValidCode(code) || isExistCode(code));
        String symbol;
        do {
            System.out.println("Nhap ki hieu cua meme Token (Eg: PEPE) : ");
            symbol = sc.nextLine();
            if (!CommonValidation.isValidSymbol(symbol)) {
                System.out.println("Vui long nhap lai ki hieu!");
            }
        } while (!CommonValidation.isValidSymbol(symbol));
        String name;
        do {
            System.out.println("Nhap ten cua meme Token (Eg: Pepe) : ");
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
            date = LocalDate.parse(sc.nextLine());
            if (!CommonValidation.isValidDate(date)) {
                System.out.println("Vui long nhap lai ngay mua!");
            }
        } while (!CommonValidation.isValidDate(date));
        String amount1;
        do {
            System.out.println("Nhap so tien mua meme Token ($): ");
            amount1 = sc.nextLine();
            if (!CommonValidation.isValidPrice(amount1)) {
                System.out.println("Vui long nhap lai tien mua!");
            }
        } while (!CommonValidation.isValidPrice(amount1));
        double amount = Double.parseDouble(amount1);
        String memeType;
        do {
            System.out.println("Nhap he cua meme Token (Eg: Frog) : ");
            memeType = sc.nextLine();
            if (!CommonValidation.isValidName(memeType)) {
                System.out.println("Vui long nhap lai he cua Token!");
            }
        } while (!CommonValidation.isValidName(memeType));
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
                if (!CommonValidation.isValidSymbol(symbol)) {
                    System.out.println("Vui long nhap lai ki hieu!");
                }
            } while (!CommonValidation.isValidSymbol(symbol));
            memeToken.setSymbol(symbol);
            String name;
            do {
                System.out.println("Nhap ten moi cua meme Token  (Eg: Pepe) : ");
                name = sc.nextLine();
                if (!CommonValidation.isValidName(name)) {
                    System.out.println("Vui long nhap lai ten!");
                }
            } while (!CommonValidation.isValidName(name));
            memeToken.setName(name);
            String price1;
            do {
                System.out.println("Nhap gia moi cua Token($): ");
                price1 = sc.nextLine();
                if (!CommonValidation.isValidPrice(price1)) {
                    System.out.println("Vui long nhap lai gia!");
                }
            } while (!CommonValidation.isValidPrice(price1));
            double price = Double.parseDouble(price1);
            memeToken.setPrice(price);
            LocalDate date;
            do {
                System.out.println("Nhap ngay mua moi Token (yyyy-mm-dd) : ");
                date = LocalDate.parse(sc.nextLine());
                if (!CommonValidation.isValidDate(date)) {
                    System.out.println("Vui long nhap lai ngay mua!");
                }
            } while (!CommonValidation.isValidDate(date));
            memeToken.setDate(date);
            String amount1;
            do {
                System.out.println("Nhap so tien mua moi meme Token ($): ");
                amount1 = sc.nextLine();
                if (!CommonValidation.isValidPrice(amount1)) {
                    System.out.println("Vui long nhap lai tien mua!");
                }
            } while (!CommonValidation.isValidPrice(amount1));
            double amount = Double.parseDouble(amount1);
            memeToken.setAmount(amount);
            String memeType;
            do {
                System.out.println("Nhap he moi cua meme Token  (Eg: Frog) : ");
                memeType = sc.nextLine();
                if (!CommonValidation.isValidName(memeType)) {
                    System.out.println("Vui long nhap lai he cua Token!");
                }
            } while (!CommonValidation.isValidName(memeType));
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
                "\t 1.Co \t 2.Khong\n" +
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

    public boolean isExistCode(String code) {
        List<MemeToken> memeLists = IOMemeTokenFile.readMemeTokenFromFile();
        for (MemeToken memeToken : memeLists) {
            if (memeToken.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
