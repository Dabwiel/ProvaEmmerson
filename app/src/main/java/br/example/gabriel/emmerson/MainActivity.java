package br.example.gabriel.emmerson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.example.gabriel.emmerson.models.Professor;

public class MainActivity extends AppCompatActivity {

    public static Professor professor = new Professor(2469, "Emmerson Silva", "emmsr2004@gmail.com");

    private Button professor_btn;
    private Button aluno_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        professor_btn = findViewById(R.id.professor_btn);
        aluno_btn = findViewById(R.id.aluno_btn);

        professor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfessorActivity.class));
            }
        });

        aluno_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AlunoActivity.class));
            }
        });
    }
}
