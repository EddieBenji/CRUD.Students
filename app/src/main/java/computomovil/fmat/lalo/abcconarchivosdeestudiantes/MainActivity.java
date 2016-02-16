package computomovil.fmat.lalo.abcconarchivosdeestudiantes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import computomovil.fmat.lalo.abcconarchivosdeestudiantes.ModuleStudent.Student;
import computomovil.fmat.lalo.abcconarchivosdeestudiantes.ModuleStudent.StudentService;

public class MainActivity extends ListActivity {
    private StudentService studentService;
    private static final String FILE_NAME = "students.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentService = StudentService.getInstance();
        this.readFileFromInternalStorage();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                studentService.getAMatricesRegistered()
        );

        this.setListAdapter(adapter);
    }

    public void addStudent(View v) {
        Student std = new Student();
        Intent intent = new Intent(getApplicationContext(), FormStudent.class);
        intent.putExtra("student", std);
        intent.putExtra("adding", 1);
        startActivity(intent);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Student studentSelected = studentService.getByMatrix(item);

        Intent intent = new Intent(getApplicationContext(), FormStudent.class);
        intent.putExtra("student", studentSelected);
        intent.putExtra("adding", 0);

        startActivity(intent);

    }

    private void readFileFromInternalStorage() {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput(FILE_NAME)));
            String line;
            while ((line = input.readLine()) != null) {
                String[] data = line.split(" ");
                this.studentService.addStudent(data[0], data[1], data[2], data[3], data[4], data[5]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.studentService.getStudents() == null || this.studentService.getStudents().isEmpty()) {
            System.out.println("NO HAY DATOS");
            this.studentService.init();
        }
    }
}
