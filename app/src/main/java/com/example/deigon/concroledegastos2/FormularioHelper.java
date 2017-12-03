package com.example.deigon.concroledegastos2;

import android.widget.EditText;

import com.example.deigon.concroledegastos2.Modelo.Gastos;

/**
 * Created by deigon on 29/10/17.
 */

public class FormularioHelper {


    private EditText nomeReceita;
    private EditText descricaoReceita;
    private EditText valorReceita;
    private String mes;
    private String tipo;

    private Gastos despesa;

    public FormularioHelper(Receita activity, String titulo){
        Datas data = new Datas();

        nomeReceita = (EditText) activity.findViewById(R.id.edtNomeReceita);
        descricaoReceita = (EditText) activity.findViewById(R.id.edtDescReceita);
        valorReceita = (EditText) activity.findViewById(R.id.edtValorReceita);
        mes = data.mes();
        this.tipo =  titulo;
        despesa = new Gastos();
    }

    public Gastos pegaAluno() {
        despesa.setNome(nomeReceita.getText().toString());
        despesa.setDescricao(descricaoReceita.getText().toString());
        despesa.setValor(Float.parseFloat(valorReceita.getText().toString()));
        despesa.setMes(mes);
        despesa.setTipo(tipo);
        return despesa;
    }

    public void preencheFormulario(Gastos aluno) {
        nomeReceita.setText(aluno.getNome());
        descricaoReceita.setText(aluno.getDescricao());
        valorReceita.setText(String.valueOf(aluno.getValor()));
        this.despesa = aluno;
    }
}
