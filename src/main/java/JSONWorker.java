import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
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
        System.out.println("Please input student's Name:");
        list.students.add( new Student(id, scn.nextLine()));
        write();

    }

    public static void search() {
        System.out.println("Please input student id:");
        int id = scn.nextInt();

        for (int i = 0; i < list.students.size(); i++) {
            if(list.students.get(i).getId() == id) {
                System.out.println(list.students.get(i));
                return;
            }
        }
        System.out.println("No student with this id found.");
    }

    public static void delete() {
        System.out.println("Please input student id:");
        int id = scn.nextInt();
        for (int i = 0; i < list.students.size(); i++) {
            if (list.students.get(i).getId() == id && id <= list.students.size()) {
                list.students.remove(i);
                System.out.println("Student removed!");
                write();
                return;
            }
        }
        System.out.println("No student with this id found.");
    }

    public static void list() {
        // compact print
        for (int i = 0; i < list.students.size(); i++) {
            System.out.println(list.students.get(i));
        }
    }

}
