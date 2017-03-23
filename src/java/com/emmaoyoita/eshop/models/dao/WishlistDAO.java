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
import com.emmaoyoita.eshop.models.dto.Wishlist;

/**
 *
 * @author CHiBEX
 */
public class WishlistDAO {
    
    public static boolean insert(Wishlist wishlist)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"wishlist\"(\"customer_id\", \"product_id\", \"date\") "
                    + "                                        VALUES(?, ?, CURRENT_TIMESTAMP)");  
            ps.setInt(1, wishlist.getCustomer().getId());  
            ps.setInt(2, wishlist.getProduct().getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        return status > 0;
    }
    
    public static boolean update(Wishlist wishlist)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"wishlist\" SET \"customer_id\" = ?, \"product_id\" = ?, \"date\" = CURRENT_TIMESTAMP WHERE \"id\" = ?");  
            ps.setInt(1, wishlist.getCustomer().getId());  
            ps.setInt(2, wishlist.getProduct().getId()); 
            ps.setInt(7, wishlist.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"wishlist\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static boolean deleteByProductAndCustomer(int pId, int cId)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"wishlist\" WHERE \"product_id\" = ? AND \"customer_id\" = ?");  
            ps.setInt(1, pId); 
            ps.setInt(2, cId); 
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static Wishlist fetch(int id)
    {
        Wishlist wishlist = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"wishlist\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                wishlist = new Wishlist();  
                wishlist.setId(rs.getInt("id"));
                wishlist.setCustomer(CustomerDAO.fetch(rs.getInt("customer_id")));  
                wishlist.setProduct(ProductDAO.fetch(rs.getInt("product_id")));     
                wishlist.setDate(rs.getDate("date"));  
            }  
        }catch(SQLException e){System.out.println(e);}  
        return wishlist;
    }
    
    public static Wishlist fetchByProductAndCustomer(int pId, int cId)
    {
        Wishlist wishlist = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"wishlist\" WHERE \"product_id\" = ? AND \"customer_id\" = ?");  
            ps.setInt(1, pId);
            ps.setInt(2, cId);
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                wishlist = new Wishlist();  
                wishlist.setId(rs.getInt("id"));
                wishlist.setCustomer(CustomerDAO.fetch(rs.getInt("customer_id")));  
                wishlist.setProduct(ProductDAO.fetch(rs.getInt("product_id")));     
                wishlist.setDate(rs.getDate("date"));  
            }  
        }catch(SQLException e){System.out.println(e);}  
        return wishlist;
    }
    
    public static List<Wishlist> fetchAll()
    {
        List<Wishlist> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"wishlist\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Wishlist wishlist = new Wishlist();  
                wishlist.setId(rs.getInt("id"));
                wishlist.setCustomer(CustomerDAO.fetch(rs.getInt("customer_id")));  
                wishlist.setProduct(ProductDAO.fetch(rs.getInt("product_id")));     
                wishlist.setDate(rs.getDate("date"));
                
                list.add(wishlist);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<Wishlist> fetchByCustomer(int customerId)
    {
        List<Wishlist> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"wishlist\" WHERE \"customer_id\" = ?");   
            ps.setInt(1, customerId); 
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Wishlist wishlist = new Wishlist();  
                wishlist.setId(rs.getInt("id"));
                wishlist.setCustomer(CustomerDAO.fetch(rs.getInt("customer_id")));  
                wishlist.setProduct(ProductDAO.fetch(rs.getInt("product_id")));     
                wishlist.setDate(rs.getDate("date"));
                
                list.add(wishlist);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<Wishlist> fetchByProduct(int productId)
    {
        List<Wishlist> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"wishlist\" WHERE \"product_id\" = ?");   
            ps.setInt(1, productId); 
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Wishlist wishlist = new Wishlist();  
                wishlist.setId(rs.getInt("id"));
                wishlist.setCustomer(CustomerDAO.fetch(rs.getInt("customer_id")));  
                wishlist.setProduct(ProductDAO.fetch(rs.getInt("product_id")));     
                wishlist.setDate(rs.getDate("date"));
                
                list.add(wishlist);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
