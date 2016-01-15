package com.borovoi.dmitrii.dbtutorial.model;

import java.util.Date;

/**
 * Created by dimas on 1/12/2016.
 */
public class CreditCard {

    private Long id;
    private String number;
    private String cvv;
    private String expiryDate;

    public CreditCard() {
    }

    public CreditCard(String number, String cvv, String expiryDate) {
        this.number = number;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
