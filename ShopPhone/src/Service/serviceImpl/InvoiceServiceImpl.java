package Service.serviceImpl;

import Repository.Entity.Invoice;
import Repository.Entity.InvoiceDetails;
import Repository.daoImpl.InvoiceRepository;
import Service.service.InvoiceService;
import dtos.Statistics;

import java.time.LocalDate;
import java.util.List;

public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceImpl;

    public InvoiceServiceImpl(InvoiceRepository invoiceImpl) {
        this.invoiceImpl = invoiceImpl;
    }

    @Override
    public Integer createInvoice(Invoice invoice) {
        return invoiceImpl.createInvoice(invoice);
    }

    @Override
    public void createInvoiceDetails(InvoiceDetails invoiceDetails) {

        invoiceImpl.createInvoiceDetails(invoiceDetails);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceImpl.getAllInvoices();
    }

    @Override
    public Invoice getInvoiceById(int id) {
        return invoiceImpl.getInvoiceById(id);
    }

    @Override
    public List<Invoice> searchByCustomerName(String customerName) {
        return invoiceImpl.getInvoiceByCustomerName(customerName);
    }

    @Override
    public List<Invoice> searchByDate(LocalDate date) {
        return invoiceImpl.getInvoiceByDateTime(date);
    }

    @Override
    public List<Statistics> statisticByDay(int month, int year) {
        return invoiceImpl.getInvoiceStatisticDay(month, year);
    }

    @Override
    public List<Statistics> statisticByMonth(int year) {
        return invoiceImpl.getInvoiceStatisticMonth(year);
    }

    @Override
    public List<Statistics> statisticByYear() {
        return invoiceImpl.getInvoiceStatisticYear();
    }
}
