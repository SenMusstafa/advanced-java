package com.etiya.campus.aj.generics;

import java.util.ArrayList;
import java.util.List;

public class SimplePage<T> {
    private int pageNumber;
    private int totalRowSize;
    private int totalSize;
    private int pageSize;
    List<T> items = new ArrayList<T>();

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setTotalRowSize(int totalRowSize) {
        this.totalRowSize = totalRowSize;
    }

    public int getTotalRowSize() {
        return totalRowSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }
}
