/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.controllers;

import com.emmaoyoita.eshop.models.dao.ProductDAO;
import com.emmaoyoita.eshop.models.dao.ShoppingCartDAO;
import com.emmaoyoita.eshop.models.dto.Product;
import com.emmaoyoita.eshop.models.dto.ShoppingCart;
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
public class ShoppingCartServlet extends HttpServlet {

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
        String productId = request.getParameter("apid");
        String action = request.getParameter("a");
        if(productId != null && action != null){
            switch (action) {
                case "add":
                    ShoppingCartDAO.add(HelperUtil.parseInt(productId), request.getSession().getId());
                    break;
                case "sub":
                    ShoppingCart shoppingCart = ShoppingCartDAO.fetchByProductAndSession(HelperUtil.parseInt(productId), request.getSession().getId());
                    if(shoppingCart.getQuantity() > 1)
                        ShoppingCartDAO.sub(HelperUtil.parseInt(productId), request.getSession().getId());
                    break;
                case "del":
                    ShoppingCartDAO.delete(HelperUtil.parseInt(productId), request.getSession().getId());
                    break;
                default:
                    break;
            }
        }
        
        if(request.getParameter("pid") != null){
            String pId = request.getParameter("pid");
            Product product = ProductDAO.fetch(HelperUtil.parseInt(pId));
            if(product.getId() > 0){
                ShoppingCart shoppingCart = ShoppingCartDAO.fetchByProductAndSession(HelperUtil.parseInt(pId), request.getSession().getId());
                if(shoppingCart == null){
                    shoppingCart = new ShoppingCart();
                    shoppingCart.setProduct(product);
                    shoppingCart.setQuantity(1);
                    shoppingCart.setSessionId(request.getSession().getId());
                    
                    ShoppingCartDAO.insert(shoppingCart);
                }
            }
        }
        
        List <ShoppingCart>cart = ShoppingCartDAO.fetchBySession(request.getSession().getId());
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
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
