package Repository.Entity;

import java.util.Date;

public class Invoice {
    private Integer id;
    private Integer customerId;
    private Date createdAt;
    private Double totalAmount;

    public Invoice() {
    }

    public Invoice(Date createdAt, Integer customerId, Integer id, Double totalAmount) {
        this.createdAt = createdAt;
        this.customerId = customerId;
        this.id = id;
        this.totalAmount = totalAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
