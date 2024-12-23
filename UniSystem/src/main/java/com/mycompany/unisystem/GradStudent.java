package com.mycompany.unisystem;

/**
 *
 * @author Abdullah Maher
 */
public class GradStudent extends Student implements PrintInfo {

    private int gradYear;
    private boolean docsDelivered;

    public GradStudent() {
    }

    public GradStudent(String name, int age, Gender_t gender, String nationality, String address, int level, String specialization, double gpa, int gradYear, boolean docsDelivered) {
        super(name, age, gender, nationality, address, level, specialization, gpa);
        this.gradYear = gradYear;
        this.docsDelivered = docsDelivered;
    }

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public boolean isDocsDelivered() {
        return docsDelivered;
    }

    public void setDocsDelivered(boolean docsDelivered) {
        this.docsDelivered = docsDelivered;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Student Graduation Year : " + this.gradYear);
        System.out.println("Student Documentations Deliveried : " + this.docsDelivered);
    }

}
