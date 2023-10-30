package com.etiya.campus.aj.domain.uygulama1;

import com.etiya.campus.aj.common.Product;
import com.etiya.campus.aj.domain.common.CommandResult;
import com.etiya.campus.aj.domain.order.CustomerOrder;
import com.etiya.campus.aj.domain.order.CustomerOrderItem;
import com.etiya.campus.aj.domain.product.Campaign;

public class ParentChildApp {
    public static void main(String[] args){
        /*
        Order ve itemlar
        Product ve child product
         */
        ParentChildsHolder<CustomerOrder, CustomerOrderItem> orderAndItems = new ParentChildsHolder<>();
        orderAndItems.setParent(new CustomerOrder());
        orderAndItems.getChilds().add(new CustomerOrderItem());

        orderAndItems.getParent().isApplicable(null);
        //ParentChildsHolder<Product, Campaign> productAndCampaigns = new ParentChildsHolder<>();
        //ParentChildsHolder<Product, Product> productAndSunprods = new ParentChildsHolder<>();

        CustomerOrder order1 = new CustomerOrder();
        order1.isApplicable(()-> order1.getBusinessInteraction().getShrtCode().equals(""));

    }

    private static void testP(ParentChildsHolder  holder){
        holder.getParent().isApplicable(null);
    }
}
