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
import com.emmaoyoita.eshop.models.dto.BillingDetail;
import com.emmaoyoita.eshop.models.ConnectionDB;
import java.sql.Statement;

/**
 *
 * @author CHiBEX
 */
public class BillingDetailDAO {
    
    public static int insert(BillingDetail bd)
    {
        int id=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"billing_detail\"(\"state_id\", \"company_name\", \"email\", \"title\", \"first_name\", \"middle_name\", "
                    + "                                         \"last_name\", \"address\", \"phone_no\", \"fax\", \"special_note\", \"date\") "
                    + "                                        VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);  
            ps.setInt(1, 1);  
            ps.setString(2, bd.getCompanyName());
            ps.setString(3, bd.getEmail());
            ps.setString(4, bd.getTitle()); 
            ps.setString(5, bd.getFirstName());  
            ps.setString(6, bd.getMiddleName()); 
            ps.setString(7, bd.getLastName());  
            ps.setString(8, bd.getAddress());  
            ps.setString(9, bd.getPhoneNo());
            ps.setString(10, bd.getFax());
            ps.setString(11, bd.getSpecialNote()); 
            ps.executeUpdate(); 
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
               id = rs.getInt(1);
            
        }catch(SQLException e){ System.out.println(e); }  
        return id;
    }
    
    public static boolean update(BillingDetail bd)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"billing_detail\" SET \"company_name\" = ?, \"email\" = ?, \"title\" = ?, \"first_name\" = ?,"
                                            + " \"middle_name\" = ?, \"last_name\" = ?, \"address\" = ?, \"phone_no\" = ?, \"fax\" = ?, \"special_no\" = ?, "
                                            + "\"date\" =  WHERE \"id\" = ?");  
            ps.setInt(1, bd.getState().getId());  
            ps.setString(2, bd.getCompanyName());
            ps.setString(3, bd.getEmail());
            ps.setString(4, bd.getTitle()); 
            ps.setString(5, bd.getFirstName());  
            ps.setString(6, bd.getMiddleName()); 
            ps.setString(7, bd.getLastName());  
            ps.setString(8, bd.getAddress());  
            ps.setString(9, bd.getPhoneNo());
            ps.setString(10, bd.getFax());
            ps.setString(11, bd.getSpecialNote()); 
            ps.setInt(12, bd.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }
        
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"billing_detail\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static BillingDetail fetch(int id)
    {
        BillingDetail bd = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"billing_detail\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                bd = new BillingDetail();  
                bd.setId(rs.getInt("id"));
                bd.setState(StateDAO.fetch(rs.getInt("state_id")));  
                bd.setCompanyName(rs.getString("company_name"));  
                bd.setEmail(rs.getString("email"));  
                bd.setTitle(rs.getString("title"));
                bd.setFirstName(rs.getString("first_name")); 
                bd.setMiddleName(rs.getString("middle_name"));  
                bd.setLastName(rs.getString("last_name"));  
                bd.setAddress(rs.getString("address"));   
                bd.setPhoneNo(rs.getString("phone_no"));   
                bd.setFax(rs.getString("fax"));   
                bd.setSpecialNote(rs.getString("special_note"));  
                bd.setDate(rs.getDate("date"));  
            }  
        }catch(SQLException e){System.out.println(e);}  
        return bd;
    }
    
    public static  List<BillingDetail> fetchAll()
    {
        List<BillingDetail> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"billing_detail\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                BillingDetail bd = new BillingDetail();  
                bd.setId(rs.getInt("id"));
                bd.setState(StateDAO.fetch(rs.getInt("state_id")));  
                bd.setCompanyName(rs.getString("company_name"));  
                bd.setEmail(rs.getString("email"));  
                bd.setTitle(rs.getString("title"));
                bd.setFirstName(rs.getString("first_name")); 
                bd.setMiddleName(rs.getString("middle_name"));  
                bd.setLastName(rs.getString("last_name"));  
                bd.setAddress(rs.getString("address"));   
                bd.setPhoneNo(rs.getString("phone_no"));   
                bd.setFax(rs.getString("fax"));   
                bd.setSpecialNote(rs.getString("spacial_note"));  
                bd.setDate(rs.getDate("date"));
                
                list.add(bd);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
