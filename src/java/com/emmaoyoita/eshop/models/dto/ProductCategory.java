/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.models.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CHiBEX
 */
public class ProductCategory {

    private Integer id;
    private int parentId;
    private String name;
    private String description;
    private Date created;
    private Date updated;
    List<ProductCategory> subCats = new ArrayList();
    private List<Product> products = new ArrayList();
    private User user;

    public ProductCategory() {
    }

    public ProductCategory(Integer id) {
        this.id = id;
    }

    public ProductCategory(Integer id, int parentId, String name, Date created, Date updated) {
        this.id = id;
        this.parentId = parentId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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

    public List<ProductCategory> getSubCats() {
        return subCats;
    }

    public void setSubCats(List<ProductCategory> subCats) {
        this.subCats = subCats;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
