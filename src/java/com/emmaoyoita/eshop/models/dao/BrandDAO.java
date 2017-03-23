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
import com.emmaoyoita.eshop.models.dto.Brand;
import com.emmaoyoita.eshop.models.ConnectionDB;

/**
 *
 * @author CHiBEX
 */
public class BrandDAO {
    
    public static boolean insert(Brand brand)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"brand\"(\"user_id\", \"name\", \"description\", \"created\", \"updated\") VALUES( ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");  
            ps.setInt(1, brand.getUser().getId());  
            ps.setString(2, brand.getName());  
            ps.setString(3, brand.getDescription());   
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        return status > 0;
    }
    
    public static boolean update(Brand brand)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"brand\" SET \"user_id\" = ?, \"name\" = ?, \"description\" = ?, \"updated\" = CURRENT_TIMESTAMP WHERE \"id\" = ?");  
            ps.setInt(1, brand.getUser().getId());  
            ps.setString(2, brand.getName());  
            ps.setString(3, brand.getDescription());    
            ps.setInt(4, brand.getId());  
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"brand\" WHERE \"id\" = ?");  
            ps.setInt(1,id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static Brand fetch(int id)
    {
        Brand brand = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"brand\" WHERE \"id\" = ?");  
            ps.setInt(1,id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                brand = new Brand();  
                brand.setId(rs.getInt("id"));
                brand.setTotalProdcuts(ProductDAO.countByBrand(rs.getInt("id")));
                brand.setUser(UserDAO.fetch(rs.getInt("user_id")));  
                brand.setName(rs.getString("name"));  
                brand.setDescription(rs.getString("description"));
                brand.setCreated(rs.getDate("created"));  
                brand.setUpdated(rs.getDate("updated"));  
            }  
        }catch(SQLException e){System.out.println(e);} 
        return brand;
    }
    
    public static List<Brand> fetchAll()
    {
        List<Brand> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"brand\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Brand brand = new Brand();  
                brand.setId(rs.getInt("id"));
                brand.setUser(UserDAO.fetch(rs.getInt("user_id")));  
                brand.setName(rs.getString("name"));  
                brand.setDescription(rs.getString("description"));
                brand.setCreated(rs.getDate("created"));  
                brand.setUpdated(rs.getDate("updated"));
                brand.setTotalProdcuts(ProductDAO.countByBrand(rs.getInt("id")));
                
                list.add(brand);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
