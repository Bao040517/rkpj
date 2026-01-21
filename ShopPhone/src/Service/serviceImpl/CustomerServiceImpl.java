package Service.serviceImpl;

import Repository.Entity.Customer;
import Repository.daoImpl.CustomerRepository;
import Service.service.CustomerService;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerImpl;

     public CustomerServiceImpl(CustomerRepository customerImpl) {
        this.customerImpl = customerImpl;
    }
    Scanner sc =  new Scanner(System.in);
     @Override
     public void addCustomer(Customer customer) {

         String name;
         String phone;
         String email;
         String address;
         while (true) {
             System.out.print("Name: ");
             name = sc.nextLine().trim();
             try {
                 Integer.parseInt(name);
                 System.out.println("Tên không được là số!");
             } catch (NumberFormatException e) {
                 if (name.isEmpty()) {
                     System.out.println("Tên không được trống!");
                 } else {
                     break;
                 }
             }
         }
         while (true) {
             System.out.print("Phone: ");
             phone = sc.nextLine().trim();

             try {
                 Long.parseLong(phone);
                 break;
             } catch (NumberFormatException e) {
                 System.out.println("Số điện thoại phải được là số!");
             }
         }

         while (true) {
             System.out.print("Email: ");
             email = sc.nextLine().trim();

             if (email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                 break;
             } else {
                 System.out.println("Email không đúng cú pháp ");
             }
         }
         while (true) {
             System.out.print("Address: ");
             address = sc.nextLine().trim();

             if (address.matches("\\d+")) {
                 System.out.println("Địa chỉ sai");
             } else if (address.isEmpty()) {
                 System.out.println("Địa chỉ trống!");
             } else {
                 break;
             }
         }
         customer.setName(name);
         customer.setPhone(phone);
         customer.setEmail(email);
         customer.setAddress(address);

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
