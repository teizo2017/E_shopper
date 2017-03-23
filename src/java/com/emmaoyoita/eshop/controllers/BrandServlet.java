/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.controllers;

import com.emmaoyoita.eshop.models.dao.BrandDAO;
import com.emmaoyoita.eshop.models.dao.UserDAO;
import com.emmaoyoita.eshop.models.dto.Brand;
import com.emmaoyoita.eshop.models.dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.HelperUtil;

/**
 *
 * @author CHiBEX
 */
public class BrandServlet extends HttpServlet {

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
        
        String brandId = request.getParameter("bid");
        
        if(brandId != null)
        {            
            Brand brand = BrandDAO.fetch(HelperUtil.parseInt(brandId));
            request.setAttribute("brand", brand);
        }
        
        request.getRequestDispatcher("/admin/brand.jsp").forward(request, response);
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
        
        String name = request.getParameter("txtName");
        String description = request.getParameter("txtDescription");

        if(name == null || description == null)
        {
            //request.setAttribute("error", "Invalid user account");
            request.getRequestDispatcher("/admin/brand.jsp").forward(request, response);
            
            return;
        }
        
        String brandId = request.getParameter("bid");
        
        Brand brand = new Brand();
        brand.setName(name);
        brand.setDescription(description);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        brand.setUser(user);
        
        String message = "";
        if(brandId != null)
        {
            brand.setId(HelperUtil.parseInt(brandId));
            if(BrandDAO.update(brand))
                message = "Brand update was successful.";
        }
        else
        {
            if(BrandDAO.insert(brand))
                message = "Brand was added successfully";
        }
        
        request.setAttribute("brand", brand);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/brand.jsp").forward(request, response);
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
