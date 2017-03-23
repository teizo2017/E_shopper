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
import com.emmaoyoita.eshop.models.dto.InvoiceDetail;

/**
 *
 * @author CHiBEX
 */
public class InvoiceDetailDAO {
    
    public static boolean insert(InvoiceDetail detail)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"invoice_detail\"(\"invoice_id\", \"product_id\", \"quantity\", \"total_amount\") "
                    + "                                        VALUES(?, ?, ?, ?)");  
            ps.setInt(1, detail.getInvoice().getId());  
            ps.setInt(2, detail.getProduct().getId());
            ps.setInt(3, detail.getQuantity());
            ps.setDouble(4, detail.getTotalAmount().doubleValue());
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }
        
        return status > 0;
    }
    
    public static boolean update(InvoiceDetail detail)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"invoice_detail\" SET \"invoice_id\" = ?, \"product_id\" = ?, \"quantity\" = ?, "
                                                                + "\"total_amount\" = ? WHERE \"id\" = ?");  
            ps.setInt(1, detail.getInvoice().getId());  
            ps.setInt(2, detail.getProduct().getId());
            ps.setInt(3, detail.getQuantity());
            ps.setDouble(4, detail.getTotalAmount().doubleValue()); 
            ps.setInt(5, detail.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"invoice_detail\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static boolean deleteByInvoice(int invoiceId)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"invoice_detail\" WHERE \"invoice_id\" = ?");  
            ps.setInt(1, invoiceId);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static InvoiceDetail fetch(int id)
    {
        InvoiceDetail detail = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"invoice_detail\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                detail = new InvoiceDetail();  
                detail.setId(rs.getInt("id"));
                detail.setInvoice(InvoiceDAO.fetch(rs.getInt("invoice_id")));  
                detail.setProduct(ProductDAO.fetch(rs.getInt("product_id")));  
                detail.setQuantity(rs.getInt("quantity"));  
                detail.setTotalAmount(rs.getBigDecimal("total_amount")); 
            }  
        }catch(SQLException e){System.out.println(e);}  
        return detail;
    }
    
    public static List<InvoiceDetail> fetchAll()
    {
        List<InvoiceDetail> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"invoice_detail\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                InvoiceDetail detail = new InvoiceDetail();  
                detail.setId(rs.getInt("id"));
                detail.setInvoice(InvoiceDAO.fetch(rs.getInt("invoice_id")));  
                detail.setProduct(ProductDAO.fetch(rs.getInt("product_id")));  
                detail.setQuantity(rs.getInt("quantity"));  
                detail.setTotalAmount(rs.getBigDecimal("total_amount")); 
                
                list.add(detail);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<InvoiceDetail> fetchByInvoice(int invoiceId)
    {
        List<InvoiceDetail> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"invoice_detail\" WHERE \"invoice_id\" = ?");   
            ps.setInt(1, invoiceId); 
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                InvoiceDetail detail = new InvoiceDetail();  
                detail.setId(rs.getInt("id"));
                detail.setInvoice(InvoiceDAO.fetch(rs.getInt("invoice_id")));  
                detail.setProduct(ProductDAO.fetch(rs.getInt("product_id")));  
                detail.setQuantity(rs.getInt("quantity"));  
                detail.setTotalAmount(rs.getBigDecimal("total_amount"));
                
                list.add(detail);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<InvoiceDetail> fetchByProduct(int productId)
    {
        List<InvoiceDetail> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"invoice_detail\" WHERE \"product_id\" = ?");   
            ps.setInt(1, productId); 
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                InvoiceDetail detail = new InvoiceDetail();  
                detail.setId(rs.getInt("id"));
                detail.setInvoice(InvoiceDAO.fetch(rs.getInt("invoice_id")));  
                detail.setProduct(ProductDAO.fetch(rs.getInt("product_id")));  
                detail.setQuantity(rs.getInt("quantity"));  
                detail.setTotalAmount(rs.getBigDecimal("total_amount"));
                
                list.add(detail);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
