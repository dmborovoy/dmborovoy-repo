package com.company.model.house;

import java.util.ArrayList;
import java.util.List;

public class AnimalHouse<T> {

    private List<T> list = new ArrayList<>();

    public List<T> getList() {
        return list;
    }

    public void add(T t) {
        list.add(t);
    }

    public int count() {
        return list.size();
    }

    public T findByIndex(int i) {
        return list.get(i);
    }

}
