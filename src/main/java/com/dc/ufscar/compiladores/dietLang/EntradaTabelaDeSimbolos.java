package com.dc.ufscar.compiladores.dietLang;

import com.dc.ufscar.compiladores.dietLang.TabelaDeSimbolos.tipoTreino;

public class EntradaTabelaDeSimbolos {
    String nome;
    tipoTreino tipo;
    TabelaDeSimbolos tabelaTreino = new TabelaDeSimbolos();

    public EntradaTabelaDeSimbolos(String nome) {
        this.nome = nome;
        this.tipo = tipoTreino.TREINO;
    }

    public EntradaTabelaDeSimbolos(String nome, tipoTreino tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
}
