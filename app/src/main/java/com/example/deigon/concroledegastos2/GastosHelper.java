package com.example.deigon.concroledegastos2;

import android.widget.EditText;

import com.example.deigon.concroledegastos2.Modelo.Gastos;

/**
 * Created by deigon on 29/10/17.
 */

public class GastosHelper {


    private EditText nomeGasto;
    private EditText descricaoGasto;
    private EditText valorGasto;
    private String mes;
    private Gastos despesa;
    public String tipo;

    public GastosHelper(Casa activity, String titulo){
        Datas data = new Datas();

        nomeGasto = (EditText) activity.findViewById(R.id.edtNomeGasto);
        descricaoGasto = (EditText) activity.findViewById(R.id.edtDescGasto);
        valorGasto = (EditText) activity.findViewById(R.id.edtValorGasto);
        mes = data.mes();
        tipo = titulo;
        despesa = new Gastos();
    }

    public Gastos pegaAluno() {
        despesa.setNome(nomeGasto.getText().toString());
        despesa.setDescricao(descricaoGasto.getText().toString());
        despesa.setValor(Float.parseFloat(valorGasto.getText().toString()));
        despesa.setMes(mes);
        despesa.setTipo(tipo);
        return despesa;
    }

    public void preencheFormulario(Gastos aluno) {
        nomeGasto.setText(aluno.getNome());
        descricaoGasto.setText(aluno.getDescricao());
        valorGasto.setText(String.valueOf(aluno.getValor()));
        this.despesa = aluno;
    }
}
