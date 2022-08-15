package com.ftpserver;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class JSONWorker {

    static Scanner scn = new Scanner(System.in);
    static Students list;

    public static void write() {

        // path for local file
        String path = "F:/FTPLocal/students.json";
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Java Object to JSON file converter
            mapper.writeValue(new File(path), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            // JSON file to Java object converter
             list = mapper.readValue(new File("F:/FTPLocal/students.json"), Students.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // creates student with inputted name and generated id
    public static void createStudent() {

        System.out.println("\nPlease input student's Name:");
        String name = scn.nextLine();
        // checks for empty name
        while(name.equals("")) {
            System.out.println("Name cannot be empty!\nInput correct name:");
            name = scn.nextLine();
        }
        // id generator kinda
        int id = list.students.get(list.students.size() - 1).getId() + 1;

        list.students.add( new Student(id, name));
        write();

    }

    // searches student with inputted id
    public static void search() {

        System.out.println("\nPlease input student id:");
        int id = scn.nextInt();
        scn.nextLine();
        for (int i = 0; i < list.students.size(); i++) {
            //checks is there are any student with this id
            if(list.students.get(i).getId() == id) {
                System.out.println(list.students.get(i));
                return;
            }
        }
        System.out.println("\nNo student with this id found.");
    }

    // deletes student with inputted id
    public static void delete() {

        System.out.println("\nPlease input student id:");
        int id = Integer.parseInt(scn.nextLine());
        for (int i = 0; i < list.students.size(); i++) {
            //checks is there are any student with this id
            if (list.students.get(i).getId() == id) {
                System.out.println("\nStudent " + list.students.get(i) + " removed!");
                list.students.remove(i);
                write();
                return;
            }
        }
        System.out.println("\nNo student with this id found.");
    }

    public static void list() {
        // sorts students alphabetically
        list.students.sort(Comparator.comparing(Student::getName));
        // compact print for listing
        System.out.println("\nId: Name:");
        for (int i = 0; i < list.students.size(); i++) {
            System.out.println(list.students.get(i));
        }
    }

}
