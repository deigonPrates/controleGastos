package com.example.deigon.concroledegastos2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deigon.concroledegastos2.Modelo.Gastos;
import com.example.deigon.concroledegastos2.dao.ControleGastosDAO;

public class Receita extends AppCompatActivity {

    private ImageButton btnReceitaVoltar;
    private ImageButton btnReceitaListar;

    private FormularioHelper helper;

    private EditText nomeReceita;
    private  EditText descricaoReceita;
    private EditText valorReceita;
    private Button btnSalvarReceita;
    private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        btnReceitaListar = (ImageButton) findViewById(R.id.btnGastoListar);
        btnReceitaVoltar = (ImageButton) findViewById(R.id.btnReceitaVoltar);

        nomeReceita = (EditText) findViewById(R.id.edtNomeReceita);
        descricaoReceita = (EditText) findViewById(R.id.edtDescReceita);
        valorReceita = (EditText) findViewById(R.id.edtValorReceita);
        btnSalvarReceita = (Button) findViewById(R.id.btnSalvarReceita);
        titulo = (TextView) findViewById(R.id.idTitulo);

        defineTitulo();

        helper = new FormularioHelper(Receita.this, titulo.getText().toString());

        Intent intent =  getIntent();

        final Gastos despesa = (Gastos) intent.getSerializableExtra("despesa");

        if(despesa != null){
            helper.preencheFormulario(despesa);
        }


        btnReceitaVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Receita.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnReceitaListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle dados = new Bundle();
                Intent intent = new Intent(Receita.this, Listar.class);
                dados.putString("titulo", "Ganhos");
                intent.putExtras(dados);
                startActivity(intent);
            }
        });

        btnSalvarReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gastos ganho = helper.pegaAluno();

                ControleGastosDAO dao = new ControleGastosDAO(Receita.this);

                dao.insereGanhos(ganho);

                dao.close();
                Toast.makeText(Receita.this, "Receita " + ganho.getNome() + " salvo", Toast.LENGTH_SHORT).show();
                finish();
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
