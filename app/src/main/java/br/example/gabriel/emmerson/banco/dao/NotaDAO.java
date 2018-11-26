package br.example.gabriel.emmerson.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.example.gabriel.emmerson.banco.Database;
import br.example.gabriel.emmerson.models.Nota;

public class NotaDAO {

    private SQLiteDatabase db;
    private Database database;

    public NotaDAO(Context context){
        database = new Database(context);
    }

    public Nota find(int id){
        String query = "select * from " + Database.TABELA_NOTA + " where id = ?";
        Nota nota = null;
        db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(id)});
        if(cursor.moveToFirst()){
            nota = new Nota();
            nota.setId(cursor.getInt(0));
            nota.setValor(cursor.getDouble(1));
        }
        db.close();
        return nota;
    }

    public List<Nota> findAll(){
        String query = "select * from " + Database.TABELA_NOTA;
        List<Nota> notas = null;
        Nota nota = null;
        db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            notas = new ArrayList<>();
            while (!cursor.isAfterLast()){
                nota = new Nota();
                nota.setId(cursor.getInt(0));
                nota.setValor(cursor.getDouble(1));
                notas.add(nota);
                cursor.moveToNext();
            }
        }
        return notas;
    }

    public boolean insert(Nota nota){
        ContentValues contentValues;
        db = database.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("valor", nota.getValor());
        long resultado = db.insert(Database.TABELA_NOTA, null, contentValues);
        db.close();
        return (resultado > 0);
    }

    public boolean delete(int id){
        String clausule = "id = ?";
        db = database.getWritableDatabase();
        long resultado = db.delete(database.TABELA_NOTA, clausule, new String[]{ Integer.toString(id) });
        db.close();
        return (resultado > 0);
    }
}
