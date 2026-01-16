package Presentation;

import Repository.dao.AdminRepoImpl;
import Service.service.AdminService;
import Service.serviceImpl.AdminServiceImpl;

import java.util.Scanner;

public class AdminMenuPresentation {

    private static final AdminService adminService =
            new AdminServiceImpl(new AdminRepoImpl());

    public static boolean loginAdmin() {
        Scanner sc = new Scanner(System.in);

        System.out.println("========== ĐĂNG NHẬP QUẢN TRỊ ==========");
        System.out.print("Tài khoản: ");
        String username = sc.nextLine();

        System.out.print("Mật khẩu : ");
        String password = sc.nextLine();
        System.out.println("======================================");

        boolean result = adminService.login(username, password);

        if (result) {
            System.out.println("Đăng nhập thành công!");
            MenuManager.showMainMenu();
        } else {
            System.out.println("Đăng nhập thất bại!");
        }

        return result;
    }
}
