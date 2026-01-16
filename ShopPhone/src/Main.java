//import Repository.dao.*;
//import Repository.Entity.*;
//import Service.service.*;
//import Service.serviceImpl.*;
//
//import java.time.LocalDate;
//import java.util.Scanner;
//
//public class Main {
//
//    static Scanner sc = new Scanner(System.in);
//
//    static AdminService adminService =
//            new AdminServiceImpl(new AdminRepoImpl());
//
//    static ProductService productService =
//            new ProductServiceImpl(new ProductRepoImpl());
//
//    static CustomerService customerService =
//            new CustomerServiceImpl(new CustomerRepoImpl());
//
//    static InvoiceService invoiceService =
//            new InvoiceServiceImpl(new InvoiceRepoImpl());
//
//    public static void main(String[] args) {
//
//        // ========== LOGIN ==========
//        while (true) {
//            System.out.println("===== ĐĂNG NHẬP ADMIN =====");
//            System.out.print("Username: ");
//            String username = sc.nextLine();
//            System.out.print("Password: ");
//            String password = sc.nextLine();
//
//            if (adminService.login(username, password)) {
//                break;
//            }
//            System.out.println("Sai thông tin đăng nhập, nhập lại!\n");
//        }
//
//        int choice;
//        do {
//            System.out.println("\n===== MENU CHÍNH =====");
//            System.out.println("1. Quản lý điện thoại");
//            System.out.println("2. Quản lý khách hàng");
//            System.out.println("3. Quản lý hóa đơn");
//            System.out.println("0. Thoát");
//
//            System.out.print("Chọn: ");
//            choice = Integer.parseInt(sc.nextLine());
//
//            switch (choice) {
//                case 1:
//                    menuProduct();
//                    break;
//                case 2:
//                    menuCustomer();
//                    break;
//                case 3:
//                    menuInvoice();
//                    break;
//                case 0:
//                    System.out.println("Thoát chương trình");
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ!");
//            }
//        } while (choice != 0);
//    }
//
//    // ================== PRODUCT ==================
//    static void menuProduct() {
//        int choice;
//        do {
//            System.out.println("\n--- QUẢN LÝ ĐIỆN THOẠI ---");
//            System.out.println("1. Thêm sản phẩm");
//            System.out.println("2. Cập nhật sản phẩm");
//            System.out.println("3. Xóa sản phẩm");
//            System.out.println("4. Hiển thị danh sách");
//            System.out.println("5. Tìm theo brand");
//            System.out.println("6. Tìm theo khoảng giá");
//            System.out.println("7. Xem tồn kho");
//            System.out.println("0. Quay về");
//
//            System.out.print("Chọn: ");
//            choice = Integer.parseInt(sc.nextLine());
//
//            switch (choice) {
//                case 1:
//                    addProduct();
//                    break;
//                case 2:
//                    updateProduct();
//                    break;
//                case 3:
//                    deleteProduct();
//                    break;
//                case 4:
//                    productService.getAllProducts().forEach(System.out::println);
//                    break;
//                case 5:
//                    System.out.print("Nhập brand: ");
//                    productService.searchByBrand(sc.nextLine())
//                            .forEach(System.out::println);
//                    break;
//                case 6:
//                    System.out.print("Giá từ: ");
//                    double min = Double.parseDouble(sc.nextLine());
//                    System.out.print("Giá đến: ");
//                    double max = Double.parseDouble(sc.nextLine());
//                    productService.searchByPriceRange(min, max)
//                            .forEach(System.out::println);
//                    break;
//                case 7:
//                    System.out.print("Tên sản phẩm: ");
//                    productService.searchByStock(sc.nextLine());
//                    break;
//                case 0:
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ!");
//            }
//        } while (choice != 0);
//    }
//
//    // ================== CUSTOMER ==================
//    static void menuCustomer() {
//        int choice;
//        do {
//            System.out.println("\n--- QUẢN LÝ KHÁCH HÀNG ---");
//            System.out.println("1. Thêm khách hàng");
//            System.out.println("2. Cập nhật khách hàng");
//            System.out.println("3. Xóa khách hàng");
//            System.out.println("4. Hiển thị danh sách");
//            System.out.println("0. Quay về");
//
//            System.out.print("Chọn: ");
//            choice = Integer.parseInt(sc.nextLine());
//
//            switch (choice) {
//                case 1:
//                    addCustomer();
//                    break;
//                case 2:
//                    updateCustomer();
//                    break;
//                case 3:
//                    deleteCustomer();
//                    break;
//                case 4:
//                    customerService.getAllCustomers().forEach(System.out::println);
//                    break;
//                case 0:
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ!");
//            }
//        } while (choice != 0);
//    }
//
//    // ================== INVOICE ==================
//    static void menuInvoice() {
//        int choice;
//        do {
//            System.out.println("\n--- QUẢN LÝ HÓA ĐƠN ---");
//            System.out.println("1. Thêm hóa đơn");
//            System.out.println("2. Hiển thị danh sách");
//            System.out.println("3. Tìm theo tên khách hàng");
//            System.out.println("4. Tìm theo ngày");
//            System.out.println("5. Thống kê doanh thu");
//            System.out.println("0. Quay về");
//
//            System.out.print("Chọn: ");
//            choice = Integer.parseInt(sc.nextLine());
//
//            switch (choice) {
//                case 1:
//                    addInvoice();
//                    break;
//                case 2:
//                    invoiceService.getAllInvoices().forEach(System.out::println);
//                    break;
//                case 3:
//                    System.out.print("Tên khách hàng: ");
//                    invoiceService.searchByCustomerName(sc.nextLine())
//                            .forEach(System.out::println);
//                    break;
//                case 4:
//                    System.out.print("Ngày (yyyy-mm-dd): ");
//                    LocalDate date = LocalDate.parse(sc.nextLine());
//                    invoiceService.searchByDate(date)
//                            .forEach(System.out::println);
//                    break;
//                case 5:
//                    invoiceService.statisticByYear().forEach(System.out::println);
//                    break;
//                case 0:
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ!");
//            }
//        } while (choice != 0);
//    }
//
//    // ================== CRUD ==================
//    static void addProduct() {
//        Product p = new Product();
//        System.out.print("Tên: ");
//        p.setName(sc.nextLine());
//        System.out.print("Brand: ");
//        p.setBrand(sc.nextLine());
//        System.out.print("Giá: ");
//        p.setPrice(Double.parseDouble(sc.nextLine()));
//        System.out.print("Số lượng: ");
//        p.setStock(Integer.parseInt(sc.nextLine()));
//        productService.addProduct(p);
//    }
//
//    static void updateProduct() {
//        System.out.print("ID sản phẩm: ");
//        int id = Integer.parseInt(sc.nextLine());
//        Product p = new Product();
//        System.out.print("Tên mới: ");
//        p.setName(sc.nextLine());
//        System.out.print("Brand mới: ");
//        p.setBrand(sc.nextLine());
//        System.out.print("Giá mới: ");
//        p.setPrice(Double.parseDouble(sc.nextLine()));
//        System.out.print("Số lượng mới: ");
//        p.setStock(Integer.parseInt(sc.nextLine()));
//        productService.updateProduct(id, p);
//    }
//
//    static void deleteProduct() {
//        System.out.print("ID sản phẩm: ");
//        int id = Integer.parseInt(sc.nextLine());
//        System.out.print("Xác nhận xóa (Y/N): ");
//        if (sc.nextLine().equalsIgnoreCase("Y")) {
//            productService.deleteProduct(id);
//        }
//    }
//
//    static void addCustomer() {
//        Customer c = new Customer();
//        System.out.print("Tên: ");
//        c.setName(sc.nextLine());
//        System.out.print("SĐT: ");
//        c.setPhone(sc.nextLine());
//        System.out.print("Email: ");
//        c.setEmail(sc.nextLine());
//        System.out.print("Địa chỉ: ");
//        c.setAddress(sc.nextLine());
//        customerService.addCustomer(c);
//    }
//
//    static void updateCustomer() {
//        System.out.print("ID khách hàng: ");
//        int id = Integer.parseInt(sc.nextLine());
//        Customer c = new Customer();
//        System.out.print("Tên mới: ");
//        c.setName(sc.nextLine());
//        System.out.print("SĐT mới: ");
//        c.setPhone(sc.nextLine());
//        System.out.print("Email mới: ");
//        c.setEmail(sc.nextLine());
//        System.out.print("Địa chỉ mới: ");
//        c.setAddress(sc.nextLine());
//        customerService.updateCustomer(id, c);
//    }
//
//    static void deleteCustomer() {
//        System.out.print("ID khách hàng: ");
//        int id = Integer.parseInt(sc.nextLine());
//        System.out.print("Xác nhận xóa (Y/N): ");
//        if (sc.nextLine().equalsIgnoreCase("Y")) {
//            customerService.deleteCustomer(id);
//        }
//    }
//
//    static void addInvoice() {
//        System.out.print("Customer ID: ");
//        int customerId = Integer.parseInt(sc.nextLine());
//        Invoice invoice = new Invoice();
//        invoice.setCustomerId(customerId);
//        invoiceService.createInvoice(invoice);
//    }
//}
