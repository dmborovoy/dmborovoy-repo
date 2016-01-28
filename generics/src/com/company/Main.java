package com.company;

import com.company.model.*;
import com.company.model.animal.*;
import com.company.model.house.AnimalHouse;
import com.company.model.house.EliteDogHouse;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//*********************************************************************************************************************
//  Задание 1. Создайте обобщенную коллекцию, которая хранит только собачек. Попробуйте добавить в эту коллекцию котика.
//*********************************************************************************************************************
//  Пояснение. Показываем, что нами созданные классы также можно использовать в дженериках и коллекции также становятся
// type-safe. Также отмечаем, что в коллекцию можно добавить всех потомков, указанного в скобках <> класса.
//--------------------------------------------------------------------------------------------------------------------
        //Создаем коллекцию, которая будет хранить только собак
        List<Dog> dogs = new ArrayList<>();
        //Создадим несколкьо экземпляров классов Cat и Dog, наследников класса Animal
        Cat tomas = new Cat("Tomas");
        Dog someDog = new Dog("D-O-G");
        Dog fluffy = new Dog("Fluffy");
        dogs.add(fluffy);
        dogs.add(someDog);
        //Поскольку Томас не собака, то и в коллекцию он попасть не может
        //dogs.add(tomas); - ошибка компиляции
        //Создадим несколько экземпляров классов наследников класса Animal
        BigDog spike = new BigDog("Spike");
        BigDog lessy = new BigDog("Lessy");
        SmallDog gimmy = new SmallDog("Gimmy");
        //Создаем коллекцию, которая будет хранить любых животных
        List<Animal> animals = new ArrayList<>();
        //Добавим их всех в коллекцию
        animals.add(fluffy);
        animals.add(tomas);
        animals.add(someDog);
        animals.add(lessy);
        animals.add(spike);
        animals.add(gimmy);
        //Как видно, мы можем добавить любых наследником класса Animal, однако попытка добавить объект другог типа
        // вызывает ошибку компиляции
        //animals.add("cat");// - Ошибка компиляции
        for (Animal animal : animals) {
            System.out.println("My name is: " + animal.getName());
        }
//*********************************************************************************************************************
//  Задание 2. Создайте обобщенную коллекцию, которая хранит только тех, кто умеет гавкать (interface Barking). Создайте
// несколько классов, реализующих этот интерфейс (SmallDog, BigDog implements Barking). Создайте функцию, которая
// принимает коллекцию, состоящую из гавкающих объектов и в цикле прогавкайте все элементы коллекции
//*********************************************************************************************************************
//  Пояснение: показать, что в скобках может быть не только класс, но и интерфейс, что бывает очень удобно при
// проектировании какой-то сложной и обобщеной системы. Также показываем, что в коллекцию мы не сможем добавить не
// "гавкающих" животных
//--------------------------------------------------------------------------------------------------------------------
        List<Barking> barkings = new ArrayList<>();
        barkings.add(someDog);
        barkings.add(spike);
        barkings.add(gimmy);
        //barkings.add(tomas); //ошибка компиляции, Томас не умеет гавкать
        for (Barking barking : barkings) {
            System.out.println("I can say: " + barking.say());
        }
//*********************************************************************************************************************
//  Задание 3. Создайте обобщенный класс AnimalHouse, который хранит в своем домике только определенных собачек (например
// только SmallDog)
//*********************************************************************************************************************
//  Пояснение: показать создание собственных обобщенных классов. Намекнуть на их ограничения в наследовании.
//--------------------------------------------------------------------------------------------------------------------

        AnimalHouse badDogHouse = new AnimalHouse();
        badDogHouse.add(someDog);
        badDogHouse.add(spike);
        badDogHouse.add(tomas);//хмм, это не то что мы хотели, мы бы хотели, чтобы при использовании нашего класса в
        // него помещали только собак, но Томас без проблем залез в этот дом

        AnimalHouse<Dog> niceDogHouse = new AnimalHouse<>();
        niceDogHouse.add(someDog);
        niceDogHouse.add(spike);
        niceDogHouse.add(gimmy);
        //niceDogHouse.add(tomas);//ага, а вот сюда Томасу дорога закрыта

        for (Dog dog : niceDogHouse.getList()) {
            System.out.println("I can say from nice dog house: " + dog.getName());
        }
//*********************************************************************************************************************
//  Задание 4. Создайте обобщенный класс, который бы принимал два параметра класса и выводил бы суммарную информацию
// о них в обобщенном классе. Например создайте класс ChildDog<SmallDog, BigDog>.
//*********************************************************************************************************************
//  Пояснение: конечно же параметров классов может быть больше чем один
//--------------------------------------------------------------------------------------------------------------------

        ChildDog<SmallDog, BigDog> metis = new ChildDog<>(gimmy, lessy);
        //ChildDog<SmallDog, BigDog> metis_oO = new ChildDog<>(gimmy, tomas);// - Ошибка компиляции
        System.out.println("Hi, I'm metis, my name is: " + metis.getName());

//*********************************************************************************************************************
//  Задание 5. Создайте элитный собачий домик, который бы не позволял создать домик для котиков
//*********************************************************************************************************************
//  Пояснение: Вводим понятие ограничение сверху extends
//--------------------------------------------------------------------------------------------------------------------

//        но ведь можно создать такой дом
        AnimalHouse<Cat> niceCatHouse = new AnimalHouse<>();
        niceCatHouse.add(tomas);
//        niceCatHouse.add(someDog); // ошибка компиляции
//        сюда томас спокойно проходит, но ни одна собака не сможет сюда поселиться. Это опять не то что мы хотели

        EliteDogHouse<Dog> eliteDogHouse = new EliteDogHouse<>();
        eliteDogHouse.add(spike);
        eliteDogHouse.add(gimmy);
        eliteDogHouse.add(someDog);
        //eliteDogHouse.add(tomas);// - Ошибка компиляции. Нет, Томас, сюда тебе нельзя
        //EliteDogHouse<Cat> eliteCatHouse = new EliteDogHouse<>();// - Ошибка компиляции. Такой домик даже построить не получится


//*********************************************************************************************************************
//  Задание 6. Создайте коллекцию элитных собачьих домиком и выведите информацию о количестве собак в каждом домике в
// цикле for
//*********************************************************************************************************************
//  Пояснение: Объясняем проблемы наследования дженериков и вводим понятие wildcard - ?
//--------------------------------------------------------------------------------------------------------------------

        EliteDogHouse<SmallDog> eliteSmallDogHouse = new EliteDogHouse<>();
        eliteSmallDogHouse.add(gimmy);
        EliteDogHouse<BigDog> eliteBigDogHouse = new EliteDogHouse<>();
        eliteBigDogHouse.add(spike);
        eliteBigDogHouse.add(lessy);
        EliteDogHouse<Dog> eliteAnyDogHouse = new EliteDogHouse<>();
        eliteAnyDogHouse.add(spike);
        eliteAnyDogHouse.add(fluffy);
        AnimalHouse<Dog> poorDogHouse = new AnimalHouse<>();
        poorDogHouse.add(someDog);

        List<EliteDogHouse<Dog>> eliteDogHouses = new ArrayList<>();
        eliteDogHouses.add(eliteAnyDogHouse);//OK
        //eliteDogHouses.add(poorDogHouse);//конечно же дешевому собачьему домику не место среди элитных домов
        //eliteDogHouses.add(eliteSmallDogHouse);//fail - как так то? eliteSmallDogHouse является элитным домиком, но
        // мы не можем его добавить сюда!
//        используем wildcard - '? extends Dog' - т.е. любой класс, который унаследуется от Dog
        List<EliteDogHouse<? extends Dog>> beverlyHillsAlley = new ArrayList<>();
        beverlyHillsAlley.add(eliteAnyDogHouse);//OK
        beverlyHillsAlley.add(eliteSmallDogHouse);//OK
        beverlyHillsAlley.add(eliteBigDogHouse);//OK
        for (EliteDogHouse house : beverlyHillsAlley) {
            System.out.println("This house contains: " + house.count() + " dogs");
        }
    }
}
