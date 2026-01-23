package Presentation;

import Repository.Entity.Invoice;
import Repository.Entity.InvoiceDetails;
import Repository.Entity.Product;
import Service.service.InvoiceService;
import Service.service.ProductService;
import dtos.Statistics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuInvoice {
    private final InvoiceService invoiceService;
    private final ProductService productService;

    public MenuInvoice(InvoiceService invoiceService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.productService = productService;
    }

    public void showInvoiceMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("========== QUẢN LÝ HÓA ĐƠN ==========");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm mới hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Quay lại menu chính");
            System.out.println("====================================");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.println("Hiển thị danh sách hóa đơn");
                    List<Invoice> invoiceList = invoiceService.getAllInvoices();
                    for (Invoice invoice : invoiceList) {
                        System.out.println(
                                "ID: " + invoice.getId()
                                        + " | Customer ID: " + invoice.getCustomerId()
                                        + " | Created At: " + invoice.getCreatedAt()
                                        + " | Total Amount: " + invoice.getTotalAmount()
                        );
                    }
                    break;
                }
                case 2: {
                    System.out.println("Thêm mới hóa đơn");

                    try {
                        System.out.print("Nhập customer ID: ");
                        int customerId = Integer.parseInt(sc.nextLine());

                        Invoice invoice = new Invoice();
                        invoice.setCustomerId(customerId);

                        Integer invoiceId = invoiceService.createInvoice(invoice);
                        boolean productAdded = false;

                        List<Product> productList = productService.getAllProducts();
                        System.out.println("----- DANH SÁCH SẢN PHẨM -----");
                        for (Product product : productList) {
                            System.out.println("ID: " + product.getId() + " | Tên: " + product.getName() + " | Giá: " + product.getPrice() + " | Số lượng tồn: " + product.getStock());
                        }
                        System.out.println("-----------------------------");

                        String addAnotherProduct;
                        do {
                            try {
                                System.out.println("Nhập chi tiết hóa đơn (nhập 'q' ở mã sản phẩm để thoát):");
                                System.out.print("Nhập mã sản phẩm: ");
                                String productIdInput = sc.nextLine();

                                if (productIdInput.equalsIgnoreCase("q")) {
                                    break;
                                }

                                int productId = Integer.parseInt(productIdInput);

                                System.out.print("Nhập số lượng: ");
                                int quantity = Integer.parseInt(sc.nextLine());

                                InvoiceDetails invoiceDetails = new InvoiceDetails();
                                invoiceDetails.setInvoiceId(invoiceId);
                                invoiceDetails.setProductId(productId);
                                invoiceDetails.setQuantity(quantity);

                                invoiceService.createInvoiceDetails(invoiceDetails);
                                productAdded = true;
                                System.out.println("Thêm sản phẩm thành công.");

                            } catch (NumberFormatException e) {
                                System.out.println("Mã sản phẩm hoặc số lượng phải là số. Vui lòng thử lại.");
                            } catch (RuntimeException e) {
                                System.out.println("Lỗi: " + e.getMessage() + ". Vui lòng thử lại.");
                            }

                            System.out.print("Bạn có muốn thêm sản phẩm khác không? (y/n): ");
                            addAnotherProduct = sc.nextLine();
                        } while (addAnotherProduct.equalsIgnoreCase("y"));

                        if (productAdded) {
                            System.out.println("Tạo hóa đơn thành công");
                            System.out.println("Invoice ID = " + invoiceId);
                        } else {
                            invoiceService.deleteInvoice(invoiceId);
                            System.out.println("Hóa đơn đã được hủy vì không có sản phẩm nào được thêm.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Customer ID phải là số");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }


                case 3: {
                    int subChoice = 0;

                    do {
                        System.out.println("----- MENU TÌM KIẾM / THỐNG KÊ HÓA ĐƠN -----");
                        System.out.println("1. Tìm theo tên khách hàng");
                        System.out.println("2. Tìm theo ngày (dd/MM/yyyy)");
                        System.out.println("3. Thống kê doanh thu theo ngày");
                        System.out.println("4. Thống kê doanh thu theo tháng");
                        System.out.println("5. Thống kê doanh thu theo năm");
                        System.out.println("6. Quay lại menu hóa đơn");
                        System.out.println("-------------------------------------------");
                        System.out.print("Nhập lựa chọn: ");

                        try {
                            subChoice = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Vui lòng nhập số");
                            continue;
                        }

                        switch (subChoice) {

                            case 1: {
                                System.out.print("Nhập tên khách hàng: ");
                                String customerName = sc.nextLine();

                                List<Invoice> invoices =
                                        invoiceService.searchByCustomerName(customerName);

                                if (invoices.isEmpty()) {
                                    System.out.println("Không tìm thấy hóa đơn nào");
                                    break;
                                }

                                for (Invoice invoice : invoices) {
                                    System.out.println(
                                            "ID: " + invoice.getId()
                                                    + " | Customer ID: " + invoice.getCustomerId()
                                                    + " | Created At: " + invoice.getCreatedAt()
                                                    + " | Total Amount: " + invoice.getTotalAmount()
                                    );
                                }
                                break;
                            }

                            case 2: {
                                try {
                                    System.out.print("Nhập ngày (dd/MM/yyyy): ");
                                    LocalDate date = LocalDate.parse(
                                            sc.nextLine(),
                                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                    );

                                    List<Invoice> invoices = invoiceService.searchByDate(date);

                                    if (invoices.isEmpty()) {
                                        System.out.println("Không có hóa đơn trong ngày này");
                                        break;
                                    }

                                    for (Invoice invoice : invoices) {
                                        System.out.println(
                                                "ID: " + invoice.getId()
                                                        + " | Customer ID: " + invoice.getCustomerId()
                                                        + " | Created At: " + invoice.getCreatedAt()
                                                        + " | Total Amount: " + invoice.getTotalAmount()
                                        );
                                    }

                                } catch (DateTimeParseException e) {
                                    System.out.println("Sai định dạng ngày (dd/MM/yyyy)");
                                }
                                break;
                            }

                            case 3: {
                                System.out.print("Nhập tháng: ");
                                int month = Integer.parseInt(sc.nextLine());
                                System.out.print("Nhập năm: ");
                                int year = Integer.parseInt(sc.nextLine());

                                List<Statistics> stats = invoiceService.statisticByDay(month, year);

                                for (Statistics s : stats) {
                                    System.out.println(
                                            "Ngày: " + s.getDay()
                                                    + "/" + s.getMonth()
                                                    + "/" + s.getYear()
                                                    + " | Doanh thu: " + s.getRevenue()
                                    );
                                }
                                break;
                            }

                            case 4: {
                                System.out.print("Nhập năm: ");
                                int year = Integer.parseInt(sc.nextLine());

                                List<Statistics> stats = invoiceService.statisticByMonth(year);

                                for (Statistics s : stats) {
                                    System.out.println(
                                            "Tháng: " + s.getMonth()
                                                    + "/" + s.getYear()
                                                    + " | Doanh thu: " + s.getRevenue()
                                    );
                                }
                                break;
                            }

                            case 5: {
                                List<Statistics> stats = invoiceService.statisticByYear();

                                for (Statistics s : stats) {
                                    System.out.println(
                                            "Năm: " + s.getYear()
                                                    + " | Doanh thu: " + s.getRevenue()
                                    );
                                }
                                break;
                            }

                            case 6:
                                System.out.println("Quay lại menu hóa đơn");
                                break;

                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                        }

                    } while (subChoice != 6);

                    break;
                }

                case 4:
                    System.out.println("Quay lại menu chính");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 4);
    }
}
