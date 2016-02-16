package computomovil.fmat.lalo.abcconarchivosdeestudiantes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import computomovil.fmat.lalo.abcconarchivosdeestudiantes.ModuleStudent.Student;
import computomovil.fmat.lalo.abcconarchivosdeestudiantes.ModuleStudent.StudentService;

public class FormStudent extends AppCompatActivity {
    private Student student;
    private String matrix, name, firstLastName, secondLastName, major, birthday;
    private StudentService stdService;

    private static final String FILE_NAME = "students.txt";
    private boolean adding;

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private void setButton(Button btn, boolean isAvailable) {
        btn.setClickable(isAvailable);
        btn.setEnabled(isAvailable);
    }

    private void setButtons() {
        setButton((Button) findViewById(R.id.btn_delete), !adding);
        setButton((Button) findViewById(R.id.btn_update), !adding);
        setButton((Button) findViewById(R.id.btn_save), adding);
    }

    private void setTexts() {
        ((EditText) this.findViewById(R.id.textMatrix)).setText(student.getMatrix());
        this.findViewById(R.id.textMatrix).setEnabled(adding);
        ((EditText) this.findViewById(R.id.textName)).setText(student.getName());
        ((EditText) this.findViewById(R.id.textFirstLastName)).setText(student.getFirstLastName());
        ((EditText) this.findViewById(R.id.textSecondLastName)).setText(student.getSecondLastName());
        ((EditText) this.findViewById(R.id.textMajor)).setText(student.getMajor());
        ((EditText) this.findViewById(R.id.textDate)).setText(sdf.format(student.getBirthday()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        student = (Student) getIntent().getSerializableExtra("student");
        adding = (int) getIntent().getSerializableExtra("adding") == 1;

        setButtons();

        stdService = StudentService.getInstance();
        this.setTexts();
    }

    private void getFields() {
        matrix = ((EditText) this.findViewById(R.id.textMatrix)).getText().toString();
        name = ((EditText) this.findViewById(R.id.textName)).getText().toString();
        firstLastName = ((EditText) this.findViewById(R.id.textFirstLastName)).getText().toString();
        secondLastName = ((EditText) this.findViewById(R.id.textSecondLastName)).getText().toString();
        major = ((EditText) this.findViewById(R.id.textMajor)).getText().toString();
        birthday = ((EditText) this.findViewById(R.id.textDate)).getText().toString();
    }

    public void updateStudent(View v) {
        getFields();
        try {
            Student std = new Student(matrix, name, firstLastName, secondLastName, major, sdf.parse(birthday));
            this.stdService.updateStudent(std);
            this.writeFileToInternalStorage();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Estudiante actualizado", Toast.LENGTH_LONG).show();
    }

    public void saveStudent(View v) {
        getFields();
        try {
            Student std = new Student(matrix, name, firstLastName, secondLastName, major, sdf.parse(birthday));
            this.stdService.addStudent(std);
            this.writeFileToInternalStorage();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Estudiante Agregado", Toast.LENGTH_LONG).show();
    }

    public void deleteStudent(View v) {
        matrix = ((EditText) this.findViewById(R.id.textMatrix)).getText().toString();
        this.stdService.deleteStudentByMatrix(matrix);
        this.writeFileToInternalStorage();
        Toast.makeText(this, "Estudiante eliminado", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("SimpleDateFormat")
    private void writeFileToInternalStorage() {
        String eol = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(this.getApplicationContext().
                    openFileOutput(FILE_NAME, Context.MODE_PRIVATE)));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Student std : stdService.getStudents()) {
                writer.write(std.getMatrix() + " ");
                writer.write(std.getName() + " ");
                writer.write(std.getFirstLastName() + " ");
                writer.write(std.getSecondLastName() + " ");
                writer.write(std.getMajor() + " ");
                writer.write(sdf.format(std.getBirthday()) + eol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}