/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.emmaoyoita.eshop.models.ConnectionDB;
import com.emmaoyoita.eshop.models.dto.Invoice;
import java.sql.Statement;
import util.HelperUtil;

/**
 *
 * @author CHiBEX
 */
public class InvoiceDAO {
    
    public static int insert(Invoice invoice)
    {
        int id=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"invoice\"(\"billing_id\", \"customer_id\", \"invoice_no\", \"total_items\", \"total_amount\", "
                    + "                                         \"is_delivered\", \"date\") "
                    + "                                        VALUES(?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);  
            ps.setInt(1, invoice.getBilling().getId());  
            ps.setInt(2, invoice.getCustomerId());
            ps.setString(3, invoice.getInvoiceNo());
            ps.setInt(4, invoice.getTotalItems()); 
            ps.setDouble(5, invoice.getTotalAmount().doubleValue());  
            ps.setBoolean(6, invoice.getIsDelivered()); 
            ps.executeUpdate(); 
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
               id = rs.getInt(1);
        }catch(SQLException e){ System.out.println(e); }  
        return id;
    }
    
    public static boolean update(Invoice invoice)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"invoice\" SET \"billing_id\" = ?, \"customer_id\" = ?, \"invoice_no\" = ?, \"total_items\" = ?, "
                                            + "\"total_amount\" = ?, \"is_delivered\" = ?, \"date\" = CURRENT_TIMESTAMP WHERE \"id\" = ?");  
            ps.setInt(1, invoice.getBilling().getId());  
            ps.setInt(2, invoice.getCustomerId());
            ps.setString(3, invoice.getInvoiceNo());
            ps.setInt(4, invoice.getTotalItems()); 
            ps.setDouble(5, invoice.getTotalAmount().doubleValue());  
            ps.setBoolean(6, invoice.getIsDelivered()); 
            ps.setInt(7, invoice.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"invoice\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static Invoice fetch(int id)
    {
        Invoice invoice = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"invoice\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                invoice = new Invoice();  
                invoice.setId(rs.getInt("id"));
                invoice.setBilling(BillingDetailDAO.fetch(rs.getInt("billing_id")));  
                invoice.setCustomerId(rs.getInt("customer_id"));  
                invoice.setInvoiceNo(rs.getString("invoice_no"));  
                invoice.setTotalItems(rs.getInt("total_items"));
                invoice.setTotalAmount(rs.getBigDecimal("total_amount")); 
                invoice.setIsDelivered(rs.getBoolean("is_delivered"));    
                invoice.setDate(rs.getDate("date"));  
            }  
        }catch(SQLException e){System.out.println(e);}  
        return invoice;
    }
    
    public static List<Invoice> fetchAll()
    {
        List<Invoice> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"invoice\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Invoice invoice = new Invoice();  
                invoice.setId(rs.getInt("id"));
                invoice.setBilling(BillingDetailDAO.fetch(rs.getInt("billing_id")));  
                invoice.setCustomerId(rs.getInt("customer_id"));  
                invoice.setInvoiceNo(rs.getString("invoice_no"));  
                invoice.setTotalItems(rs.getInt("total_items"));
                invoice.setTotalAmount(rs.getBigDecimal("total_amount")); 
                invoice.setIsDelivered(rs.getBoolean("is_delivered"));    
                invoice.setDate(rs.getDate("date"));
                invoice.setInvoiceDetail(InvoiceDetailDAO.fetchByInvoice(rs.getInt("id")));
                
                list.add(invoice);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<Invoice> fetchByCustomer(int customerId)
    {
        List<Invoice> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"invoice\" WHERE \"customer_id\" = ?");   
            ps.setInt(1, customerId); 
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Invoice invoice = new Invoice();  
                invoice.setId(rs.getInt("id"));
                invoice.setBilling(BillingDetailDAO.fetch(rs.getInt("billing_id")));  
                invoice.setCustomerId(rs.getInt("customer_id"));  
                invoice.setInvoiceNo(rs.getString("invoice_no"));  
                invoice.setTotalItems(rs.getInt("total_items"));
                invoice.setTotalAmount(rs.getBigDecimal("total_amount")); 
                invoice.setIsDelivered(rs.getBoolean("is_delivered"));   
                invoice.setDate(rs.getDate("date"));
                invoice.setInvoiceDetail(InvoiceDetailDAO.fetchByInvoice(rs.getInt("id")));
                
                list.add(invoice);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<Invoice> fetchByState(int stateId)
    {
        List<Invoice> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"invoice\" WHERE \"state_id\" = ?");   
            ps.setInt(1, stateId); 
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Invoice invoice = new Invoice();  
                invoice.setId(rs.getInt("id"));
                invoice.setBilling(BillingDetailDAO.fetch(rs.getInt("billing_id")));  
                invoice.setCustomerId(rs.getInt("customer_id"));  
                invoice.setInvoiceNo(rs.getString("invoice_no"));  
                invoice.setTotalItems(rs.getInt("total_items"));
                invoice.setTotalAmount(rs.getBigDecimal("total_amount")); 
                invoice.setIsDelivered(rs.getBoolean("is_delivered"));    
                invoice.setDate(rs.getDate("date"));
                
                list.add(invoice);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static int getInvoiceNo()
    {
            int id = 0;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT \"invoice_no\" FROM \"invoice\" ORDER BY \"date\" DESC LIMIT 0, 1");   
            ResultSet rs = ps.executeQuery();  
            if(rs.next()){  
                id = HelperUtil.parseInt(rs.getString("invoice_no"));
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return ++id; 
    }
}
