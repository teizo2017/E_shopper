/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.controllers;

import com.emmaoyoita.eshop.models.dao.CustomerDAO;
import com.emmaoyoita.eshop.models.dto.Customer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CHiBEX
 */
public class CustomerLoginServlet extends HttpServlet {

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
        String statusMessage = "";
        if(request.getParameter("btnSignup") != null){
        
            String password = request.getParameter("txtPassword");
            String confirmPassword = request.getParameter("txtConfirmPassword");
            String email = request.getParameter("txtEmail");
       
            if(password.equals(confirmPassword)){
                Customer customer = new Customer();
                customer.setFullName(request.getParameter("txtFullName"));
                customer.setEmail(request.getParameter("txtEmail"));
                customer.setPhoneNo(request.getParameter("txtPhoneNo"));
                customer.setPassword(password);
                if(!CustomerDAO.isEmailExists(email)){
                    int customerId = CustomerDAO.insert(customer);
                    
                    request.getSession().setAttribute("customerId", customerId);
                    request.getSession().setAttribute("email", email);
                    if(request.getParameter("checkout") != null){
                        request.getRequestDispatcher("/checkout-complete.jsp").forward(request, response);
                        //response.sendRedirect("checkout-complete.jsp");
                        return;
                    }
                    else{
                        request.getRequestDispatcher("/account.jsp").forward(request, response);
                        //response.sendRedirect("account.jsp");
                        return;
                    }
                }
                else{
                   statusMessage = email+" already exist in our database try another email"; 
                }
            }
        }
        else if(request.getParameter("btnLogin") != null){
            
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            
            Customer customer = CustomerDAO.login(new Customer(email, password));
            if(null != customer && customer.getId() > 0){
                request.getSession().setAttribute("customerId", customer.getId());
                request.getSession().setAttribute("email", email);
                if(request.getParameter("checkout") != null){
                    request.getRequestDispatcher("/checkout-complete.jsp").forward(request, response);
                    //response.sendRedirect("checkout-complete.jsp");
                    return;
                }
                else{
                    request.getRequestDispatcher("/account.jsp").forward(request, response);
                    //response.sendRedirect("account.jsp");
                    return;
                }
            }
            else{
               statusMessage = "Login account is invalid"; 
            }
        }
        else if(request.getParameter("logout") != null){
            request.getSession().invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        
        request.setAttribute("statusMessage", statusMessage);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
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