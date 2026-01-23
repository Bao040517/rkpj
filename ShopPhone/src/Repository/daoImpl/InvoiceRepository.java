package Repository.daoImpl;

import Repository.Entity.Invoice;
import Repository.Entity.InvoiceDetails;
import dtos.Statistics;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository {
    Integer createInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Integer invoiceId);
    void createInvoiceDetails(InvoiceDetails invoiceDetails);
    List<Invoice> getInvoiceByCustomerName(String customerName);
    List<Invoice> getInvoiceByDateTime(LocalDate date);
    List<Statistics> getInvoiceStatisticDay(Integer month, Integer year);
    List<Statistics> getInvoiceStatisticMonth(Integer year);
    List<Statistics> getInvoiceStatisticYear();
    void deleteInvoice(Integer invoiceId);




}
