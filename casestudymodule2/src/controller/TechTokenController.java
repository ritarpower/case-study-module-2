package controller;

import model.TechnologyToken;
import service.TechTokenService.ITechTokenService;
import service.TechTokenService.TechTokenService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TechTokenController {
    private ITechTokenService techTokenService = new TechTokenService();
    private static final Scanner sc = new Scanner(System.in);

    public void getAll(){
        List<TechnologyToken> techTokens = techTokenService.getAll();
        for(TechnologyToken techToken : techTokens){
            if(techToken != null){
                System.out.println(techToken);
            }else{
                return;
            }
        }
    }

    public void addTechToken(){
        System.out.println("Nhap ma cua giao dich: ");
        int number = Integer.parseInt(sc.nextLine());
        System.out.println("Nhap ki hieu cua Token cong nghe: ");
        String symbol = sc.nextLine();
        System.out.println("Nhap ten cua Token: ");
        String name = sc.nextLine();
        System.out.println("Nhap gia cua Token: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.println("Nhap ngay mua Token theo yyyy-mm-dd: ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        System.out.println("Nhap so tien mua Token: ");
        double amount = Double.parseDouble(sc.nextLine());
        System.out.println("Nhap loai cong nghe cua Token: ");
        String techTokenType = sc.nextLine();
        TechnologyToken token = new TechnologyToken(number, symbol, name, price, date, amount, techTokenType);
        techTokenService.add(token);
        System.out.println("Da them thanh cong giao dich!");
    }

    public void updateTechToken(){
        System.out.println("Nhap ma cua giao dich can chinh sua: ");
        int number = Integer.parseInt(sc.nextLine());
        TechnologyToken token = techTokenService.findByNumber(number);
        if(token != null){
            System.out.println("Nhap ki hieu moi cua Token: ");
            String symbol = sc.nextLine();
            token.setSymbol(symbol);
            System.out.println("Nhap ten moi cua Token: ");
            String name = sc.nextLine();
            token.setName(name);
            System.out.println("Nhap gia moi cua Token: ");
            double price = Double.parseDouble(sc.nextLine());
            token.setPrice(price);
            System.out.println("Nhap ngay mua moi cua Token theo yyyy-mm-dd: ");
            LocalDate date = LocalDate.parse(sc.nextLine());
            token.setDate(date);
            System.out.println("Nhap so tien mua moi cua Token: ");
            double amount = Double.parseDouble(sc.nextLine());
            token.setAmount(amount);
            System.out.println("Nhap moi loai cong nghe cua Token: ");
            String techTokenType = sc.nextLine();
            token.setTechnologyType(techTokenType);
        }
    }
}
