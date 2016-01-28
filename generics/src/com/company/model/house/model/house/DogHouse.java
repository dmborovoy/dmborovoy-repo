package com.company.model.house.model.house;

import com.company.model.house.model.animal.Dog;

import java.util.ArrayList;
import java.util.List;

public class DogHouse {

    private List list = new ArrayList();

    public List getList() {
        return list;
    }

    public void add(Dog t) {
        list.add(t);
    }

    public int count() {
        return list.size();
    }

    public Dog findByIndex(int i) {
        return (Dog)list.get(i);
    }
}
