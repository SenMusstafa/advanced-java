package com.etiya.campus.aj.domain.order;

import com.etiya.campus.aj.domain.common.Definitions;

public class CustomerOrderItemUtility {
    public boolean isItemDispatched(CustomerOrderItem item){
        System.out.println(item.getStatus());
        return item!=null&&item.getStatus().equals(Definitions.OrderItemStatus.DISPATCED.toString());
    }
}
