package com.mycompany.unisystem;

/**
 *
 * @author Abdullah Maher
 */
public class Student extends Person{

    private int level;
    private String specialization;
    private double gpa;

    public Student() {
    }

    public Student(String name, int age, Gender gender, String nationality, String address, int level, String specialization, double gpa) {
        super(name, age, gender, nationality, address);
        this.level = level;
        this.specialization = specialization;
        this.gpa = gpa;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Level : " + level);
        System.out.println("specialization : " + specialization);
        System.out.println("GPA : " + gpa);
    }


}
