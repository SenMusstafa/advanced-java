package com.etiya.campus.aj.domain.product;

import com.etiya.campus.aj.domain.uygulama1.ApplicableState;
import com.etiya.campus.aj.domain.uygulama1.DomainItem;

public class Campaign implements DomainItem {
    @Override
    public boolean isApplicable(ApplicableState state) {
        return false;
    }
}
