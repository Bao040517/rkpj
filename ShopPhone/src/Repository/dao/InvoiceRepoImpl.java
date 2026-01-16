package Repository.dao;

import Repository.Entity.Invoice;
import Repository.Entity.InvoiceDetails;
import Repository.Entity.Product;
import Utils.ConnectionOpen;
import dtos.Statistics;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRepoImpl implements Repository.daoImpl.InvoiceRepository {
    @Override
    public Integer createInvoice(Invoice invoice) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall("{ ? = call create_invoice(?) }");

            stmt.registerOutParameter(1, Types.INTEGER);

            stmt.setInt(2, invoice.getCustomerId());

            stmt.execute();

            return stmt.getInt(1);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            ConnectionOpen.closeConnection(conn, stmt);
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Invoice> invoices = new ArrayList<>();
        try{
            conn =  ConnectionOpen.getConnection();
            stmt = conn.prepareCall("{CALL get_all_invoice()}");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                Invoice invoice = new Invoice();
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCreatedAt(rs.getTimestamp("created_at"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoices.add(invoice);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionOpen.closeConnection(conn,stmt);
        }
        return invoices;
    }

    @Override
    public void createInvoiceDetails(InvoiceDetails invoiceDetails) {
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            conn =  ConnectionOpen.getConnection();
            stmt = conn.prepareCall("CALL create_invoice_detail(?,?,?) ");
            stmt.setInt(1, invoiceDetails.getInvoiceId());
            stmt.setInt(2, invoiceDetails.getProductId());
            stmt.setInt(3, invoiceDetails.getQuantity());
            stmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionOpen.closeConnection(conn,stmt);
        }
    }


    @Override
    public Invoice getInvoiceById(Integer invoiceId) {
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            conn =  ConnectionOpen.getConnection();
            stmt = conn.prepareCall("{CALL get_invoice_by_id(?)}");
            stmt.setInt(1, invoiceId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                Invoice invoice = new Invoice();
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCreatedAt(rs.getTimestamp("created_at"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                return invoice;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionOpen.closeConnection(conn,stmt);
        }
        return null;
    }

    @Override
    public List<Invoice> getInvoiceByCustomerName(String customerName) {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Invoice> invoices = new ArrayList<>();
        try{
            conn =  ConnectionOpen.getConnection();
            stmt = conn.prepareCall("{CALL get_invoice_by_customer_name(?) }");
            stmt.setString(1, customerName);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                Invoice invoice = new Invoice();
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCreatedAt(rs.getTimestamp("created_at"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoices.add(invoice);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionOpen.closeConnection(conn,stmt);
        }
        return invoices;
    }

    @Override
    public List<Invoice> getInvoiceByDateTime(LocalDate date) {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Invoice> invoices = new ArrayList<>();
        try{
            conn =  ConnectionOpen.getConnection();
            stmt = conn.prepareCall("{CALL get_invoice_by_datetime(?) }");
            stmt.setTimestamp(1, Timestamp.valueOf(date.atStartOfDay()));
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCreatedAt(rs.getTimestamp("created_at"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoices.add(invoice);
                return invoices;
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        finally{
            ConnectionOpen.closeConnection(conn,stmt);
        }
        return null;
    }

    @Override
    public List<Statistics> getInvoiceStatisticDay(Integer month, Integer year) {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Statistics> statisticsList = new ArrayList<>();
        try {
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall("{CALL get_total_revenue_day(?, ?)}");
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Statistics stat = new Statistics();
                stat.setDay(rs.getInt("day"));
                stat.setMonth(month);
                stat.setYear(year);
                stat.setRevenue(rs.getBigDecimal("total_revenue"));
                statisticsList.add(stat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ConnectionOpen.closeConnection(conn,stmt);
        }

        return statisticsList;
    }

    @Override
    public List<Statistics> getInvoiceStatisticMonth(Integer year) {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Statistics> statisticsList = new ArrayList<>();
        try{
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall("{CALL get_total_revenue_month(?)}");
            stmt.setInt(1, year);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Statistics stat = new Statistics();
                stat.setMonth(rs.getInt("month"));
                stat.setYear(year);
                stat.setRevenue(rs.getBigDecimal("total_revenue"));
                statisticsList.add(stat);
            }
            return statisticsList;

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionOpen.closeConnection(conn,stmt);
        }
        return null;
    }

    @Override
    public List<Statistics> getInvoiceStatisticYear() {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Statistics> statisticsList = new ArrayList<>();
        try{
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall("CALL get_total_revenue_year()");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Statistics stat = new Statistics();
                stat.setYear(rs.getInt("year"));
                stat.setRevenue(rs.getBigDecimal("total_revenue"));
                statisticsList.add(stat);
            }
            return statisticsList;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionOpen.closeConnection(conn,stmt);
        }
        return null;
    }


}
