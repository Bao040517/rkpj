package Presentation;

import Repository.Entity.Customer;
import Service.service.CustomerService;

import java.util.List;
import java.util.Scanner;

public class MenuCustomer {

    private final CustomerService customerService;

    public MenuCustomer(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void showCustomerMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("========== QUẢN LÝ KHÁCH HÀNG ==========");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng theo ID");
            System.out.println("5. Quay lại menu chính");
            System.out.println("=======================================");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số");
                continue;
            }

            switch (choice) {

                case 1: {
                    System.out.println("Hiển thị danh sách khách hàng");

                    List<Customer> customerList = customerService.getAllCustomers();

                    if (customerList.isEmpty()) {
                        System.out.println("Chưa có khách hàng nào");
                        break;
                    }

                    for (Customer c : customerList) {
                        System.out.println(
                                "ID: " + c.getId()
                                        + " | Name: " + c.getName()
                                        + " | Phone: " + c.getPhone()
                                        + " | Email: " + c.getEmail()
                                        + " | Address: " + c.getAddress()
                        );
                    }
                    break;
                }

                case 2: {
                    System.out.println("Thêm khách hàng mới");
                    Customer customer = new Customer();
                    customerService.addCustomer(customer);
                    System.out.println("Thêm khách hàng thành công");
                    break;
                }

                case 3: {
                    System.out.println("Cập nhật thông tin khách hàng");

                    try {
                        System.out.print("Nhập ID khách hàng cần cập nhật: ");
                        int id = Integer.parseInt(sc.nextLine());

                        Customer currentCustomer =
                                customerService.getCustomerById(id);

                        System.out.println("Thông tin hiện tại:");
                        System.out.println("Name: " + currentCustomer.getName());
                        System.out.println("Phone: " + currentCustomer.getPhone());
                        System.out.println("Email: " + currentCustomer.getEmail());
                        System.out.println("Address: " + currentCustomer.getAddress());

                        int subChoice;
                        do {
                            System.out.println("----- MENU CẬP NHẬT KHÁCH HÀNG -----");
                            System.out.println("1. Cập nhật tên");
                            System.out.println("2. Cập nhật số điện thoại");
                            System.out.println("3. Cập nhật email");
                            System.out.println("4. Cập nhật địa chỉ");
                            System.out.println("5. Lưu & quay lại");
                            System.out.print("Chọn: ");

                            subChoice = Integer.parseInt(sc.nextLine());

                            switch (subChoice) {
                                case 1:
                                    System.out.print("Tên mới: ");
                                    currentCustomer.setName(sc.nextLine());
                                    break;
                                case 2:
                                    System.out.print("Số điện thoại mới: ");
                                    currentCustomer.setPhone(sc.nextLine());
                                    break;
                                case 3:
                                    System.out.print("Email mới: ");
                                    currentCustomer.setEmail(sc.nextLine());
                                    break;
                                case 4:
                                    System.out.print("Địa chỉ mới: ");
                                    currentCustomer.setAddress(sc.nextLine());
                                    break;
                                case 5:
                                    customerService.updateCustomer(currentCustomer.getId(),currentCustomer);
                                    System.out.println("Cập nhật khách hàng thành công");
                                    break;
                                default:
                                    System.out.println("Lựa chọn không hợp lệ");
                            }

                        } while (subChoice != 5);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 4: {
                    System.out.println("Xóa khách hàng theo ID");
                    System.out.print("Nhập ID khách hàng cần xóa: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        customerService.deleteCustomer(id);
                        System.out.println("Xóa khách hàng thành công");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 5:
                    System.out.println("Quay lại menu chính");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 5);
    }
}
