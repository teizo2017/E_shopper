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
import com.emmaoyoita.eshop.models.dto.State;

/**
 *
 * @author CHiBEX
 */
public class StateDAO {
    
    public static boolean insert(State state)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"state\"(\"name\") VALUES(?");  
            ps.setString(1, state.getName()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        return status > 0;
    }
    
    public static boolean update(State state)
    {
        int status=0;  
        try{  
            Connection conn = ConnectionDB.getInstance(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE \"state\" SET \"name\" = ? WHERE \"id\" = ?"); 
            ps.setInt(1, state.getId()); 
            status = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); }  
        
        return status > 0;
    }
    
    public static boolean delete(int id)
    {
        int flag = 0;
        Connection conn = ConnectionDB.getInstance();
        try{  
            PreparedStatement ps = conn.prepareStatement("DELETE FROM \"state\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            flag = ps.executeUpdate();  
        }catch(SQLException e){ System.out.println(e); } 
        
        return flag > 0;
    }
    
    public static State fetch(int id)
    {
        State state = null;
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"state\" WHERE \"id\" = ?");  
            ps.setInt(1, id);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                state = new State();  
                state.setId(rs.getInt("id"));
                state.setName(rs.getString("name"));   
            }  
        }catch(SQLException e){System.out.println(e);}  
        return state;
    }
    
    public static List<State> fetchAll()
    {
        List<State> list = new ArrayList();
        try{  
            Connection conn = ConnectionDB.getInstance();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"state\"");    
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){  
                State state = new State();  
                state.setId(rs.getInt("id"));
                state.setName(rs.getString("name"));
                
                list.add(state);
            }  
        }catch(SQLException e){ System.out.println(e); } 
        
        return list;
    }
}
