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
import com.emmaoyoita.eshop.models.dto.Customer;
import java.sql.Statement;

/**
 *
 * @author CHiBEX
 */
public class CustomerDAO {
    
    public static int insert(Customer customer)
    {
        int id=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"customer\"(\"full_name\", \"email\", \"password\", \"phone_no\", \"created\", \"updated\") "
                    + "                                        VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);  
            ps.setString(1, customer.getFullName()); 
            ps.setString(2, customer.getEmail());  
            ps.setString(3, customer.getPassword()); 
            ps.setString(4, customer.getPhoneNo());   
            ps.executeUpdate();  
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
               id = rs.getInt(1);
        }catch(SQLException e){ System.out.println(e); }  
        return id;
    }
    
    public static boolean update(Customer customer)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"customer\" SET \"full_name\" = ?, \"email\" = ?, \"password\" = ?, \"phone_no\" = ?, \"updated\" = CURRENT_TIMESTAMP WHERE \"id\" = ?");  
            ps.setString(1, customer.getFullName()); 
            ps.setString(2, customer.getEmail());  
            ps.setString(3, customer.getPassword()); 
            ps.setString(4, customer.getPhoneNo()); 
            ps.setInt(5, customer.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"customer\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static Customer login(Customer customer)
    {
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"customer\" WHERE \"email\" = ? AND \"password\" = ?");  
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getEncryptedPassword());
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                customer = new Customer();  
                customer.setId(rs.getInt("id"));
                customer.setFullName(rs.getString("full_name"));  
                customer.setEmail(rs.getString("email"));  
                customer.setPassword(rs.getString("password"));
                customer.setPhoneNo(rs.getString("phone_no"));   
                customer.setCreated(rs.getDate("created"));  
                customer.setUpdated(rs.getDate("updated"));  
            }  
        }catch(SQLException e){System.out.println(e);} 
        
        return customer;
    }
    
    public static boolean isEmailExists(String email)
    {
        boolean flag = false;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"customer\" WHERE \"email\" = ?");  
            ps.setString(1, email);  
            ResultSet rs = ps.executeQuery();  
            if(rs.next()){  
                flag = true;
            }  
        }catch(SQLException e){System.out.println(e);} 
        
        return flag;
    }
    
    public static Customer fetch(int id)
    {
        Customer customer = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"customer\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                customer = new Customer();  
                customer.setId(rs.getInt("id"));
                customer.setFullName(rs.getString("full_name"));  
                customer.setEmail(rs.getString("email"));  
                customer.setPassword(rs.getString("password"));
                customer.setPhoneNo(rs.getString("phone_no"));   
                customer.setCreated(rs.getDate("created"));  
                customer.setUpdated(rs.getDate("updated"));  
            }  
        }catch(SQLException e){System.out.println(e);} 
        
        return customer;
    }
    
    public static List<Customer> fetchAll()
    {
        List<Customer> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"customer\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Customer customer = new Customer();  
                customer.setId(rs.getInt("id"));
                customer.setFullName(rs.getString("full_name"));  
                customer.setEmail(rs.getString("email"));  
                customer.setPassword(rs.getString("password"));
                customer.setPhoneNo(rs.getString("phone_no"));   
                customer.setCreated(rs.getDate("created"));  
                customer.setUpdated(rs.getDate("updated"));
                
                list.add(customer);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
