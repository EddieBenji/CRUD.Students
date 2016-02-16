package computomovil.fmat.lalo.abcconarchivosdeestudiantes.ModuleStudent;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lalo
 * Date: 26/01/16
 * Project: Ejemplo de Fragment
 */
public class StudentService implements Serializable {
    private List<Student> students;
    private static StudentService instance = null;

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    private StudentService() {
        students = new ArrayList<>();
    }

    public void init() {
        students.add(new Student("0900056", "Eduardo", "Canché", "Vázquez", "LIS", new Date()));
        students.add(new Student("1255845", "José", "Pérez", "Méndez", "LCC", new Date()));
        students.add(new Student("5587455", "Esteban", "Gutiérrez", "Fernández", "LIC", new Date()));
        students.add(new Student("7899455", "Jorge", "Menéndez", "Vázquez", "LCC", new Date()));
        students.add(new Student("7784451", "Jessica", "Vázquez", "Vázquez", "LIS", new Date()));
        students.add(new Student("0548798", "Manuel", "Fernández", "Vázquez", "LIS", new Date()));
    }

    public Student getByMatrix(String matrix) {
        if (students == null) return null;
        for (Student student : students)
            if (student.getMatrix().equalsIgnoreCase(matrix))
                return student;
        return null;
    }

    public void deleteStudentByMatrix(String matrix) {
        System.out.println("Matrix: " + matrix);
        for (Student student : students)
            if (student.getMatrix().equalsIgnoreCase(matrix)) {
                students.remove(student);
                break;
            }
        System.out.println(getStudents());
    }

    private boolean studentIsRegistered(String matrix) {
        for (Student student : students)
            if (student.getMatrix().equalsIgnoreCase(matrix))
                return true;
        return false;
    }

    public void updateStudent(Student student) {
        if (studentIsRegistered(student.getMatrix())) {
            this.deleteStudentByMatrix(student.getMatrix());
            this.students.add(student);
        }
        System.out.println(getStudents());
    }

    public String[] getAMatricesRegistered() {
        String[] values = new String[students.size()];
        for (int i = 0; i < students.size(); i++)
            values[i] = students.get(i).getMatrix();

        return values;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(String matrix, String name, String firstLastName,
                           String secondLastName, String major, String birthday) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.students.add(new Student(matrix, name, firstLastName, secondLastName,
                    major, sdf.parse(birthday)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentService that = (StudentService) o;

        return !(students != null ? !students.equals(that.students) : that.students != null);

    }

    @Override
    public int hashCode() {
        return students != null ? students.hashCode() : 0;
    }
}
