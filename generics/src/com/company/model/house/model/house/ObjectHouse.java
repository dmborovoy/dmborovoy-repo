package com.company.model.house.model.house;

import java.util.ArrayList;
import java.util.List;

public class ObjectHouse {

    private List list = new ArrayList();

    public List getList() {
        return list;
    }

    public void add(Object t) {
        list.add(t);
    }

    public int count() {
        return list.size();
    }

    public Object findByIndex(int i) {
        return list.get(i);
    }

}
