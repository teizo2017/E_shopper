/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.models.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author CHiBEX
 */
public class State implements Serializable {

    private Integer id;
    private String name;
    private Collection<BillingDetail> billingDetailCollection;

    public State() {
    }

    public State(Integer id) {
        this.id = id;
    }

    public State(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Collection<BillingDetail> getBillingDetailCollection() {
        return billingDetailCollection;
    }

    public void setBillingDetailCollection(Collection<BillingDetail> billingDetailCollection) {
        this.billingDetailCollection = billingDetailCollection;
    }
    
}
