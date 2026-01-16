package Service.serviceImpl;

import Repository.Entity.Customer;
import Repository.daoImpl.CustomerRepository;
import Service.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerImpl;

     public CustomerServiceImpl(CustomerRepository customerImpl) {
        this.customerImpl = customerImpl;
    }

     @Override
    public void addCustomer(Customer customer) {
        customerImpl.createCustomer(customer);
    }

     @Override
    public void updateCustomer(int id, Customer customer) {

        Customer old = customerImpl.getCustomerById(id);
        if (old == null) {
            throw new RuntimeException("Không tìm thấy khách hàng với ID: " + id);
        }

        customer.setId(id);
        customerImpl.updateCustomer(customer);
    }

     @Override
    public void deleteCustomer(int id) {

        Customer old = customerImpl.getCustomerById(id);
        if (old == null) {
            throw new RuntimeException("ID khách hàng không tồn tại");
        }

        customerImpl.deleteCustomer(id);
    }

     @Override
    public List<Customer> getAllCustomers() {
        return customerImpl.getAllCustomers();
    }

     @Override
    public Customer getCustomerById(int id) {
        return customerImpl.getCustomerById(id);
    }
}
