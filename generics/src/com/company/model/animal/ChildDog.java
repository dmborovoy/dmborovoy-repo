package com.company.model.animal;

import com.company.model.animal.Animal;
import com.company.model.animal.Dog;

public class ChildDog<T1 extends Dog, T2 extends Dog> extends Animal {

    T1 parent1;
    T2 parent2;

    public ChildDog(T1 parent1, T2 parent2) {
        super(parent1.getName() + "&" + parent2.getName());
        this.parent1 = parent1;
        this.parent2 = parent2;
    }
}
