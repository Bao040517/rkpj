package Repository.daoImpl;

import Repository.Entity.Customer;

import java.util.List;


public interface CustomerRepository {
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Integer customerId);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer customerId);

}
