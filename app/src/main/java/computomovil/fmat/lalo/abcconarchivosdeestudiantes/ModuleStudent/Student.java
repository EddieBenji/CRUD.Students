package computomovil.fmat.lalo.abcconarchivosdeestudiantes.ModuleStudent;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lalo
 * Date: 26/01/16
 * Project: Ejemplo de Fragment
 */
public class Student implements Serializable {


    private String matrix, name, firstLastName, secondLastName, major;
    private Date birthday;

    public Student() {
        this.matrix = "";
        this.name = "";
        this.firstLastName = "";
        this.secondLastName = "";
        this.major = "";
        this.birthday = new Date();
    }

    public Student(String matrix, String name) {
        this.matrix = matrix;
        this.name = name;
    }

    public Student(String matrix, String name, String firstLastName, String secondLastName, String major, Date birthday) {
        this.matrix = matrix;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.major = major;
        this.birthday = birthday;
    }

    public String getMatrix() {
        return matrix;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (matrix != null ? !matrix.equals(student.matrix) : student.matrix != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (firstLastName != null ? !firstLastName.equals(student.firstLastName) : student.firstLastName != null)
            return false;
        if (secondLastName != null ? !secondLastName.equals(student.secondLastName) : student.secondLastName != null)
            return false;
        if (major != null ? !major.equals(student.major) : student.major != null) return false;
        return !(birthday != null ? !birthday.equals(student.birthday) : student.birthday != null);

    }

    @Override
    public int hashCode() {
        int result = matrix != null ? matrix.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (firstLastName != null ? firstLastName.hashCode() : 0);
        result = 31 * result + (secondLastName != null ? secondLastName.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "matrix='" + matrix + '\'' +
                ", name='" + name + '\'' +
                ", firstLastName='" + firstLastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", major='" + major + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
