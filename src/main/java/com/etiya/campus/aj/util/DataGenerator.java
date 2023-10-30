package com.etiya.campus.aj.util;

import com.etiya.campus.aj.common.Product;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    public List<Product> productList1(){
        List<Product> result = new ArrayList<>();
        for(int i =1;i<11;i++){
            result.add(generateProduct(i));
        }
        return result;
    }

    public List<Product> productList2(int x, int y){
        List<Product> result = new ArrayList<>();
        for(int i =x;i<=y;i++){
            result.add(generateProduct(i));
        }
        return result;
    }

    public Product generateProduct(int i){
        Product product = new Product("Product"+i);
        product.setProductNum("N"+i);
        product.setSerialNum("S"+i);
        return product;
    }

    public Product generateProduct(String s){
        Product product = new Product(s);
        product.setProductNum("N"+s.hashCode());
        product.setSerialNum("S"+s.hashCode());
        return product;
    }

}

