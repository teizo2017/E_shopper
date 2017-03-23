/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.controllers;

import com.emmaoyoita.eshop.models.dao.BrandDAO;
import com.emmaoyoita.eshop.models.dao.ProductCategoryDAO;
import com.emmaoyoita.eshop.models.dao.ProductDAO;
import com.emmaoyoita.eshop.models.dto.Product;
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
public class ProductDetailsServlet extends HttpServlet {

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
        String productId = request.getParameter("pid");
        if(productId == null)
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        else{
            Product product = ProductDAO.fetch(HelperUtil.parseInt(productId));
            if(product == null)
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            
            List parentCats = ProductCategoryDAO.fetchParents();
            List brands = BrandDAO.fetchAll();
            List recommendedProducts = ProductDAO.fetchAllRecommended();
        
            request.setAttribute("product", product);
            request.setAttribute("parentCats", parentCats);
            request.setAttribute("brands", brands);
            request.setAttribute("recommendedProducts", recommendedProducts);
            request.getRequestDispatcher("/product-details.jsp").forward(request, response);
        }
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
