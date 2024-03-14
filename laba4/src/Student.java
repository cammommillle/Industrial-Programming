import java.util.Comparator;

public class Student implements Comparator<Student>
{
    int course;
    String surname;
    public Student(int course, String str) {
        this.course = course;
        this.surname = str;
    }
    @Override
    public int compare(Student student1, Student student2) {
        if (course != student2.course) {
            return course - student2.course;
        }
        return surname.compareTo(student2.surname);
    }
    public String toString() {
        return "course: " + course + " surname: " + surname;
    }

}
