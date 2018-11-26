package br.example.gabriel.emmerson.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.example.gabriel.emmerson.banco.Database;
import br.example.gabriel.emmerson.models.Aluno;

public class AlunoDAO {

    private SQLiteDatabase db;
    private Database database;

    public AlunoDAO(Context context){
        database = new Database(context);
    }

    public Aluno find(int id){
        String query = "select * from " + Database.TABELA_ALUNO + " where id = ?";
        Aluno aluno = null;
        db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(id)});
        if(cursor.moveToFirst()){
            aluno = new Aluno();
            aluno.setId(cursor.getInt(0));
            aluno.setNome(cursor.getString(1));
        }
        db.close();
        return aluno;
    }

    public List<Aluno> findAll(){
        String query = "select * from " + Database.TABELA_ALUNO;
        List<Aluno> alunos = null;
        Aluno aluno = null;
        db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            alunos = new ArrayList<>();
            while (!cursor.isAfterLast()){
                aluno = new Aluno();
                aluno.setId(cursor.getInt(0));
                aluno.setNome(cursor.getString(1));
                alunos.add(aluno);
                cursor.moveToNext();
            }
        }
        return alunos;
    }

    public boolean insert(Aluno aluno){
        ContentValues contentValues;
        db = database.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("nome", aluno.getNome());
        long resultado = db.insert(Database.TABELA_ALUNO, null, contentValues);
        db.close();
        return (resultado > 0);
    }

    public boolean delete(int id){
        String clausule = "id = ?";
        db = database.getWritableDatabase();
        long resultado = db.delete(database.TABELA_ALUNO, clausule, new String[]{ Integer.toString(id) });
        db.close();
        return (resultado > 0);
    }
}
