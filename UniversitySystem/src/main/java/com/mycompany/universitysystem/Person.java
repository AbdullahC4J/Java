package com.mycompany.universitysystem;

/**
 *
 * @author Abdullah Maher
 */

enum Gender_t {
    Male, Female;
}

public abstract class Person {
    
    protected static int personsCount = 0;
   
    protected String name;
    protected int age;
    protected Gender_t gender;
    protected String nationality;
    protected String address;
    
    public Person(){
        personsCount++;
    }
    
    public Person(String name, int age, Gender_t gender, String nationality, String address){
        personsCount++;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
        this.address = address;
    }

    public static int getPersonsCount() {
        return personsCount;
    }

    public static void setPersonsCount(int personsCount) {
        Person.personsCount = personsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender_t getGender() {
        return gender;
    }

    public void setGender(Gender_t gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
       
}
