/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.controllers;

import com.emmaoyoita.eshop.models.dao.UserDAO;
import com.emmaoyoita.eshop.models.dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.HelperUtil;

/**
 *
 * @author CHiBEX
 */
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userId = request.getParameter("uid"); 
            
        if("post".equalsIgnoreCase(request.getMethod()))
        {
            String fullName = request.getParameter("txtFullName");  
            String userName = request.getParameter("txtUserName"); 
            String password = request.getParameter("txtPassword");
            String confirmPassword = request.getParameter("txtConfirmPassword");
            String currentUserId = request.getParameter("txtUserId");
            
            if(!password.equals(confirmPassword)){
                request.setAttribute("error", "Password did not match!");
                request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
                
                return;
            }
            
            User user = new User();
            user.setFullName(fullName);
            user.setUserName(userName);
            user.setPassword(password);
            user.setUserId(HelperUtil.parseInt(currentUserId));
            
            if(userId != null)
            {        
                user.setId(HelperUtil.parseInt(userId));
                
                if(UserDAO.update(user))
                    request.setAttribute("message", "User was updated successfully");
            }
            else
            {
                if(UserDAO.insert(user))
                    request.setAttribute("message", "User was created successfully");
            }
            
            request.setAttribute("user", user);
        }
        
        if(userId != null)
        {            
            User user = UserDAO.fetch(HelperUtil.parseInt(userId));
            request.setAttribute("user", user);
        }
        
        request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
