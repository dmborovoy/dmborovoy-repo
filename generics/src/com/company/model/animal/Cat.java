package com.company.model.animal;


import com.company.model.Say;

public class Cat extends Animal implements Say {
    public Cat(String name) {
        super(name);
    }

    @Override
    public String say() {
        return "mow-wow";
    }
}
