package com.etiya.campus.aj.domain.order;

import com.etiya.campus.aj.domain.uygulama1.ApplicableState;
import com.etiya.campus.aj.domain.uygulama1.DomainItem;

public class CustomerOrderItem implements DomainItem {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean isApplicable(ApplicableState state) {
        return false;
    }
}
