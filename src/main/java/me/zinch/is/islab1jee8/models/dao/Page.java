package me.zinch.is.islab1jee8.models.dao;

import java.util.List;

public class Page<T> {
    private int total;
    private List<T> items;

    public Page() {
    }

    public Page(int total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
