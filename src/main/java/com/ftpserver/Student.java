package com.ftpserver;

import java.util.ArrayList;

public class Student {

    // Student fields
    private int id;
    private String name;

    // constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // empty constructor
    public Student() {}

    // getters and setters
    public String getName() {return name;}
    public int getId() {return id;}

    public void setName(String name) { this.name = name;}
    public void setId(int id) {this.id = id;}

    // overwritten toString method for outputting students
    @Override
    public String toString() {
        return (id +
                ". "+ name);
    }
}

class Students {
    // for proper JSON write-in look
    public ArrayList<Student> students;

    // constructor
    public Students() {
            students = new ArrayList<>();
        }
}

