package com.github.vbomm.orderterminal.datatransferobject;

import java.math.BigDecimal;

public class CartItem {

    private String title;
    private BigDecimal unitPrice;
    private int quantity;

    public CartItem(String title, BigDecimal unitPrice, int quantity) {
        this.title = title;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
