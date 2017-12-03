package com.example.deigon.concroledegastos2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deigon.concroledegastos2.Modelo.Gastos;
import com.example.deigon.concroledegastos2.dao.ControleGastosDAO;

public class Casa extends AppCompatActivity {

    private ImageButton gastoVoltar;
    private EditText nomeGasto;
    private  EditText descricaoGasto;
    private EditText valorGasto;
    private Button salvarGasto;
    private GastosHelper helper;
    private TextView titulo;
    private ImageButton filtrarGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa);

        gastoVoltar = (ImageButton) findViewById(R.id.btnReceitaVoltar);
        nomeGasto = (EditText) findViewById(R.id.edtNomeGasto);
        descricaoGasto = (EditText) findViewById(R.id.edtDescGasto);
        valorGasto = (EditText) findViewById(R.id.edtValorGasto);
        salvarGasto = (Button) findViewById(R.id.btnSalvarGasto);
        titulo = (TextView) findViewById(R.id.idTitulo);
        filtrarGasto = (ImageButton) findViewById(R.id.btnGastoListar);

        defineTitulo();

        helper = new GastosHelper(Casa.this, titulo.getText().toString());



        Intent intent =  getIntent();

        final Gastos despesa = (Gastos) intent.getSerializableExtra("despesa");

        if(despesa != null){
            helper.preencheFormulario(despesa);
        }


        gastoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Casa.this, MainActivity.class);
                startActivity(intent);
            }
        });


        salvarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gastos gastos = helper.pegaAluno();

                ControleGastosDAO dao = new ControleGastosDAO(Casa.this);

                dao.insere(gastos);

                dao.close();
                Toast.makeText(Casa.this, gastos.getNome() + " salvo", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        filtrarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle dados = new Bundle();
                Intent intent = new Intent(Casa.this, define_mes.class);

                dados.putString("titulo", "Definir");
                intent.putExtras(dados);

                startActivity(intent);
            }
        });

    }

    private void defineTitulo(){

        String titulo;
        Intent intent = getIntent();

        Bundle dados = intent.getExtras();

        titulo =dados.getString("titulo");

        this.titulo.setText(titulo);

    }


}
