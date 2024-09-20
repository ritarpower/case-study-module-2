package controller;

import model.MemeToken;
import service.MeMeTokenService.IMemeTokenService;
import service.MeMeTokenService.MemeTokenService;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MemeTokenController {
    private IMemeTokenService memeTokenService = new MemeTokenService();
    private static final Scanner scanner = new Scanner(System.in);

    public void getAll(){
        List<MemeToken> memeTokens = memeTokenService.getAll();
        for (MemeToken memeToken : memeTokens) {
            if(memeToken != null){
                System.out.println(memeToken);
            } else {
                return;
            }
        }
    }

    public void addMemeToken(){
        System.out.println("Nhap ma cua giao dich: ");
        int num = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap ki hieu cua meme Token: ");
        String symbol = scanner.nextLine();
        System.out.println("Nhap ten cua meme Token: ");
        String name = scanner.nextLine();
        System.out.println("Nhap gia cua Token($): ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhap ngay Token theo yyyy-mm-dd : ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.println("Nhap so tien mua Token ($): ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhap he meme cua Token: ");
        String memeType = scanner.nextLine();
        MemeToken memeToken = new MemeToken(num, symbol, name, price, date, amount, memeType);
        memeTokenService.add(memeToken);
        System.out.println("Da them thanh cong giao dich moi!");
    }

    public void updateMemeToken(){
        System.out.println("Nhap ma cua giao dich can chinh sua: ");
        int number = Integer.parseInt(scanner.nextLine());
        MemeToken memeToken = memeTokenService.findByNumber(number);
        if(memeToken != null){
            System.out.println("Nhap ki hieu cua Token: ");
            String symbol = scanner.nextLine();
            memeToken.setSymbol(symbol);
            System.out.println("Nhap ten moi cua Token: ");
            String name = scanner.nextLine();
            memeToken.setName(name);
            System.out.println("Nhap gia moi cua Token($): ");
            double price = Double.parseDouble(scanner.nextLine());
            memeToken.setPrice(price);
            System.out.println("Nhap ngay mua moi cua Token yyyy-mm-dd : ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            memeToken.setDate(date);
            System.out.println("Nhap so tien mua Token($): ");
            double amount = Double.parseDouble(scanner.nextLine());
            memeToken.setAmount(amount);
            System.out.println("Nhap he meme moi cua Token: ");
            String memeType = scanner.nextLine();
            memeToken.setMemeType(memeType);
            memeTokenService.add(memeToken);
            System.out.println("Da chinh sua Token giao dich so " + number + "thanh cong!");
        }else {
            System.out.println("Khong tim thay giao dich can chinh sua!");
        }
    }

    public MemeToken findMemeToken(int number){
        return memeTokenService.findByNumber(number);
    }

    public void deleteMemeTokenByNumber(){
        System.out.println("Nhap STT giao dich can xoa: ");
        int number = Integer.parseInt(scanner.nextLine());
        MemeToken memeToken = memeTokenService.findByNumber(number);
        if(memeToken != null){
            confirmDeleteMemeToken(memeToken);
        }else {
            System.out.println("Khong tim thay giao dich can xoa!");
        }
    }

    public void confirmDeleteMemeToken(MemeToken memeToken){
        System.out.println("Ban chac chan muon xoa giao dich " + memeToken + "o sTT " + memeToken.getNumber()+ " chu?\n" +
                "1.\t Co. \t 2.Khong.");
        int number = Integer.parseInt(scanner.nextLine());
        if(number == 1){
            memeTokenService.delete(memeToken);
        }else if(number == 2){
            System.out.println("Vui long chon lai muc khac!");
        }else{
            System.out.println("Vui long chon Co hoac Khong!");
        }
    }
}
