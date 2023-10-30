package com.etiya.campus.aj.common;

import java.util.Map;
import java.util.WeakHashMap;

public class InternContainer<T> {
    private Map<T,T> contianer = new WeakHashMap<>();

    public T get(T t){
        T type = this.contianer.get(t);
        if(type == null) this.contianer.put(t,t);
        return this.contianer.get(t);
    }

    public void put(T t){
        if(t!=null) this.contianer.put(t,t);
    }

    public boolean contains(T t){
        return this.contianer.get(t)!=null;
    }
}
