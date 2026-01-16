package Repository.Entity;

public class Customer {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Customer() {
    }

    public Customer(String address, String phone, String email, Integer id, String name) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
