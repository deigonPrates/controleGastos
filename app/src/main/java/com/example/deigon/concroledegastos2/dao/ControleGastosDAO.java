package com.example.deigon.concroledegastos2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.deigon.concroledegastos2.Datas;
import com.example.deigon.concroledegastos2.Modelo.Gastos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deigon on 29/10/17.
 */

public class ControleGastosDAO extends SQLiteOpenHelper{

    public ControleGastosDAO (Context context){
        super(context, "controleGastos", null,4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE despesa (id INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL, nome TEXT NOT NULL, descricao TEXT NOT NULL, mes TEXT NOT NULL, tipo TEXT NOT NULL, valor FLOAT);";
        String sql2 = "CREATE TABLE ganho (id INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL, nome TEXT NOT NULL, descricao TEXT NOT NULL, mes TEXT NOT NULL,  tipo TEXT NOT NULL,valor FLOAT);";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS despesa";
        sqLiteDatabase.execSQL(sql);
        String sql2 = "DROP TABLE IF EXISTS ganho";
        sqLiteDatabase.execSQL(sql2);
        onCreate(sqLiteDatabase);
    }

    public void insere(Gastos despesa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDespesa(despesa);

        db.insert("despesa", null, dados);
    }

    public void insereGanhos(Gastos despesa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDespesa(despesa);
        db.insert("ganho", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDespesa(Gastos despesa) {
        ContentValues dados = new ContentValues();
        dados.put("nome", despesa.getNome());
        dados.put("descricao", despesa.getDescricao());
        dados.put("valor", despesa.getValor());
        dados.put("mes", despesa.getMes());
        dados.put("tipo", despesa.getTipo());
        return dados;
    }

    public List<Gastos> buscaDespesas(String mes) {
        String sql = "SELECT * FROM ganho where mes = "+"'"+mes+"'"+ ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Gastos> alunos = new ArrayList<Gastos>();
        while (c.moveToNext()){
            Gastos despesa = new Gastos();
            despesa.setId(c.getLong(c.getColumnIndex("id")));
            despesa.setNome(c.getString(c.getColumnIndex("nome")));
            despesa.setDescricao(c.getString(c.getColumnIndex("descricao")));
            despesa.setValor((float) c.getDouble(c.getColumnIndex("valor")));
            despesa.setMes(c.getString(c.getColumnIndex("mes")));
            despesa.setTipo(c.getString(c.getColumnIndex("tipo")));
            alunos.add(despesa);
        }
        c.close();
        return alunos;
    }

    public List<Gastos> buscaReceita() {
        String sql = "SELECT * FROM ganho;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Gastos> alunos = new ArrayList<Gastos>();
        while (c.moveToNext()){
            Gastos despesa = new Gastos();
            despesa.setId(c.getLong(c.getColumnIndex("id")));
            despesa.setNome(c.getString(c.getColumnIndex("nome")));
            despesa.setDescricao(c.getString(c.getColumnIndex("descricao")));
            despesa.setValor((float) c.getDouble(c.getColumnIndex("valor")));
            despesa.setMes(c.getString(c.getColumnIndex("mes")));
            despesa.setTipo(c.getString(c.getColumnIndex("tipo")));
            alunos.add(despesa);
        }
        c.close();
        return alunos;
    }

    public Float buscaSaldo(){
       Datas datas = new Datas();

       String despesa = this.somaGeralDespesa(datas.mes());
       String ganhos = this.somaGeralGanhos(datas.mes());
       Float resultado =  Float.parseFloat(ganhos) - Float.parseFloat(despesa);

        return resultado;
    }

    public String somaGeralGanhos(String mes){
        String sql = "SELECT * FROM ganho where mes = "+"'"+mes+"'"+ ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        float valor = 0;

        List<Gastos> alunos = new ArrayList<Gastos>();
        while (c.moveToNext()){
            Gastos despesa = new Gastos();
            despesa.setId(c.getLong(c.getColumnIndex("id")));
            despesa.setNome(c.getString(c.getColumnIndex("nome")));
            despesa.setDescricao(c.getString(c.getColumnIndex("descricao")));
            despesa.setValor((float) c.getDouble(c.getColumnIndex("valor")));
            despesa.setTipo(c.getString(c.getColumnIndex("tipo")));
            valor += despesa.getValor();
            alunos.add(despesa);
        }
        c.close();

        return String.valueOf(valor);
    }

    public String somaGeralDespesa(String mes){
        String sql = "SELECT * FROM despesa where mes = "+"'"+mes+"'"+";";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        float valor = 0;

        List<Gastos> alunos = new ArrayList<Gastos>();
        while (c.moveToNext()){
            Gastos despesa = new Gastos();
            despesa.setId(c.getLong(c.getColumnIndex("id")));
            despesa.setNome(c.getString(c.getColumnIndex("nome")));
            despesa.setDescricao(c.getString(c.getColumnIndex("descricao")));
            despesa.setValor((float) c.getDouble(c.getColumnIndex("valor")));
            despesa.setMes(c.getString(c.getColumnIndex("mes")));
            despesa.setTipo(c.getString(c.getColumnIndex("tipo")));
            valor += despesa.getValor();
            alunos.add(despesa);
        }
        c.close();

        return String.valueOf(valor);
    }
}
