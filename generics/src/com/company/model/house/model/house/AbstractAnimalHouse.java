package com.company.model.house.model.house;

import com.company.model.house.model.animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class AbstractAnimalHouse {

    private List list = new ArrayList();

    public List getList() {
        return list;
    }

    public void add(Animal t) {
        list.add(t);
    }

    public int count() {
        return list.size();
    }

    public Animal findByIndex(int i) {
        return (Animal)list.get(i);
    }
}
