package com.etiya.campus.aj.common;

public class Phone extends Product implements Comparable<Product> {
    private String imei;
    private Integer memory;

    public Phone(String name) {
        super(name);
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    @Override
    public int compareTo(Product o) {
        return this.getName().compareTo(o.getName());
    }
}
