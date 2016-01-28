package com.company.model.animal;

import com.company.model.Barking;

public class Dog extends Animal implements Barking {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String say() {
        return "dow-wow";
    }
}
