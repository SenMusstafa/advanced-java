package com.etiya.campus.aj.domain.order;

import com.etiya.campus.aj.domain.customer.Customer;
import com.etiya.campus.aj.domain.product.DefaultProduct;
import com.etiya.campus.aj.domain.uygulama1.ApplicableState;
import com.etiya.campus.aj.domain.uygulama1.DomainItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerOrder implements DomainItem {
    private Long customerOrderId;
    private BusinessInteraction businessInteraction;
    private Customer customer;
    private List<CustomerOrderItem> activatedItemList;
    private List<CustomerOrderItem> itemList;

    public CustomerOrder() {
        activatedItemList = new ArrayList<>();
        itemList = new ArrayList<>();
    }
    public List<CustomerOrderItem> getActivatedItemList() {
        return activatedItemList;
    }

    public void setActivatedItemList(List<CustomerOrderItem> activatedItemList) {
        this.activatedItemList = activatedItemList;
    }

    public List<CustomerOrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<CustomerOrderItem> itemList) {
        this.itemList = itemList;
    }

    public Map<String, List<DefaultProduct>> getDocumentCodeToProductMap() {
        return documentCodeToProductMap;
    }

    public void setDocumentCodeToProductMap(Map<String, List<DefaultProduct>> documentCodeToProductMap) {
        this.documentCodeToProductMap = documentCodeToProductMap;
    }

    private Map<String, List<DefaultProduct>> documentCodeToProductMap = new HashMap<>();
    @Override
    public boolean isApplicable(ApplicableState state) {
        return false;
    }

    public BusinessInteraction getBusinessInteraction() {
        return businessInteraction;
    }

    public void setBusinessInteraction(BusinessInteraction businessInteraction) {
        this.businessInteraction = businessInteraction;
    }

    public Long getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Long customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
