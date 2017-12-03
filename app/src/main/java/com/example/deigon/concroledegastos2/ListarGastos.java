package com.example.deigon.concroledegastos2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deigon.concroledegastos2.Modelo.Gastos;
import com.example.deigon.concroledegastos2.dao.ControleGastosDAO;

import java.util.List;

public class ListarGastos extends AppCompatActivity {

    private ListView campolistaDespesas;
    private TextView titulo;
    private ImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_gastos);

        campolistaDespesas = (ListView) findViewById(R.id.ListarReceita);
        titulo = (TextView) findViewById(R.id.idTituloGasto);
        voltar = (ImageButton) findViewById(R.id.btnReceitaVoltar);


        defineTitulo();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarGastos.this, MainActivity.class);
                startActivity(intent);
            }
        });


        carregaLista();


    }
    private void defineTitulo(){

        String titulo;
        Intent intent = getIntent();

        Bundle dados = intent.getExtras();

        titulo =dados.getString("titulo");

        this.titulo.setText(titulo);

    }

    private void carregaLista() {
        String titulo;
        Intent intent = getIntent();

        Bundle dados = intent.getExtras();

        titulo =dados.getString("titulo");

        this.titulo.setText(titulo);

        ControleGastosDAO dao = new ControleGastosDAO(this);
        List<Gastos> alunos = dao.buscaDespesas(titulo);
        dao.close();

        ArrayAdapter<Gastos> adapter = new ArrayAdapter<Gastos>(this, android.R.layout.simple_list_item_1, alunos);
        campolistaDespesas.setAdapter(adapter);
    }
}
