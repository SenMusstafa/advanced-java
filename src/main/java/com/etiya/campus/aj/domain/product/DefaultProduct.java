package com.etiya.campus.aj.domain.product;

import com.etiya.campus.aj.domain.uygulama1.ApplicableState;
import com.etiya.campus.aj.domain.uygulama1.DomainItem;

public class DefaultProduct implements DomainItem {
    private Long id;
    private String productNumber;
    private String name;

    public DefaultProduct(String productNumber, String name) {
        this.productNumber = productNumber;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isApplicable(ApplicableState state) {
        return false;
    }
}
