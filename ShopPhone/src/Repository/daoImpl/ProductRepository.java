package Repository.daoImpl;

import Repository.Entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository {
    public List<Product> getAllProduct();
    public List<Product> getProductById(Integer id);
    public void createProduct(Product product);
    public void updateProduct(Product product);
    public void deleteProduct(Integer id);
    public List<Product> getProductByBrand(String brand);
    public List<Product> getProductByPrice(BigDecimal priceFrom, BigDecimal priceTo);
    public Integer getProductStockByName(String productName);



}
