package Repository.dao;

import Repository.Entity.Customer;
import Utils.ConnectionOpen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepoImpl implements Repository.daoImpl.CustomerRepository {

    @Override
    public void createCustomer(Customer customer) {
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall("CALL create_customer(?,?,?,?)");
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAddress());
            stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
                ConnectionOpen.closeConnection(conn,stmt);
        }
        return;
    }

    @Override
    public void updateCustomer(Customer customer) {
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall("CALL update_customer(?,?,?,?,?)");
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getAddress());
            stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ConnectionOpen.closeConnection(conn,stmt);
        }
        return;
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall(" CALL delete_customer(?) ");
            stmt.setInt(1, customerId);
            stmt.execute();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            ConnectionOpen.closeConnection(conn,stmt);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Customer> customers = new ArrayList<>();
        try{
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall(" SELECT * FROM get_all_customer() ");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionOpen.closeConnection(conn,stmt);
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall("{ CALL get_customer_by_id(?) } ");
            stmt.setInt(1, customerId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("customer_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                return customer;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            ConnectionOpen.closeConnection(conn,stmt);
        }
        return null;
    }


}
