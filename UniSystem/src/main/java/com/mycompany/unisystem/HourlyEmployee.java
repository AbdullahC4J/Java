package com.mycompany.unisystem;

/**
 *
 * @author Abdullah Maher
 */
public class HourlyEmployee extends Employee implements PrintInfo {

    private double workHours;

    public HourlyEmployee() {
    }

    public HourlyEmployee(double workHours, double salary, String job, String name, int age, Gender_t gender, String nationality, String address) {
        super(salary, job, name, age, gender, nationality, address);
        this.workHours = workHours;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    @Override
    public double getEmpSalary() {
        return (salary * workHours);
    }

    @Override
    public void printInfo() {
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
        System.out.println("Nationality : " + nationality);
        System.out.println("Address : " + address);
        System.out.println("Gender : " + gender.toString());
        System.out.println("Salary : " + getEmpSalary());
        System.out.println("Job : " + job);
    }
    
}
