import java.util.ArrayList;

public class Student {
    private int id;
    private String name;

    // constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // empty constructor
    public Student() {

    }

    public String getName() {return name;}
    public int getId() {return id;}

    public void setName(String name) { this.name = name;}
    public void setId(int id) {this.id = id;}

    // overwritten toString method for outputting students
    @Override
    public String toString() {
        return (this.id +
                ". "+ this.name);
    }
}

class Students {
    public ArrayList<Student> students;

    public Students() {
            students = new ArrayList<>();
        }
}

