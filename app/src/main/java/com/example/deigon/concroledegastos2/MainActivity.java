package com.example.deigon.concroledegastos2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.deigon.concroledegastos2.dao.ControleGastosDAO;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnCasa;
    private ImageButton btnReceita;
    private ImageButton btnSaude;
    private ImageButton btnSupermercado;
    private ImageButton btnTransporte;
    private ImageButton btnEditar;
    private ImageButton btnDetalhes;
    private ImageButton btnLazer;
    private TextView txtSaldo;

    private static final String ARQUIVO_PREFERENCIA = "ArqPreferencia";
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCasa = (ImageButton) findViewById(R.id.btnCasa);
        btnReceita = (ImageButton) findViewById(R.id.btnReceita);
        btnSaude = (ImageButton) findViewById(R.id.btnSaude);
        btnSupermercado = (ImageButton) findViewById(R.id.btnSupermercado);
        btnTransporte = (ImageButton) findViewById(R.id.btnTrasnporte);
        btnLazer = (ImageButton) findViewById(R.id.btnLazer);
        btnEditar = (ImageButton) findViewById(R.id.btnEditar);
        btnDetalhes = (ImageButton) findViewById(R.id.btnDetalhes);
        layout = (RelativeLayout) findViewById(R.id.relativeLayout);
        txtSaldo = (TextView) findViewById(R.id.txtSaldo);

        btnDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle dados = new Bundle();
                Intent intent = new Intent(MainActivity.this, ListarGastos.class);
                dados.putString("titulo", "Gastos");
                intent.putExtras(dados);

                startActivity(intent);
            }
        });

        btnCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle dados = new Bundle();
                Intent intent = new Intent(MainActivity.this, Casa.class);

                dados.putString("titulo", "Moradia");
                intent.putExtras(dados);

                startActivity(intent);
            }
        });


        btnReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle dados = new Bundle();
                Intent intent = new Intent(MainActivity.this, define_mes.class);

                dados.putString("tipo", "Receita");
                intent.putExtras(dados);

                startActivity(intent);
            }
        });

        btnSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle dados = new Bundle();
                Intent intent = new Intent(MainActivity.this, Casa.class);

                dados.putString("titulo", "Saude");
                intent.putExtras(dados);

                startActivity(intent);
            }
        });

        btnSupermercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle dados = new Bundle();
                Intent intent = new Intent(MainActivity.this, Casa.class);

                dados.putString("titulo", "Supermercado");
                intent.putExtras(dados);

                startActivity(intent);
            }
        });

        btnTransporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle dados = new Bundle();
                Intent intent = new Intent(MainActivity.this, Casa.class);

                dados.putString("titulo", "Transporte");
                intent.putExtras(dados);

                startActivity(intent);
            }
        });

        btnLazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle dados = new Bundle();
                Intent intent = new Intent(MainActivity.this, Casa.class);

                dados.putString("titulo", "Lazer");
                intent.putExtras(dados);

                startActivity(intent);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle dados = new Bundle();
                Intent intent = new Intent(MainActivity.this, Config.class);

                dados.putString("titulo", "Personalizar");
                intent.putExtras(dados);

                startActivity(intent);
            }
        });

        mudarCor();

        fontColorSaldo();
    }

    private void fontColorSaldo(){
        ControleGastosDAO dao = new ControleGastosDAO(MainActivity.this);

        txtSaldo.setText("Saldo: "+dao.buscaSaldo());

        if(dao.buscaSaldo() < 1 ){
            txtSaldo.setTextColor(Color.RED);
        }else{
            txtSaldo.setTextColor(Color.BLACK);
        }
    }

    protected void onResume() {
        super.onResume();
        fontColorSaldo();
    }

    private void mudarCor(){
        try {
            String corEscolhida;
            Intent intent = getIntent();

            Bundle dados = intent.getExtras();

            corEscolhida =dados.getString("cor");

            if(corEscolhida.equals("Verde") || corEscolhida.equals("Azul") || corEscolhida.equals("Laranja") ){
                SharedPreferences preferencia = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

                SharedPreferences.Editor editor = preferencia.edit();

                editor.putString("cor", corEscolhida);
                editor.apply();



                //recuperando cor
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
                if(sharedPreferences.contains("cor")){
                    //colocando a cor laranja como defalt
                    String corRecuperada = sharedPreferences.getString("cor","Laranja");
                    setPreferencia(corRecuperada);

                }
                setPreferencia(corEscolhida);

            }

        }catch (Exception e){
            //recuperando cor
            String corRecuperada = "Laranja";
            SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
            if(sharedPreferences.contains("cor")){
                //colocando a cor laranja como defalt
                 corRecuperada = sharedPreferences.getString("cor","Laranja");
                setPreferencia(corRecuperada);

            }
            setPreferencia(corRecuperada);

        }
    }
    private void setPreferencia(String cor){
        if(cor.equals("Azul")){
            layout.setBackgroundColor(Color.parseColor("#0000CD"));
        }
        if(cor.equals("Verde")){
            layout.setBackgroundColor(Color.parseColor("#228B22"));
        }
        if(cor.equals("Laranja")){
            layout.setBackgroundColor(Color.parseColor("#FFA500"));
        }
    }

}
