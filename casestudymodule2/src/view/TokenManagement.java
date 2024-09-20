package view;

import controller.MemeTokenController;
import controller.TechTokenController;

import java.util.Scanner;

public class TokenManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private MemeTokenController memeTokenController = new MemeTokenController();
    private TechTokenController techTokenController = new TechTokenController();

    public void manageMainMenu() {
        do {
            System.out.println("-----CHUONG TRINH QUAN LY GIAO DICH TOKEN-----\n" +
                    "1.\tQuan ly giao dich Token cong nghe.\n" +
                    "2.\tQuan ly giao dich Token meme.\n" +
                    "3.\tKet thuc chuong trinh.\n" +
                    "Nhap vao muc can chon: ");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    System.out.println("Quan ly giao dich Token cong nghe.");
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
            int choose = Integer.parseInt(scanner.nextLine());
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
                    memeTokenController.deleteMemeTokenByNumber();
                case 5:
                    manageMemeTokenMenu();
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
