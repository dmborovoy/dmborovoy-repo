package com.company.model.house.model.animal;

import com.company.model.house.model.Barking;

public class SmallDog extends Dog implements Barking {

    public SmallDog(String name) {
        super(name);
    }

    @Override
    public String say() {
        return "gav-gav";
    }
}
