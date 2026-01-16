package Repository.dao;

import Repository.Entity.Product;
import Utils.ConnectionOpen;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProductRepoImpl implements Repository.daoImpl.ProductRepository {

    @Override
    public List<Product> getAllProduct() {

        List<Product> products = new ArrayList<>();
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionOpen.getConnection();

            callStmt = conn.prepareCall("{call getallproduct() }");
            callStmt.execute();
            ResultSet rs =callStmt.getResultSet();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionOpen.closeConnection(conn, callStmt);
        }
        return products;
    }

    @Override
    public List<Product> getProductById(Integer id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        List<Product> products = new ArrayList<>();
        try{
            conn = ConnectionOpen.getConnection();
            callStmt = conn.prepareCall("{call getproductbyid(?) }");
            callStmt.setInt(1, id);
            callStmt.execute();
            ResultSet rs =callStmt.getResultSet();
            while (rs.next()) {
                Product product1 = new Product();
                product1.setId(rs.getInt("id"));
                product1.setName(rs.getString("name"));
                product1.setBrand(rs.getString("brand"));
                product1.setPrice(rs.getDouble("price"));
                product1.setStock(rs.getInt("stock"));
                products.add(product1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionOpen.closeConnection(conn, callStmt);
        }
        return products;
    }

    @Override
    public void createProduct(Product product) {

        Connection conn = null;
        CallableStatement callStmt = null;

        try {
            conn = ConnectionOpen.getConnection();
            callStmt = conn.prepareCall("call createproduct(?,?,?,?)");

            callStmt.setString(1, product.getName());
            callStmt.setString(2, product.getBrand());
            callStmt.setBigDecimal(3, BigDecimal.valueOf(product.getPrice()));
            callStmt.setInt(4, product.getStock());

            callStmt.execute();

            System.out.println("Insert product thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionOpen.closeConnection(conn, callStmt);
        }
    }

    @Override
    public void updateProduct(Product product) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try{
            conn = ConnectionOpen.getConnection();
            callStmt = conn.prepareCall("call updateproduct(?,?,?,?,?)");
            callStmt.setInt(1, product.getId());
            callStmt.setString(2, product.getName());
            callStmt.setString(3, product.getBrand());
            callStmt.setBigDecimal(4, BigDecimal.valueOf(product.getPrice()));
            callStmt.setInt(5, product.getStock());
            callStmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionOpen.closeConnection(conn, callStmt);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try{
            conn = ConnectionOpen.getConnection();
            callStmt = conn.prepareCall("call deleteproduct(?)");
            callStmt.setInt(1, id);
            callStmt.execute();
            System.out.println("Xoá product id = " + id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionOpen.closeConnection(conn, callStmt);
        }
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        Connection conn = null;
        CallableStatement callStmt = null;
        List<Product> products = new ArrayList<>();
        try{
            conn = ConnectionOpen.getConnection();
            callStmt = conn.prepareCall("SELECT * FROM findproductbyname(?)");
            callStmt.setString(1, brand);
            callStmt.execute();
            ResultSet rs =callStmt.getResultSet();
            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionOpen.closeConnection(conn, callStmt);
        }
        return products;
    }

    @Override
    public List<Product> getProductByPrice(BigDecimal priceFrom, BigDecimal priceTo) {
        Connection conn = null;
        CallableStatement callStmt = null;
        List<Product> products = new ArrayList<>();
        try{
            conn = ConnectionOpen.getConnection();
            callStmt = conn.prepareCall("SELECT * FROM findbyprice(?,?) ");
            callStmt.setBigDecimal(1, priceFrom);
            callStmt.setBigDecimal(2, priceTo);
            callStmt.execute();
            ResultSet rs =callStmt.getResultSet();
            while(rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            ConnectionOpen.closeConnection(conn, callStmt);
        }
        return products;
    }

    @Override
    public Integer getProductStockByName(String productName) {
        Connection conn = null;
        CallableStatement callStmt = null;
        Integer stock = 0;
        try{
            conn = ConnectionOpen.getConnection();
            callStmt = conn.prepareCall(   "{ ? = call get_stock_by_product_name(?) }");
            callStmt.registerOutParameter(1, Types.INTEGER);
            callStmt.setString(2, productName);
            callStmt.execute();
            stock = callStmt.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionOpen.closeConnection(conn, callStmt);
        }
        return stock;
    }
}
