/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.models.dto;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author CHiBEX
 */
public class User {

    private int id;
    private int userId;
    private String fullName;
    private String userName;
    private String password;
    private Date created;
    private Date updated;
    private Collection<Product> productCollection;
    private Collection<Brand> brandCollection;
    private Collection<ProductCategory> productCategoryCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(int id, int userId, String fullName, String userName, String password, Date created, Date updated) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return password;
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

    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    public Collection<Brand> getBrandCollection() {
        return brandCollection;
    }

    public void setBrandCollection(Collection<Brand> brandCollection) {
        this.brandCollection = brandCollection;
    }

    public Collection<ProductCategory> getProductCategoryCollection() {
        return productCategoryCollection;
    }

    public void setProductCategoryCollection(Collection<ProductCategory> productCategoryCollection) {
        this.productCategoryCollection = productCategoryCollection;
    }
}
