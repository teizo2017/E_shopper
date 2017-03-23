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
import com.emmaoyoita.eshop.models.dto.ProductCategory;

/**
 *
 * @author CHiBEX
 */
public class ProductCategoryDAO {
    
    public static boolean insert(ProductCategory product)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"product_category\"(\"user_id\", \"parent_id\", \"name\", \"description\", \"created\", \"updated\") "
                                                            + "VALUES( ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");  
            ps.setInt(1, product.getUser().getId());
            ps.setInt(2, product.getParentId());
            ps.setString(3, product.getName());  
            ps.setString(4, product.getDescription()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){
            System.out.println(e); }  
        return status > 0;
    }
    
    public static boolean update(ProductCategory productCat)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"product_category\" SET \"user_id\" = ?,"
                                            + " \"parent_id\" = ?, \"name\" = ?, \"description\" = ?, "
                                            + "\"updated\" = CURRENT_TIMESTAMP WHERE \"id\" = ?");  
            ps.setInt(1, productCat.getUser().getId());
            ps.setInt(2, productCat.getParentId());
            ps.setString(3, productCat.getName());  
            ps.setString(4, productCat.getDescription());  
            ps.setInt(5, productCat.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"product_category\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static ProductCategory fetch(int id)
    {
        ProductCategory productCat = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product_category\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                productCat = new ProductCategory();  
                productCat.setId(rs.getInt("id"));
                productCat.setUser(UserDAO.fetch(rs.getInt("user_id")));
                productCat.setParentId(rs.getInt("parent_id"));  
                productCat.setName(rs.getString("name"));  
                productCat.setDescription(rs.getString("description"));  
                productCat.setCreated(rs.getDate("created"));  
                productCat.setUpdated(rs.getDate("updated"));  
            }  
        }catch(SQLException e){System.out.println(e);}  
        return productCat;
    }
    
    public static List<ProductCategory> fetchAll()
    {
        List<ProductCategory> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product_category\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                ProductCategory productCat = new ProductCategory();  
                productCat.setId(rs.getInt("id"));
                productCat.setUser(UserDAO.fetch(rs.getInt("user_id")));  
                productCat.setParentId(rs.getInt("parent_id"));  
                productCat.setName(rs.getString("name"));  
                productCat.setDescription(rs.getString("description"));   
                productCat.setCreated(rs.getDate("created"));  
                productCat.setUpdated(rs.getDate("updated"));
                
                list.add(productCat);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<ProductCategory> fetchParents()
    {
        List<ProductCategory> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product_category\" WHERE \"parent_id\" = 0");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                ProductCategory productCat = new ProductCategory();  
                productCat.setId(rs.getInt("id"));
                productCat.setUser(UserDAO.fetch(rs.getInt("user_id")));  
                productCat.setParentId(rs.getInt("parent_id"));  
                productCat.setName(rs.getString("name"));  
                productCat.setDescription(rs.getString("description"));   
                productCat.setCreated(rs.getDate("created"));  
                productCat.setUpdated(rs.getDate("updated"));
                productCat.setSubCats(ProductCategoryDAO.fetchSub(rs.getInt("id")));
                
                list.add(productCat);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<ProductCategory> fetchCatsWithProducts()
    {
        List<ProductCategory> list = new ArrayList();
        try{  
            
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT c.\"id\", c.\"name\", c.\"description\", p.\"name\" AS \"p_name\" FROM \"product_category\" c INNER JOIN \"product\" p ON c.\"id\" = p.\"category_id\" GROUP BY c.\"id\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                ProductCategory productCat = new ProductCategory();  
                productCat.setId(rs.getInt("id")); 
                productCat.setName(rs.getString("name"));  
                productCat.setDescription(rs.getString("description")); 
                productCat.setProducts(ProductDAO.fetchByCategory(rs.getInt("id")));
                
                list.add(productCat);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<ProductCategory> fetchSub(int id)
    {
        List<ProductCategory> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product_category\" WHERE \"parent_id\" = ?");   
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                ProductCategory productCat = new ProductCategory();  
                productCat.setId(rs.getInt("id"));
                productCat.setUser(UserDAO.fetch(rs.getInt("user_id")));  
                productCat.setParentId(rs.getInt("parent_id"));  
                productCat.setName(rs.getString("name"));  
                productCat.setDescription(rs.getString("description"));   
                productCat.setCreated(rs.getDate("created"));  
                productCat.setUpdated(rs.getDate("updated"));
                
                list.add(productCat);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
