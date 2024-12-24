package com.mycompany.unisystem;

/**
 *
 * @author Abdullah Maher
 */
enum Gender {
    Male, Female;
}

public abstract class Person implements PrintInfo {

    protected static int personsCount = 0;

    protected String name;
    protected int age;
    protected Gender gender;
    protected String nationality;
    protected String address;

    public Person() {
        personsCount++;
    }

    public Person(String name, int age, Gender gender, String nationality, String address) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    @Override
    public void printInfo() {
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
        System.out.println("Nationality : " + nationality);
        System.out.println("Address : " + address);
        System.out.println("Gender : " + gender.toString());
    }
}
