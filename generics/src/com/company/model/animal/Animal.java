package com.company.model.animal;

//Базовый абстрактный класс для всех животных
public abstract class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
