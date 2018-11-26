package br.example.gabriel.emmerson.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.example.gabriel.emmerson.banco.Database;
import br.example.gabriel.emmerson.models.Disciplina;

public class DisciplinaDAO {

    private SQLiteDatabase db;
    private Database database;

    public DisciplinaDAO(Context context){
        database = new Database(context);
    }

    public Disciplina find(int id){
        String query = "select * from " + Database.TABELA_DISCIPLINA + " where id = ?";
        Disciplina disciplina = null;
        db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(id)});
        if(cursor.moveToFirst()){
            disciplina = new Disciplina();
            disciplina.setId(cursor.getInt(0));
            disciplina.setNome(cursor.getString(1));
        }
        db.close();
        return disciplina;
    }

    public List<Disciplina> findAll(){
        String query = "select * from " + Database.TABELA_DISCIPLINA;
        List<Disciplina> disciplinas = null;
        Disciplina disciplina = null;
        db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            disciplinas = new ArrayList<>();
            while (!cursor.isAfterLast()){
                disciplina = new Disciplina();
                disciplina.setId(cursor.getInt(0));
                disciplina.setNome(cursor.getString(1));
                disciplinas.add(disciplina);
                cursor.moveToNext();
            }
        }
        return disciplinas;
    }

    public boolean insert(Disciplina disciplina){
        ContentValues contentValues;
        db = database.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("nome", disciplina.getNome());
        long resultado = db.insert(Database.TABELA_DISCIPLINA, null, contentValues);
        db.close();
        return (resultado > 0);
    }

    public boolean delete(int id){
        String clausule = "id = ?";
        db = database.getWritableDatabase();
        long resultado = db.delete(database.TABELA_DISCIPLINA, clausule, new String[]{ Integer.toString(id) });
        db.close();
        return (resultado > 0);
    }
}
