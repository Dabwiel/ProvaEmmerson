package br.example.gabriel.emmerson;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.example.gabriel.emmerson.banco.dao.AlunoDAO;
import br.example.gabriel.emmerson.models.Aluno;

public class ProfessorActivity extends AppCompatActivity {

    private TextView nome;
    private ListView list;
    private Button add;

    private List<Aluno> alunos;
    private List<String> nomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        nome = findViewById(R.id.nome);
        nome.setText(MainActivity.professor.getNome());

        list = findViewById(R.id.list);
        carregarLista();

        add = findViewById(R.id.aluno_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mensagem = new AlertDialog.Builder(ProfessorActivity.this);
                mensagem.setTitle("Inserir aluno");
                mensagem.setMessage("Nome do aluno: ");
                final EditText campo = new EditText(ProfessorActivity.this);
                mensagem.setView(campo);
                mensagem.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!campo.getText().toString().equals("")){
                            Aluno aluno = new Aluno(campo.getText().toString());
                            boolean r = new AlunoDAO(getApplicationContext()).insert(aluno);
                            if(r) Toast.makeText(getApplicationContext(), "Inserido!", 0).show();
                            else Toast.makeText(getApplicationContext(), "Ocorreu algum erro", 0).show();
                            carregarLista();
                        }
                    }
                });
                mensagem.show();
            }
        });
        /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Deletando aluno")
                        .setMessage("Tem certeza que deseja deletar esse aluno?")
                        .setPositiveButton("sim",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new AlunoDAO(getApplicationContext()).delete(alunos.get(position).getId());
                                        carregarLista();
                                    }
                                })
                        .show();
            }
        });
        */
    }

    private void carregarLista(){
        nomes = new ArrayList<>();
        alunos = new AlunoDAO(getApplicationContext()).findAll();
        if(alunos != null){
            for (Aluno a : alunos){
                nomes.add(a.getNome());
            }
        }
        else Toast.makeText(getApplicationContext(), "Lista vazia", 0).show();
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes));
    }
}
