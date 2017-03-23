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
import com.emmaoyoita.eshop.models.dto.ShoppingCart;

/**
 *
 * @author CHiBEX
 */
public class ShoppingCartDAO {
    
    public static boolean insert(ShoppingCart cart)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"shopping_cart\"(\"product_id\", \"session_id\", \"quantity\", \"unit_price\", \"date\") "
                    + "                                        VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP)");  
            ps.setInt(1, cart.getProduct().getId());  
            ps.setString(2, cart.getSessionId());
            ps.setInt(3, cart.getQuantity()); 
            ps.setDouble(4, cart.getProduct().getUnitPrice().doubleValue());  
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        return status > 0;
    }
    
    public static boolean update(ShoppingCart cart)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"shopping_cart\" SET \"product_id\" = ?, \"session_id\" = ?, \"quantity\" = ?, \"unit_price\" = ?, "
                                            + "\"date\" = CURRENT_TIMESTAMP WHERE \"id\" = ?");  
            ps.setInt(1, cart.getProduct().getId());  
            ps.setString(2, cart.getSessionId());
            ps.setInt(3, cart.getQuantity()); 
            ps.setDouble(4, cart.getUnitPrice().doubleValue()); 
            ps.setInt(5, cart.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean sub(int productId, String sessionId)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"shopping_cart\" SET \"quantity\" = \"quantity\" - 1 WHERE \"product_id\" = ? AND \"session_id\" = ?");  
            ps.setInt(1, productId);  
            ps.setString(2, sessionId); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean add(int productId, String sessionId)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"shopping_cart\" SET \"quantity\" = \"quantity\" + 1 WHERE \"product_id\" = ? AND \"session_id\" = ?");  
            ps.setInt(1, productId);  
            ps.setString(2, sessionId); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"shopping_cart\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static boolean delete(int productId, String sessionId)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"shopping_cart\" WHERE \"product_id\" = ? AND \"session_id\" = ?");  
            ps.setInt(1, productId);  
            ps.setString(2, sessionId);
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static boolean clear(String sessionId)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"shopping_cart\" WHERE \"session_id\" = ?");  
            ps.setString(1, sessionId);
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static ShoppingCart fetch(int id)
    {
        ShoppingCart cart = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"shopping_cart\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                cart = new ShoppingCart();  
                cart.setId(rs.getInt("id"));
                cart.setProduct(ProductDAO.fetch(rs.getInt("product_id")));  
                cart.setSessionId(rs.getString("session_id"));  
                cart.setQuantity(rs.getInt("quantity"));  
                cart.setUnitPrice(rs.getBigDecimal("unit_price"));   
                cart.setDate(rs.getDate("date"));  
            }  
        }catch(SQLException e){System.out.println(e);}  
        return cart;
    }
    
    public static ShoppingCart fetchByProductAndSession(int productId, String sessionId)
    {
        ShoppingCart cart = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"shopping_cart\" WHERE \"product_id\" = ? AND \"session_id\" = ?");  
            ps.setInt(1, productId);  
            ps.setString(2, sessionId);
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                cart = new ShoppingCart();  
                cart.setId(rs.getInt("id"));
                cart.setProduct(ProductDAO.fetch(rs.getInt("product_id")));  
                cart.setSessionId(rs.getString("session_id"));  
                cart.setQuantity(rs.getInt("quantity"));  
                cart.setUnitPrice(rs.getBigDecimal("unit_price"));   
                cart.setDate(rs.getDate("date"));  
            }  
        }catch(SQLException e){System.out.println(e);}  
        return cart;
    }
    
    public static List<ShoppingCart> fetchAll()
    {
        List<ShoppingCart> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"shopping_cart\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                ShoppingCart cart = new ShoppingCart();  
                cart.setId(rs.getInt("id"));
                cart.setProduct(ProductDAO.fetch(rs.getInt("product_id")));  
                cart.setSessionId(rs.getString("session_id"));  
                cart.setQuantity(rs.getInt("quantity"));  
                cart.setUnitPrice(rs.getBigDecimal("unit_price"));   
                cart.setDate(rs.getDate("date"));
                
                list.add(cart);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<ShoppingCart> fetchBySession(String sessionId)
    {
        List<ShoppingCart> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"shopping_cart\" WHERE \"session_id\" = ?");    
            ps.setString(1, sessionId);
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                ShoppingCart cart = new ShoppingCart();  
                cart.setId(rs.getInt("id"));
                cart.setProduct(ProductDAO.fetch(rs.getInt("product_id")));  
                cart.setSessionId(rs.getString("session_id"));  
                cart.setQuantity(rs.getInt("quantity"));  
                cart.setUnitPrice(rs.getBigDecimal("unit_price"));   
                cart.setDate(rs.getDate("date"));
                
                list.add(cart);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<ShoppingCart> fetchByProduct(int productId)
    {
        List<ShoppingCart> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"shopping_cart\" WHERE \"product_id\" = ?");   
            ps.setInt(1, productId); 
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                ShoppingCart cart = new ShoppingCart();  
                cart.setId(rs.getInt("id"));
                cart.setProduct(ProductDAO.fetch(rs.getInt("product_id")));  
                cart.setSessionId(rs.getString("session_id"));  
                cart.setQuantity(rs.getInt("quantity"));  
                cart.setUnitPrice(rs.getBigDecimal("unit_price"));   
                cart.setDate(rs.getDate("date"));
                
                list.add(cart);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
