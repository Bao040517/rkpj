package Presentation;

import java.util.Scanner;

public class MenuPresentation {
    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("========== HỆ THỐNG QUẢN LÝ CỬA HÀNG ==========");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Thoát");
            System.out.println("============================================");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                choice = 0;
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println(" Chức năng đăng nhập Admin");
                    AdminMenuPresentation adminMenuPresentation = new AdminMenuPresentation();
                    break;
                case 2:
                    System.out.println(" Thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println(" Lựa chọn không hợp lệ!");
            }

        } while (choice != 2);
    }
}
