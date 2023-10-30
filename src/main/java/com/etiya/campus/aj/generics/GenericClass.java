package com.etiya.campus.aj.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericClass <T> {
    private T property;
    private List<T> propertyList = new ArrayList<>();

    public void addProperty(T property){
        this.propertyList.add(property);
    }

    public T getProperty() {
        return property;
    }

    public void setProperty(T property) {
        this.property = property;
    }

    public List<T> getPropertyList() {
        return propertyList;
    }
}
