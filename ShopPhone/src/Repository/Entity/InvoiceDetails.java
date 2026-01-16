package Repository.Entity;

public class InvoiceDetails {
    private Integer id;
    private Integer invoiceId;
    private Integer productId;
    private Integer quantity;
    private Double unitPrice;

    public InvoiceDetails() {
    }

    public InvoiceDetails(Integer id, Integer invoiceId, Integer quantity, Integer productId, Double unitPrice) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.quantity = quantity;
        this.productId = productId;
        this.unitPrice = unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
