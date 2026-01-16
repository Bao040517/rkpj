package Service.serviceImpl;

import Repository.Entity.Product;
import Repository.daoImpl.ProductRepository;
import Service.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productImpl;

    public ProductServiceImpl(ProductRepository productImpl) {
        this.productImpl = productImpl;
    }

    @Override
    public void addProduct(Product product) {
        productImpl.createProduct(product);
    }

    @Override
    public void updateProduct(int id, Product product) {

        List<Product> products = productImpl.getProductById(id);
        if (products == null || products.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + id);
        }

        product.setId(id);
        productImpl.updateProduct(product);
    }

    @Override
    public void deleteProduct(int id) {

        List<Product> products = productImpl.getProductById(id);
        if (products == null || products.isEmpty()) {
            throw new RuntimeException("ID sản phẩm không tồn tại");
        }

        productImpl.deleteProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productImpl.getAllProduct();
    }

    @Override
    public List<Product> getProductById(int id) {
        if (id <= 0) {
            throw new RuntimeException("ID sản phẩm phải lớn hơn 0");
        }
        List<Product> products = productImpl.getProductById(id);
        if (products == null || products.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + id);
        }
        return products;
    }

    @Override
    public List<Product> searchByBrand(String keyword) {
        return productImpl.getProductByBrand(keyword);
    }

    @Override
    public List<Product> searchByPriceRange(double min, double max) {

        if (min > max) {
            throw new RuntimeException("Giá min không được lớn hơn giá max");
        }
        return productImpl.getProductByPrice(BigDecimal.valueOf(min),BigDecimal.valueOf(max));
    }

    @Override
    public List<Product> searchByStock(String productName) {

        Integer stock = productImpl.getProductStockByName(productName);

        if (stock == null) {
            throw new RuntimeException("Không tìm thấy sản phẩm");
        }
        System.out.println("Số lượng tồn kho của " + productName + " = " + stock);
        return List.of();
    }
}
