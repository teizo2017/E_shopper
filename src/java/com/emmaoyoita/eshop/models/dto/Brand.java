/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.models.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author CHiBEX
 */
public class Brand {

    private Integer id;
    private String name;
    private String description;
    private Date created;
    private Date updated;
    private List<Product> products;
    private int totalProdcuts;
    private User user;

    public Brand() {
    }

    public Brand(Integer id) {
        this.id = id;
    }

    public Brand(Integer id, String name, Date created, Date updated) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the totalProdcuts
     */
    public int getTotalProdcuts() {
        return totalProdcuts;
    }

    /**
     * @param totalProdcuts the totalProdcuts to set
     */
    public void setTotalProdcuts(int totalProdcuts) {
        this.totalProdcuts = totalProdcuts;
    }
    
}
