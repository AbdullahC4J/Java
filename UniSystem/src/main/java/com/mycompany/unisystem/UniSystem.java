/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.unisystem;

/**
 *
 * @author hp
 */
public class UniSystem {

    public static void main(String[] args) {
        Student student1 = new Student("Abdullah", 24, Gender_t.Male, "Egyptian", "Cairo", 5, "Mechatronics", 3.8);
        GradStudent gradstd1 = new GradStudent("Abdullah", 24, Gender_t.Male, "Egyptian", "Cairo", 5, "Mechatronics", 3.8, 2023, true);
        SalariedEmployee emp1 = new SalariedEmployee(200, 100, 10000, "Engineer", "Ali", 25, Gender_t.Male, "Saudi", "Alx");
        HourlyEmployee emp2 = new HourlyEmployee(8 * 20, 1000, "Engineer", "Mohamed", 30, Gender_t.Male, "Germany", "Kafr Sakr");

        student1.printInfo();
        System.out.println("\n");
        gradstd1.printInfo();
        System.out.println("\n");
        emp1.printInfo();
        System.out.println("\n");
        emp2.printInfo();
    }
}
