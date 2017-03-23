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
import com.emmaoyoita.eshop.models.dto.User;

/**
 *
 * @author CHiBEX
 */
public class UserDAO {
    
    public static boolean insert(User user)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"user\"(\"user_id\", \"full_name\", \"user_name\", \"password\", \"created\", \"updated\") "
                    + "                                        VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");  
            ps.setInt(1, user.getUserId());  
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getUserName()); 
            ps.setString(4, user.getEncryptedPassword());   
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        return status > 0;
    }
    
    public static boolean update(User user)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"user\" SET \"user_id\" = ?, \"full_name\" = ?, \"updated\" = CURRENT_TIMESTAMP WHERE \"id\" = ?");  
            ps.setInt(1, user.getUserId());  
            ps.setString(2, user.getFullName());
            ps.setInt(3, user.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean changePassword(User user)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"user\" SET \"user_id\" = ?, \"password\" = ?, \"updated\" = CURRENT_TIMESTAMP WHERE \"id\" = ?");  
            ps.setInt(1, user.getUserId());  
            ps.setString(2, user.getEncryptedPassword());
            ps.setInt(3, user.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static User login(String userName, String password)
    {
        System.out.println("chibexme");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"user\" WHERE \"user_name\" = ? AND \"password\" = ?");  
            ps.setString(1, user.getUserName()); 
            ps.setString(2, user.getEncryptedPassword());  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){   
                user.setId(rs.getInt("id"));
                user.setUserId(rs.getInt("user_id"));  
                user.setFullName(rs.getString("full_name"));  
                //user.setUserName(rs.getString("user_name"));  
                //user.setPassword(rs.getString("password")); 
                user.setCreated(rs.getDate("created"));    
                user.setUpdated(rs.getDate("updated")); 
            }  
        }catch(SQLException e){
            System.out.println("Exception thrown");
            System.out.println(e);}  
        return user;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"user\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static User fetch(int id)
    {
        User user = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"user\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                user = new User();  
                user.setId(rs.getInt("id"));
                user.setUserId(rs.getInt("user_id"));  
                user.setFullName(rs.getString("full_name"));  
                user.setUserName(rs.getString("user_name"));  
                user.setPassword(rs.getString("password")); 
                user.setCreated(rs.getDate("updated"));    
                user.setUpdated(rs.getDate("updated"));  
            }  
        }catch(SQLException e){System.out.println(e);}  
        return user;
    }
    
    public static List<User> fetchAll()
    {
        List<User> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"user\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                User user = new User();  
                user.setId(rs.getInt("id"));
                user.setUserId(rs.getInt("user_id"));  
                user.setFullName(rs.getString("full_name"));  
                user.setUserName(rs.getString("user_name"));  
                user.setPassword(rs.getString("password")); 
                user.setCreated(rs.getDate("updated"));    
                user.setUpdated(rs.getDate("updated"));
                
                list.add(user);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
