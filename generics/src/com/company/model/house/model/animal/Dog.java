package com.company.model.house.model.animal;

import com.company.model.house.model.Barking;

public class Dog extends Animal implements Barking {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String say() {
        return "dow-wow";
    }
}
