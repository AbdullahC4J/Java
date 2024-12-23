package com.mycompany.unisystem;

/**
 *
 * @author Abdullah Maher
 */
public abstract class Employee extends Person {

    protected double salary;
    protected String job;

    public Employee() {
    }

    public Employee(double salary, String job, String name, int age, Gender_t gender, String nationality, String address) {
        super(name, age, gender, nationality, address);
        this.salary = salary;
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public abstract double getEmpSalary();

}
