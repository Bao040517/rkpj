package Presentation;

import Repository.dao.InvoiceRepoImpl;
import Repository.dao.ProductRepoImpl;
import Repository.dao.CustomerRepoImpl;

import Service.service.InvoiceService;
import Service.service.ProductService;
import Service.service.CustomerService;

import Service.serviceImpl.InvoiceServiceImpl;
import Service.serviceImpl.ProductServiceImpl;
import Service.serviceImpl.CustomerServiceImpl;

import java.util.Scanner;

public class MenuManager {

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("========== MENU CHÍNH ==========");
            System.out.println("1. Quản lý sản phẩm điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hóa đơn");
            System.out.println("4. Thoát");
            System.out.println("================================");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số");
                continue;
            }

            switch (choice) {

                case 1: {
                    ProductService productService = new ProductServiceImpl(new ProductRepoImpl());
                    MenuPhone menuPhone = new MenuPhone(productService);
                    menuPhone.showPhoneMenu();
                    break;
                }

                case 2: {
                    CustomerService customerService = new CustomerServiceImpl(new CustomerRepoImpl());
                    MenuCustomer menuCustomer = new MenuCustomer(customerService);
                    menuCustomer.showCustomerMenu();
                    break;
                }

                case 3: {
                    InvoiceService invoiceService = new InvoiceServiceImpl(new InvoiceRepoImpl());
                    MenuInvoice menuInvoice = new MenuInvoice(invoiceService);
                    menuInvoice.showInvoiceMenu();
                    break;
                }

                case 4:
                    System.out.println("Thoát chương trình");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 4);
    }
}
