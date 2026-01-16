package Presentation;

import Repository.Entity.Product;
import Service.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class MenuPhone {

    private final ProductService productService;

    public MenuPhone(ProductService productService) {
        this.productService = productService;
    }

    public void showPhoneMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("========== QUẢN LÝ SẢN PHẨM ==========");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo Brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.println("====================================");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {

                case 1: {
                    System.out.println("Hiển thị danh sách sản phẩm");
                    List<Product> productList = productService.getAllProducts();
                    for (Product product : productList) {
                        System.out.println(
                                "ID: " + product.getId()
                                        + " | Name: " + product.getName()
                                        + " | Brand: " + product.getBrand()
                                        + " | Price: " + product.getPrice()
                        );
                    }
                    break;
                }

                case 2: {
                    System.out.println("Thêm sản phẩm mới");
                    Product product = new Product();

                    System.out.print("Name: ");
                    product.setName(sc.nextLine());

                    System.out.print("Brand: ");
                    product.setBrand(sc.nextLine());

                    System.out.print("Price: ");
                    product.setPrice(Double.parseDouble(sc.nextLine()));

                    productService.addProduct(product);
                    break;
                }

                case 3: {
                    System.out.println("Cập nhật thông tin sản phẩm");

                    try {
                        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
                        Integer id = Integer.parseInt(sc.nextLine());

                        Product currentProduct =
                                productService.getProductById(id).get(0);
                        // Em thưa thầy sao cái Extenstion lại gợi ý cái get(0) này để làm gì ạ

                        int choiceUpdate;
                        do {
                            System.out.println("----- MENU CẬP NHẬT -----");
                            System.out.println("1. Cập nhật tên");
                            System.out.println("2. Cập nhật Brand");
                            System.out.println("3. Cập nhật Giá");
                            System.out.println("4. Cập nhật tồn kho");
                            System.out.println("5. Lưu & quay lại");
                            System.out.print("Chọn: ");

                            choiceUpdate = Integer.parseInt(sc.nextLine());

                            switch (choiceUpdate) {
                                case 1:
                                    System.out.print("Tên mới: ");
                                    currentProduct.setName(sc.nextLine());
                                    break;

                                case 2:
                                    System.out.print("Brand mới: ");
                                    currentProduct.setBrand(sc.nextLine());
                                    break;

                                case 3:
                                    System.out.print("Giá mới: ");
                                    currentProduct.setPrice(
                                            Double.parseDouble(sc.nextLine()));
                                    break;

                                case 4:
                                    System.out.print("Tồn kho mới: ");
                                    currentProduct.setStock(
                                            Integer.parseInt(sc.nextLine()));
                                    break;

                                case 5:
                                    productService.updateProduct(id, currentProduct);
                                    System.out.println("Cập nhật thành công");
                                    break;

                                default:
                                    System.out.println("Không đúng lựa chọn");
                            }

                        } while (choiceUpdate != 5);

                    } catch (NumberFormatException e) {
                        System.out.println("Dữ liệu nhập không hợp lệ");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case 4: {
                    System.out.println("Xóa sản phẩm theo ID");

                    try {
                        System.out.print("Nhập ID sản phẩm muốn xoá: ");
                        Integer id = Integer.parseInt(sc.nextLine());

                        Product del =
                                productService.getProductById(id).get(0);

                        productService.deleteProduct(del.getId());

                    } catch (NumberFormatException e) {
                        System.out.println("Dữ liệu nhập không hợp lệ");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case 5: {
                    System.out.println("Tìm kiếm theo Brand");
                    System.out.print("Nhập Brand: ");

                    List<Product> productList =
                            productService.searchByBrand(sc.nextLine());

                    for (Product p : productList) {
                        System.out.println(
                                p.getName() + " | "
                                        + p.getBrand() + " | "
                                        + p.getPrice()
                        );
                    }
                    break;
                }

                case 6: {
                    System.out.println("Tìm kiếm theo khoảng giá");

                    System.out.print("Giá thấp nhất: ");
                    double priceFrom = Double.parseDouble(sc.nextLine());

                    System.out.print("Giá cao nhất: ");
                    double priceTo = Double.parseDouble(sc.nextLine());

                    List<Product> productList =
                            productService.searchByPriceRange(priceFrom, priceTo);

                    for (Product p : productList) {
                        System.out.println(
                                p.getName() + " | "
                                        + p.getBrand() + " | "
                                        + p.getPrice()
                        );
                    }
                    break;
                }

                case 7: {
                    System.out.println("Tìm kiếm theo tồn kho");
                    System.out.print("Nhập tên sản phẩm: ");
                    productService.searchByStock(sc.nextLine());
                    break;
                }

                case 8:
                    System.out.println("Quay lại menu chính");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 8);
    }
}
