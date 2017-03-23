/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.controllers;

import com.emmaoyoita.eshop.models.dao.BillingDetailDAO;
import com.emmaoyoita.eshop.models.dao.InvoiceDAO;
import com.emmaoyoita.eshop.models.dao.InvoiceDetailDAO;
import com.emmaoyoita.eshop.models.dao.ShoppingCartDAO;
import com.emmaoyoita.eshop.models.dao.StateDAO;
import com.emmaoyoita.eshop.models.dto.BillingDetail;
import com.emmaoyoita.eshop.models.dto.Invoice;
import com.emmaoyoita.eshop.models.dto.InvoiceDetail;
import com.emmaoyoita.eshop.models.dto.ShoppingCart;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.HelperUtil;

/**
 *
 * @author CHiBEX
 */
public class CheckoutCompleteServlet extends HttpServlet {

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
       
        List <ShoppingCart>cart = ShoppingCartDAO.fetchBySession(request.getSession().getId());
        
        if(!cart.isEmpty()){
            String customerId = "";
            if(request.getSession().getAttribute("customerId") != null )
                customerId  = (int)request.getSession().getAttribute("customerId")+"";
            String statusMessage = "Your Order has been received and processing have started. We will get back to you as soon as possible. Thank you.";
            if(!customerId.equals("")){
                BillingDetail billing = getBillingDetail(request);
                if(request.getSession().getAttribute("billing") != null){
                    billing = (BillingDetail)request.getSession().getAttribute("billing");
                }
                recordOrder(HelperUtil.parseInt(customerId), billing, cart);
                ShoppingCartDAO.clear(request.getSession().getId());
                request.setAttribute("statusMessage", statusMessage);
            }
            else{
                String customerType = (String)request.getParameter("rdCustomerType");
                BillingDetail billing = getBillingDetail(request);
                if(customerType != null && customerType.equals("Register"))
                {
                    request.getSession().setAttribute("billing", billing);
                    response.sendRedirect(request.getContextPath()+"/login.jsp?checkout");
                    return;
                }
                else if(customerType != null && customerType.equals("Guest"))
                {
                    recordOrder(0, billing, cart);

                    ShoppingCartDAO.clear(request.getSession().getId());
                    request.setAttribute("statusMessage", statusMessage);
                }
            }
        }
        else{
           String statusMessage = "Dear Customer, your shopping cart is empty, there is nothing to checkout. Please add items to your shopping cart.";
           request.setAttribute("statusMessage", statusMessage);
        }
        request.getRequestDispatcher("/checkout-complete.jsp").forward(request, response);
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
        //processRequest(request, response);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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

    private BillingDetail getBillingDetail(HttpServletRequest request)
    {
        BillingDetail billing = new BillingDetail();
        billing.setState(StateDAO.fetch(HelperUtil.parseInt(request.getParameter("downState"))));
        billing.setCompanyName(request.getParameter("txtCompanyName"));
        billing.setEmail(request.getParameter("txtEmail"));
        billing.setTitle(request.getParameter("txtTitle"));
        billing.setFirstName(request.getParameter("txtFirstName"));
        billing.setMiddleName(request.getParameter("txtMiddleName"));
        billing.setLastName(request.getParameter("txtLastName"));
        billing.setAddress(request.getParameter("txtAddress"));
        billing.setPhoneNo(request.getParameter("txtPhoneNo"));
        billing.setSpecialNote(request.getParameter("txtSpecialNote"));
        billing.setFax(request.getParameter("txtFax"));
        
        request.getSession().setAttribute("paymentType", request.getParameter("rbPayment"));
                                
        return billing;
    }
    
    private void recordOrder(int customerId, BillingDetail billing, List <ShoppingCart>cart)
	{
            int billingId = BillingDetailDAO.insert(billing);
            billing.setId(billingId);
            int totalItems = 0;
            double totalAmount = 0;
            ListIterator<ShoppingCart> cartIterator = cart.listIterator();
            while(cartIterator.hasNext())
            {
                ShoppingCart item = cartIterator.next();
                totalItems += item.getQuantity();
                totalAmount += item.getQuantity()*item.getUnitPrice().doubleValue();
            }
            Invoice invoice = new Invoice();
            invoice.setBilling(billing);
            invoice.setCustomerId(customerId);
            invoice.setTotalItems(totalItems);
            invoice.setTotalAmount(BigDecimal.valueOf(totalAmount));
            invoice.setIsDelivered(false);
            invoice.setInvoiceNo(String.format("%5s", (InvoiceDAO.getInvoiceNo()+"")).replaceAll(" ", "0"));
            int invoiceId = InvoiceDAO.insert(invoice);
            invoice.setId(invoiceId); 
            
            cartIterator = cart.listIterator();
            while(cartIterator.hasNext())
            {
                ShoppingCart item = cartIterator.next();
                int quantity = item.getQuantity();
                double amount = item.getQuantity()*item.getUnitPrice().doubleValue();
                
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setInvoice(invoice);
                invoiceDetail.setProduct(item.getProduct());
                invoiceDetail.setQuantity(quantity);
                invoiceDetail.setTotalAmount(BigDecimal.valueOf(amount));
                InvoiceDetailDAO.insert(invoiceDetail);
            }
	}
}