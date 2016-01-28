package com.company.model.animal;

import com.company.model.Barking;

/**
 * Created by dimas on 9/29/2015.
 */
public class BigDog extends Dog implements Barking {

    public BigDog(String name) {
        super(name);
    }

    @Override
    public String say() {
        return "GAV-GAV";
    }
}
