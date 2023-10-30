package com.etiya.campus.aj.common;

import java.util.Objects;

public class Product {
    @Override
    public String toString() {
        return this.name + " - " + this.serialNum + " - " + this.getProductNum();
    }

    private String name;
    private Long price;
    private String serialNum;
    private String productNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productNum, product.productNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNum);
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String customServiceSpecName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
