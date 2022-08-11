import java.util.ArrayList;

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {

    }

    public String getName() {return name;}
    public int getId() {return id;}

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

