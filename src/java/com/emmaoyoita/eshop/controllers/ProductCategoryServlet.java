/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.controllers;

import com.emmaoyoita.eshop.models.dao.ProductCategoryDAO;
import com.emmaoyoita.eshop.models.dao.UserDAO;
import com.emmaoyoita.eshop.models.dto.ProductCategory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.HelperUtil;

/**
 *
 * @author CHiBEX
 */
public class ProductCategoryServlet extends HttpServlet {

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
        List categories = ProductCategoryDAO.fetchAll();
        request.setAttribute("categories", categories);
        
        String catId = request.getParameter("cid"); 
        if("post".equalsIgnoreCase(request.getMethod())){
            String name = request.getParameter("txtName");  
            String description = request.getParameter("txtDescription"); 
            String parent = request.getParameter("downParent");
            
            int parentId = 0;
            try{
                parentId = Integer.parseInt(parent);
            }catch(NumberFormatException nfe){}
            
            ProductCategory cat = new ProductCategory();
            cat.setName(name);
            cat.setDescription(description);
            cat.setParentId(parentId);
            cat.setUser(UserDAO.fetch(HelperUtil.parseInt(request.getParameter("txtUserId").trim())));
            
            String message = "";
            if(catId != null)
            {
                cat.setId(HelperUtil.parseInt(catId.trim()));
                if(ProductCategoryDAO.update(cat))
                    message = "Product category update was successful.";
            }
            else
            {
                if(ProductCategoryDAO.insert(cat))
                    message = "Product category was registered successfully";
            }
            
            request.setAttribute("category", cat);
            request.setAttribute("message", message);
        }
        
        if(catId != null)
        {            
            ProductCategory category = ProductCategoryDAO.fetch(HelperUtil.parseInt(catId));
            request.setAttribute("category", category);
        }
        
        request.getRequestDispatcher("/admin/product-category.jsp").forward(request, response);
        
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
