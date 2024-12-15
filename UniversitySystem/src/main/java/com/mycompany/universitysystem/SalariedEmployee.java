package com.mycompany.universitysystem;

/**
 *
 * @author Abdullah Maher
 */
public class SalariedEmployee extends Employee implements PrintInfo{
    
    private double bonus;
    private double deductions;
    
    public SalariedEmployee(){
        
    }

    public SalariedEmployee(double bonus, double deductions, double salary, String job, String name, int age, Gender_t gender, String nationality, String address) {
        super(salary, job, name, age, gender, nationality, address);
        this.bonus = bonus;
        this.deductions = deductions;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }
    
    
    @Override
    public double getEmpSalary()
    {
        return (salary + bonus - deductions);
    }
    
    @Override
    public void printInfo()
    {
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
        System.out.println("Nationality : " + nationality);
        System.out.println("Address : " + address);
        System.out.println("Gender : " + gender.toString());
        System.out.println("Salary : " + getEmpSalary());
        System.out.println("Job : " + job);
    }
}
