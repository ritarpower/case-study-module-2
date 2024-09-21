package view;

import controller.MemeTokenController;
import controller.TechTokenController;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenManagement {
    private static final Scanner sc = new Scanner(System.in);
    private MemeTokenController memeTokenController = new MemeTokenController();
    private TechTokenController techTokenController = new TechTokenController();

    public void manageMainMenu() {
        do {
            System.out.println("-----CHUONG TRINH QUAN LY GIAO DICH TOKEN-----\n" +
                    "1.\tQuan ly giao dich Token cong nghe.\n" +
                    "2.\tQuan ly giao dich Token meme.\n" +
                    "3.\tKet thuc chuong trinh.\n" +
                    "Nhap vao muc can chon: ");
            int choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    manageTechTokenMenu();
                    break;
                case 2:
                    manageMemeTokenMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Vui long chon lai!");
            }
        } while (true);

    }

    public void manageMemeTokenMenu() {
        do {
            System.out.println("----Quan ly giao dich Token meme----\n" +
                    "1.\tHien thi danh sach giao dich.\n" +
                    "2.\tThem moi giao dich.\n" +
                    "3.\tChinh sua giao dich.\n" +
                    "4.\tXoa giao dich.\n" +
                    "5.\tQuay lai menu chinh.\n" +
                    "6.\tKet thuc chuong trinh.\n" +
                    "Nhap vao muc can chon: ");
            int choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    memeTokenController.getAll();
                    break;
                case 2:
                    memeTokenController.addMemeToken();
                    break;
                case 3:
                    memeTokenController.updateMemeToken();
                    break;
                case 4:
                    memeTokenController.deleteMemeToken();
                    break;
                case 5:
                    manageMainMenu();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Vui long chon lai!");
            }
        } while (true);
    }

    public void manageTechTokenMenu() {
        do {
            System.out.println("----Quan ly giao dich Token cong nghe----\n" +
                    "1.\tHien thi danh sach giao dich.\n" +
                    "2.\tThem moi giao dich.\n" +
                    "3.\tChinh sua giao dich.\n" +
                    "4.\tXoa giao dich.\n" +
                    "5.\tQuay lai menu chinh.\n" +
                    "6.\tKet thuc chuong trinh.\n" +
                    "Nhap vao muc can chon: ");
            int choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    techTokenController.getAll();
                    break;
                case 2:
                    techTokenController.addTechToken();
                    break;
                case 3:
                    techTokenController.updateTechToken();
                    break;
                case 4:
                    techTokenController.deleteTechToken();
                    break;
                case 5:
                    manageMainMenu();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Vui long chon lai!");
            }
        } while (true);
    }

    public static void main(String[] args) {
        TokenManagement tokenManagement = new TokenManagement();
        tokenManagement.manageMainMenu();
    }

}
