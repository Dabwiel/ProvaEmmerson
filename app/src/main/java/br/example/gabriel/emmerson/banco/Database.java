package br.example.gabriel.emmerson.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public final static String NOME_BANCO = "banco.bd";
    public final static int VERSAO = 1;
    public final static String TABELA_ALUNO = "alunos";
    public final static String TABELA_DISCIPLINA = "disciplinas";
    public final static String TABELA_NOTA = "notas";
    public final static String TABELA_ADN = "aluno_disciplina_nota";

    public Database(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabelaAluno = "CREATE TABLE " + TABELA_ALUNO + " (id integer primary key autoincrement, nome text not null)";
        String tabelaDisciplina = "CREATE TABLE " + TABELA_DISCIPLINA + " (id integer primary key autoincrement, nome text not null)";
        String tabelaNota = "CREATE TABLE " + TABELA_NOTA + " (id integer primary key autoincrement, valor real not null)";
        String tabelaADN = "CREATE TABLE " + TABELA_ADN + " (aluno_id integer not null, disciplina_id integer not null, nota_id integer not null, " +
                "foreign key(aluno_id) references alunos(id), foreign key(disciplina_id) references disciplinas(id), foreign key(nota_id) references notas(id))";

        db.execSQL(tabelaAluno);
        db.execSQL(tabelaDisciplina);
        db.execSQL(tabelaNota);
        db.execSQL(tabelaADN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ALUNO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_DISCIPLINA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_NOTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ADN);
        onCreate(db);
    }
}
