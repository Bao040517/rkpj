package Service.service;

import Repository.Entity.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    void updateProduct(int id, Product product);

    void deleteProduct(int id);

    List<Product> getAllProducts();

    List<Product> getProductById(int id);

    List<Product> searchByBrand(String keyword);

    List<Product> searchByPriceRange(double min, double max);

    List<Product> searchByStock(String keyword);
}
