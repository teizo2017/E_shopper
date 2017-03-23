/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.controllers;

import com.emmaoyoita.eshop.models.dao.CustomerDAO;
import com.emmaoyoita.eshop.models.dao.ProductDAO;
import com.emmaoyoita.eshop.models.dao.WishlistDAO;
import com.emmaoyoita.eshop.models.dto.Product;
import com.emmaoyoita.eshop.models.dto.Wishlist;
import java.io.IOException;
import java.io.PrintWriter;
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
public class WishlistServlet extends HttpServlet {

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
        String customerId = "";
        if(request.getSession().getAttribute("customerId") != null )
            customerId  = (int)request.getSession().getAttribute("customerId")+"";
        
        if(customerId.equals(""))
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        
        if(request.getParameter("apid") != null)
        {
            WishlistDAO.deleteByProductAndCustomer(HelperUtil.parseInt(request.getParameter("apid")), HelperUtil.parseInt(customerId));
        }
        
        if(request.getParameter("pid") != null)
        {
            String productId = request.getParameter("pid");
            Product product = ProductDAO.fetch(HelperUtil.parseInt(productId));
            if(product != null){
                Wishlist wishlist = WishlistDAO.fetchByProductAndCustomer(HelperUtil.parseInt(productId), HelperUtil.parseInt(customerId));
                if(wishlist == null )
                {
                    wishlist = new Wishlist();
                    wishlist.setCustomer(CustomerDAO.fetch(HelperUtil.parseInt(customerId)));
                    wishlist.setProduct(ProductDAO.fetch(HelperUtil.parseInt(productId)));
                    WishlistDAO.insert(wishlist);
                }
            }
        }
        List<Wishlist> wishlist = WishlistDAO.fetchByCustomer(HelperUtil.parseInt(customerId));
        request.setAttribute("wishlist", wishlist);
        request.getRequestDispatcher("/wishlist.jsp").forward(request, response);
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
