package Service.service;

import Repository.Entity.Invoice;
import Repository.Entity.InvoiceDetails;
import dtos.Statistics;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    Integer  createInvoice(Invoice invoice);
    void createInvoiceDetails(InvoiceDetails invoiceDetails);

    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(int id);

    void deleteInvoice(int id);


    List<Invoice> searchByCustomerName(String customerName);
    List<Invoice> searchByDate(LocalDate date);

    List<Statistics> statisticByDay(int month, int year);
    List<Statistics> statisticByMonth(int year);
    List<Statistics> statisticByYear();
}
