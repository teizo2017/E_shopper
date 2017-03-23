/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.controllers;

import com.emmaoyoita.eshop.models.dao.BrandDAO;
import com.emmaoyoita.eshop.models.dao.ProductCategoryDAO;
import com.emmaoyoita.eshop.models.dao.ProductDAO;
import com.emmaoyoita.eshop.models.dao.UserDAO;
import com.emmaoyoita.eshop.models.dto.Product;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.HelperUtil;

/**
 *
 * @author CHiBEX
 */
public class ProductServlet extends HttpServlet {

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
        
        String idStr = request.getParameter("pid");
        if("post".equalsIgnoreCase(request.getMethod())){
            // Check that we have a file upload request
            ServletFileUpload.isMultipartContent(request);
            
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            
            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
 
            //Product data transer object
            Product product = new Product();
            product.setImageUrl("");
                
            List<FileItem> items;
            try {
                //Parse request parameters into FileItem List
                items = upload.parseRequest(request);
                
                // Process the uploaded items
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        String value = item.getString();
                        switch(fieldName)
                        {
                            case "txtStockNo":
                            {
                                product.setStockNo(value);
                                break;
                            }
                            case "txtName":
                            {
                                product.setName(value);
                                break;
                            }
                            case "txtDescription":
                            {
                                product.setDescription(value);
                                break;
                            }
                            case "cbRecommended":
                            {
                                product.setIsRecommended(true);
                                break;
                            }
                            case "cbFeatured":
                            {
                                product.setIsFeatured(true);
                                break;
                            }
                            case "txtUnitPrice":
                            {
                                product.setUnitPrice(BigDecimal.valueOf(HelperUtil.parseBouble(value)));
                                break;
                            }
                            case "downBrand":
                            {
                                product.setBrand(BrandDAO.fetch(HelperUtil.parseInt(value)));
                                break;
                            }
                            case "downCategory":
                            {
                                product.setCategory(ProductCategoryDAO.fetch(HelperUtil.parseInt(value)));
                                break;
                            }
                            case "txtUserId":
                            {
                                product.setUser(UserDAO.fetch(HelperUtil.parseInt(value)));
                                break;
                            }
                        }
                    } else {
                        try {

                            String root = getServletContext().getRealPath("/");
                            File path = new File(root + "/img/uploads");
                            if (!path.exists()) {
                                path.mkdirs();
                            }
                            String []mime = item.getContentType().split("/");
                            if(HelperUtil.isImageExt(mime[1]))
                            {
                                Date date = new Date();
                                
                                //use the current timespan as the file name
                                String fileName = date.getTime()+"."+mime[1];
                                
                                File uploadedFile = new File(path.getAbsolutePath() + "/" + fileName);
                                item.write(uploadedFile);
                                
                                product.setImageUrl(fileName);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                String message = "";
                if(idStr != null)
                {
                    product.setId(HelperUtil.parseInt(idStr));
                    if(product.getImageUrl().equals(""))
                    {
                        Product tempProduct = ProductDAO.fetch(HelperUtil.parseInt(idStr));
                        product.setImageUrl(tempProduct.getImageUrl());
                    }
                    if(ProductDAO.update(product))
                        message = "Product update was successful.";
                }
                else
                {
                    if(ProductDAO.insert(product))
                        message = "Product was registered successfully";
                }

                request.setAttribute("message", message);
                request.setAttribute("product", product);
            } catch (FileUploadException ex) {
                
            }
        }
        if(idStr != null)
        {            
            Product Product = ProductDAO.fetch(HelperUtil.parseInt(idStr));
            request.setAttribute("product", Product);
        }
        List brands = BrandDAO.fetchAll();
        request.setAttribute("brands", brands);
        
        List categories = ProductCategoryDAO.fetchAll();
        request.setAttribute("categories", categories);
       
        request.getRequestDispatcher("/admin/product.jsp").forward(request, response);
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
