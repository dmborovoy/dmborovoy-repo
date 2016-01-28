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
//  ������� 1. �������� ���������� ���������, ������� ������ ������ �������. ���������� �������� � ��� ��������� ������.
//*********************************************************************************************************************
//  ���������. ����������, ��� ���� ��������� ������ ����� ����� ������������ � ���������� � ��������� ����� ����������
// type-safe. ����� ��������, ��� � ��������� ����� �������� ���� ��������, ���������� � ������� <> ������.
//--------------------------------------------------------------------------------------------------------------------
        //������� ���������, ������� ����� ������� ������ �����
        List<Dog> dogs = new ArrayList<>();
        //�������� ��������� ����������� ������� Cat � Dog, ����������� ������ Animal
        Cat tomas = new Cat("Tomas");
        Dog someDog = new Dog("D-O-G");
        Dog fluffy = new Dog("Fluffy");
        dogs.add(fluffy);
        dogs.add(someDog);
        //��������� ����� �� ������, �� � � ��������� �� ������� �� �����
        //dogs.add(tomas); - ������ ����������
        //�������� ��������� ����������� ������� ����������� ������ Animal
        BigDog spike = new BigDog("Spike");
        BigDog lessy = new BigDog("Lessy");
        SmallDog gimmy = new SmallDog("Gimmy");
        //������� ���������, ������� ����� ������� ����� ��������
        List<Animal> animals = new ArrayList<>();
        //������� �� ���� � ���������
        animals.add(fluffy);
        animals.add(tomas);
        animals.add(someDog);
        animals.add(lessy);
        animals.add(spike);
        animals.add(gimmy);
        //��� �����, �� ����� �������� ����� ����������� ������ Animal, ������ ������� �������� ������ ������ ����
        // �������� ������ ����������
        //animals.add("cat");// - ������ ����������
        for (Animal animal : animals) {
            System.out.println("My name is: " + animal.getName());
        }
//*********************************************************************************************************************
//  ������� 2. �������� ���������� ���������, ������� ������ ������ ���, ��� ����� ������� (interface Barking). ��������
// ��������� �������, ����������� ���� ��������� (SmallDog, BigDog implements Barking). �������� �������, �������
// ��������� ���������, ��������� �� ��������� �������� � � ����� ����������� ��� �������� ���������
//*********************************************************************************************************************
//  ���������: ��������, ��� � ������� ����� ���� �� ������ �����, �� � ���������, ��� ������ ����� ������ ���
// �������������� �����-�� ������� � ��������� �������. ����� ����������, ��� � ��������� �� �� ������ �������� ��
// "���������" ��������
//--------------------------------------------------------------------------------------------------------------------
        List<Barking> barkings = new ArrayList<>();
        barkings.add(someDog);
        barkings.add(spike);
        barkings.add(gimmy);
        //barkings.add(tomas); //������ ����������, ����� �� ����� �������
        for (Barking barking : barkings) {
            System.out.println("I can say: " + barking.say());
        }
//*********************************************************************************************************************
//  ������� 3. �������� ���������� ����� AnimalHouse, ������� ������ � ����� ������ ������ ������������ ������� (��������
// ������ SmallDog)
//*********************************************************************************************************************
//  ���������: �������� �������� ����������� ���������� �������. ��������� �� �� ����������� � ������������.
//--------------------------------------------------------------------------------------------------------------------

        AnimalHouse badDogHouse = new AnimalHouse();
        badDogHouse.add(someDog);
        badDogHouse.add(spike);
        badDogHouse.add(tomas);//���, ��� �� �� ��� �� ������, �� �� ������, ����� ��� ������������� ������ ������ �
        // ���� �������� ������ �����, �� ����� ��� ������� ����� � ���� ���

        AnimalHouse<Dog> niceDogHouse = new AnimalHouse<>();
        niceDogHouse.add(someDog);
        niceDogHouse.add(spike);
        niceDogHouse.add(gimmy);
        //niceDogHouse.add(tomas);//���, � ��� ���� ������ ������ �������

        for (Dog dog : niceDogHouse.getList()) {
            System.out.println("I can say from nice dog house: " + dog.getName());
        }
//*********************************************************************************************************************
//  ������� 4. �������� ���������� �����, ������� �� �������� ��� ��������� ������ � ������� �� ��������� ����������
// � ��� � ���������� ������. �������� �������� ����� ChildDog<SmallDog, BigDog>.
//*********************************************************************************************************************
//  ���������: ������� �� ���������� ������� ����� ���� ������ ��� ����
//--------------------------------------------------------------------------------------------------------------------

        ChildDog<SmallDog, BigDog> metis = new ChildDog<>(gimmy, lessy);
        //ChildDog<SmallDog, BigDog> metis_oO = new ChildDog<>(gimmy, tomas);// - ������ ����������
        System.out.println("Hi, I'm metis, my name is: " + metis.getName());

//*********************************************************************************************************************
//  ������� 5. �������� ������� ������� �����, ������� �� �� �������� ������� ����� ��� �������
//*********************************************************************************************************************
//  ���������: ������ ������� ����������� ������ extends
//--------------------------------------------------------------------------------------------------------------------

//        �� ���� ����� ������� ����� ���
        AnimalHouse<Cat> niceCatHouse = new AnimalHouse<>();
        niceCatHouse.add(tomas);
//        niceCatHouse.add(someDog); // ������ ����������
//        ���� ����� �������� ��������, �� �� ���� ������ �� ������ ���� ����������. ��� ����� �� �� ��� �� ������

        EliteDogHouse<Dog> eliteDogHouse = new EliteDogHouse<>();
        eliteDogHouse.add(spike);
        eliteDogHouse.add(gimmy);
        eliteDogHouse.add(someDog);
        //eliteDogHouse.add(tomas);// - ������ ����������. ���, �����, ���� ���� ������
        //EliteDogHouse<Cat> eliteCatHouse = new EliteDogHouse<>();// - ������ ����������. ����� ����� ���� ��������� �� ���������


//*********************************************************************************************************************
//  ������� 6. �������� ��������� ������� �������� ������� � �������� ���������� � ���������� ����� � ������ ������ �
// ����� for
//*********************************************************************************************************************
//  ���������: ��������� �������� ������������ ���������� � ������ ������� wildcard - ?
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
        //eliteDogHouses.add(poorDogHouse);//������� �� �������� ��������� ������ �� ����� ����� ������� �����
        //eliteDogHouses.add(eliteSmallDogHouse);//fail - ��� ��� ��? eliteSmallDogHouse �������� ������� �������, ��
        // �� �� ����� ��� �������� ����!
//        ���������� wildcard - '? extends Dog' - �.�. ����� �����, ������� ������������ �� Dog
        List<EliteDogHouse<? extends Dog>> beverlyHillsAlley = new ArrayList<>();
        beverlyHillsAlley.add(eliteAnyDogHouse);//OK
        beverlyHillsAlley.add(eliteSmallDogHouse);//OK
        beverlyHillsAlley.add(eliteBigDogHouse);//OK
        for (EliteDogHouse house : beverlyHillsAlley) {
            System.out.println("This house contains: " + house.count() + " dogs");
        }
    }
}
