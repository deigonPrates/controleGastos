package com.example.deigon.concroledegastos2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class define_mes extends AppCompatActivity {

    private EditText mes;
    private Button filtrar;
    private String escolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_mes);

        mes = (EditText) findViewById(R.id.edtMes);
        filtrar = (Button) findViewById(R.id.btnFiltrarMes);

        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escolha =  converteString(mes.getText().toString());
                if(escolha.equals("invalido")){
                    Toast.makeText(getApplicationContext(),"Mes escolhido invalido", Toast.LENGTH_LONG).show();
                }else{
                    Bundle dados = new Bundle();
                    Intent intent = new Intent(define_mes.this, ListarGastos.class);
                    dados.putString("titulo", escolha);
                    intent.putExtras(dados);

                    startActivity(intent);
                }
            }
        });


    }

    protected String converteString(String mes){
        String mesEscolhido = "0";
        switch (mes){
            case "1":
                mesEscolhido = "Janeiro";
                break;
            case "2":
                mesEscolhido = "Fevereiro";
                break;
            case "3":
                mesEscolhido = "Março";
                break;
            case "4":
                mesEscolhido = "Abril";
                break;
            case "5":
                mesEscolhido = "Maio";
                break;
            case "6":
                mesEscolhido = "Junho";
                break;
            case "7":
                mesEscolhido = "Julho";
                break;
            case "8":
                mesEscolhido = "Agosto";
                break;
            case "9":
                mesEscolhido = "Setembro";
                break;
            case "10":
                mesEscolhido = "Outubro";
                break;
            case "11":
                mesEscolhido = "Novembro";
                break;
            case "12":
                mesEscolhido = "Dezembro";
                break;
            default:
               mesEscolhido = "invalido";
        }
        return mesEscolhido;
    }

}
