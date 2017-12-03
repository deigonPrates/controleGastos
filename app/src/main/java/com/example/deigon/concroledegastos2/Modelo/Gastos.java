package com.example.deigon.concroledegastos2.Modelo;

import java.io.Serializable;

/**
 * Created by deigon on 29/10/17.
 */

public class Gastos implements Serializable {

    private long id;
    private String nome;
    private String descricao;
    private String mes;
    private float valor;
    private String tipo;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMes() {
        return mes;
    }

    public String getTipo() {
        return tipo;
    }

    public Float getValor() {
        return valor;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString(){
        return "\n"+"Nome: "+getNome()+"\n"+"Descriçao: "+getDescricao()+"\n"+"Valor: "+getValor()+"\n Tipo:"+getTipo()+"\n";
    }
}
