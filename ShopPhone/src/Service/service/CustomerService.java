package Service.service;

import Repository.Entity.Customer;
import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer);

    void updateCustomer(int id, Customer customer);

    void deleteCustomer(int id);

    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);
}
