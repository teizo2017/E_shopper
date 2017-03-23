/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.models.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author CHiBEX
 */
public class ShoppingCart {

    private Integer id;
    private String sessionId;
    private int quantity;
    private BigDecimal unitPrice;
    private Date date;
    private Product product;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer id) {
        this.id = id;
    }

    public ShoppingCart(Integer id, String sessionId, int quantity, BigDecimal unitPrice, Date date) {
        this.id = id;
        this.sessionId = sessionId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
