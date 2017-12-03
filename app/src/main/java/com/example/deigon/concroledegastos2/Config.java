package com.example.deigon.concroledegastos2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Config extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButtonSelecionado;
    private Button salvar;
    private RelativeLayout layout;
    private ImageButton voltar;
    private TextView titulo;

    private static final String ARQUIVO_PREFERENCIA = "ArqPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupCor);
        salvar = (Button) findViewById(R.id.btnSalvarCor);
        layout = (RelativeLayout) findViewById(R.id.relativeLayout);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupCor);
        salvar = (Button) findViewById(R.id.btnSalvarCor);
        voltar = (ImageButton) findViewById(R.id.imageButtonVoltar);
        titulo = (TextView) findViewById(R.id.idTitulo);

        defineTitulo();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Config.this, MainActivity.class);
                startActivity(intent);
            }
        });
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idRadioButtonEscolhido = radioGroup.getCheckedRadioButtonId();

                if(idRadioButtonEscolhido > 0){
                    radioButtonSelecionado = (RadioButton) findViewById(idRadioButtonEscolhido);

                    String corEscolhida = radioButtonSelecionado.getText().toString();

                    Intent intent = new Intent(Config.this, MainActivity.class);
                    Bundle dados = new Bundle();

                    dados.putString("cor", corEscolhida);
                    intent.putExtras(dados);
                    startActivity(intent);
                }
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
