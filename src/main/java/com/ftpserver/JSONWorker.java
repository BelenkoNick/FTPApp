package com.ftpserver;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class JSONWorker {

    static Scanner scn = new Scanner(System.in);
    static Students list;

    // done
    public static void write() {

        String path = "F:/FTPLocal/students.json";

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //half done
    public static void read() {

        ObjectMapper mapper = new ObjectMapper();

        try {

            // JSON file to Java object
             list = mapper.readValue(new File("F:/FTPLocal/students.json"), Students.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // stub for now
    public static void createStudent() {
        int id = list.students.get(list.students.size() - 1).getId() + 1;
        System.out.println("\nPlease input student's Name:");
        String name = scn.nextLine();
        while(name.equals("")) {
            System.out.println("Name cannot be empty!\nInput correct name:");
            name = scn.nextLine();
        }
        list.students.add( new Student(id, name));
        write();

    }

    public static void search() {
        System.out.println("\nPlease input student id:");
        int id = scn.nextInt();
        scn.nextLine();
        for (int i = 0; i < list.students.size(); i++) {
            if(list.students.get(i).getId() == id) {
                System.out.println(list.students.get(i));
                return;
            }
        }
        System.out.println("\nNo student with this id found.");
    }

    public static void delete() {
        System.out.println("\nPlease input student id:");
        int id = scn.nextInt();
        scn.nextLine();
        for (int i = 0; i < list.students.size(); i++) {
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
        // compact print
        list.students.sort(Comparator.comparing(Student::getName));
        System.out.println("\nId: Name:");
        for (int i = 0; i < list.students.size(); i++) {
            System.out.println(list.students.get(i));
        }
    }

}
