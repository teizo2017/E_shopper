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
import com.emmaoyoita.eshop.models.dto.Product;

/**
 *
 * @author CHiBEX
 */
public class ProductDAO {
    
    public static boolean insert(Product product)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"product\"(\"category_id\", \"user_id\", \"brand_id\", \"stock_no\", \"name\", "
                    + "                                         \"description\", \"image_url\", \"unit_price\", \"is_recommended\", \"is_featured\", \"created\", \"updated\") "
                    + "                                        VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");  
            ps.setInt(1, product.getCategory().getId());  
            ps.setInt(2, product.getUser().getId());
            ps.setInt(3, product.getBrand().getId());
            ps.setString(4, product.getStockNo()); 
            ps.setString(5, product.getName());  
            ps.setString(6, product.getDescription()); 
            ps.setString(7, product.getImageUrl());  
            ps.setDouble(8, product.getUnitPrice().doubleValue());  
            ps.setBoolean(9, product.getIsRecommended());
            ps.setBoolean(10, product.getIsFeatured()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        return status > 0;
    }
    
    public static boolean update(Product product)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"product\" SET \"category_id\" = ?, \"user_id\" = ?, \"brand_id\" = ?, \"stock_no\" = ?,"
                                            + " \"name\" = ?, \"description\" = ?, \"image_url\" = ?, \"unit_price\" = ?, \"is_recommended\" = ?, \"is_featured\" = ?, "
                                            + "\"updated\" = CURRENT_TIMESTAMP WHERE \"id\" = ?");  
            ps.setInt(1, product.getCategory().getId());  
            ps.setInt(2, product.getUser().getId());
            ps.setInt(3, product.getBrand().getId());
            ps.setString(4, product.getStockNo()); 
            ps.setString(5, product.getName());  
            ps.setString(6, product.getDescription()); 
            ps.setString(7, product.getImageUrl());  
            ps.setDouble(8, product.getUnitPrice().doubleValue());  
            ps.setBoolean(9, product.getIsRecommended());
            ps.setBoolean(10, product.getIsFeatured()); 
            ps.setInt(11, product.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"product\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static boolean deleteByBrand(int brandId)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"product\" WHERE \"brand_id\" = ?");  
            ps.setInt(1, brandId);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static boolean deleteByCategory(int catId)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"product\" WHERE \"category_id\" = ?");  
            ps.setInt(1, catId);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static Product fetch(int id)
    {
        Product product = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                product = new Product();  
                product.setId(rs.getInt("id"));
                product.setUser(UserDAO.fetch(rs.getInt("user_id")));  
                product.setBrand(BrandDAO.fetch(rs.getInt("brand_id")));
                product.setCategory(ProductCategoryDAO.fetch(rs.getInt("category_id")));
                product.setStockNo(rs.getString("stock_no"));  
                product.setName(rs.getString("name"));  
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("image_url")); 
                product.setUnitPrice(rs.getBigDecimal("unit_price"));  
                product.setIsRecommended(rs.getBoolean("is_recommended"));  
                product.setIsFeatured(rs.getBoolean("is_featured"));   
                product.setCreated(rs.getDate("created"));  
                product.setUpdated(rs.getDate("updated"));  
            }  
        }catch(SQLException e){System.out.println(e);}  
        return product;
    }
    
    public static List<Product> fetchAll()
    {
        List<Product> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Product product = new Product();  
                product.setId(rs.getInt("id"));
                product.setUser(UserDAO.fetch(rs.getInt("user_id"))); 
                product.setBrand(BrandDAO.fetch(rs.getInt("brand_id")));
                product.setCategory(ProductCategoryDAO.fetch(rs.getInt("category_id"))); 
                product.setStockNo(rs.getString("stock_no"));  
                product.setName(rs.getString("name"));  
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("image_url")); 
                product.setUnitPrice(rs.getBigDecimal("unit_price"));  
                product.setIsRecommended(rs.getBoolean("is_recommended"));  
                product.setIsFeatured(rs.getBoolean("is_featured"));   
                product.setCreated(rs.getDate("created"));  
                product.setUpdated(rs.getDate("updated"));
                
                list.add(product);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<Product> fetchTop(String col, int typeId, int total)
    {
        List<Product> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product\" OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY");
            ps.setInt(1, total);
            if(!col.equals("")){
                ps = conn.prepareStatement("SELECT * FROM \"product\" WHERE \""+col+"\" = ? OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY");
                ps.setInt(1, typeId);
                ps.setInt(2, total);
            }
            
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Product product = new Product();  
                product.setId(rs.getInt("id"));
                product.setUser(UserDAO.fetch(rs.getInt("user_id"))); 
                product.setBrand(BrandDAO.fetch(rs.getInt("brand_id")));
                product.setCategory(ProductCategoryDAO.fetch(rs.getInt("category_id"))); 
                product.setStockNo(rs.getString("stock_no"));  
                product.setName(rs.getString("name"));  
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("image_url")); 
                product.setUnitPrice(rs.getBigDecimal("unit_price"));  
                product.setIsRecommended(rs.getBoolean("is_recommended"));  
                product.setIsFeatured(rs.getBoolean("is_featured"));   
                product.setCreated(rs.getDate("created"));  
                product.setUpdated(rs.getDate("updated"));
                
                list.add(product);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<Product> fetchByBrand(int brandId)
    {
        List<Product> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product\" WHERE \"brand_id\" = ?"); 
            ps.setInt(1, brandId);
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Product product = new Product();  
                product.setId(rs.getInt("id"));
                product.setUser(UserDAO.fetch(rs.getInt("user_id"))); 
                product.setBrand(BrandDAO.fetch(rs.getInt("brand_id")));
                product.setCategory(ProductCategoryDAO.fetch(rs.getInt("category_id"))); 
                product.setStockNo(rs.getString("stock_no"));  
                product.setName(rs.getString("name"));  
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("image_url")); 
                product.setUnitPrice(rs.getBigDecimal("unit_price"));  
                product.setIsRecommended(rs.getBoolean("is_recommended"));  
                product.setIsFeatured(rs.getBoolean("is_featured"));   
                product.setCreated(rs.getDate("created"));  
                product.setUpdated(rs.getDate("updated"));
                
                list.add(product);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<Product> fetchByCategory(int categoryId)
    {
        List<Product> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product\" WHERE \"category_id\" = ?"); 
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Product product = new Product();  
                product.setId(rs.getInt("id"));
                product.setUser(UserDAO.fetch(rs.getInt("user_id"))); 
                product.setBrand(BrandDAO.fetch(rs.getInt("brand_id")));
                product.setCategory(ProductCategoryDAO.fetch(rs.getInt("category_id"))); 
                product.setStockNo(rs.getString("stock_no"));  
                product.setName(rs.getString("name"));  
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("image_url")); 
                product.setUnitPrice(rs.getBigDecimal("unit_price"));  
                product.setIsRecommended(rs.getBoolean("is_recommended"));  
                product.setIsFeatured(rs.getBoolean("is_featured"));   
                product.setCreated(rs.getDate("created"));  
                product.setUpdated(rs.getDate("updated"));
                
                list.add(product);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static int countByBrand(int brandId)
    {
        int total = 0;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(\"id\") AS \"total\" FROM \"product\" WHERE \"brand_id\" = ?"); 
            ps.setInt(1, brandId);
            ResultSet rs = ps.executeQuery();  
            if(rs.next()){  
                total = rs.getInt(1);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return total;
    }
    
    public static List<Product> fetchAllRecommended()
    {
        List<Product> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product\" WHERE \"is_recommended\" = 1");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Product product = new Product();  
                product.setId(rs.getInt("id"));
                product.setUser(UserDAO.fetch(rs.getInt("user_id")));  
                product.setStockNo(rs.getString("stock_no"));  
                product.setName(rs.getString("name"));  
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("image_url")); 
                product.setUnitPrice(rs.getBigDecimal("unit_price"));  
                product.setIsRecommended(rs.getBoolean("is_recommended"));  
                product.setIsFeatured(rs.getBoolean("is_featured"));   
                product.setCreated(rs.getDate("created"));  
                product.setUpdated(rs.getDate("updated"));
                
                list.add(product);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
    
    public static List<Product> fetchAllFeatured()
    {
        List<Product> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"product\" WHERE \"is_featured\" = 1");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                Product product = new Product();  
                product.setId(rs.getInt("id"));
                product.setUser(UserDAO.fetch(rs.getInt("user_id")));  
                product.setStockNo(rs.getString("stock_no"));  
                product.setName(rs.getString("name"));  
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("image_url")); 
                product.setUnitPrice(rs.getBigDecimal("unit_price"));  
                product.setIsRecommended(rs.getBoolean("is_recommended"));  
                product.setIsFeatured(rs.getBoolean("is_featured"));   
                product.setCreated(rs.getDate("created"));  
                product.setUpdated(rs.getDate("updated"));
                
                list.add(product);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
