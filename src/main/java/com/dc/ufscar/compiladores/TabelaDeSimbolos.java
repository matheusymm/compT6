package com.dc.ufscar.compiladores;

import java.util.HashMap;

public class TabelaDeSimbolos {
    public enum Objetivos {
        EMAGRECER,
        HIPERTROFIA,
        MANUTENCAO
    }

    public enum UnidadesTempo {
        MIN,
        H
    }

    public enum tipoTreino {
        TREINO,
        AEROBICO,
        MUSCULACAO,
        POWERLIFTING,
        CALISTENIA,
        ATLETISMO,
    }

    public enum TipoDiet {
        TRAPEZIO,
        OMBRO,
        BICEPS,
        TRICEPS,
        ANTEBRACO,
        PEITO,
        COSTAS,
        ABDOMINAL,
        LOMBAR,
        GLUTEO,
        QUADRICEPS,
        PANTURILHA,
        BICEPS_FEMORAL,
        ESTEIRA,
        CORRIDA,
        ESCADA,
        CAMINHADA,
        BICICLETA,
        ELIPTICO,
        NATACAO,
        LUTA
    }

    public enum TiposAerobico {
        ESTEIRA,
        CORRIDA,
        ESCADA,
        CAMINHADA,
        BICICLETA,
        ELIPTICO,
        NATACAO,
        LUTA
    }

    private final HashMap<String, EntradaTabelaDeSimbolos> tabelaDeSimbolos;

    public int getHashMapSize() {
        return tabelaDeSimbolos.size();
    }

    public void printTreinos() {
        for (String chave : tabelaDeSimbolos.keySet()) {
            System.out.println(chave);
            TabelaDeSimbolos tab = getTabelaTreino(chave);
            for (String cha : tab.tabelaDeSimbolos.keySet()) {
                System.out.println(cha + " - " + tab.verificar(cha));
            }
        }
    }

    public TabelaDeSimbolos() {
        this.tabelaDeSimbolos = new HashMap<>();
    }

    public void adicionar(String nome, tipoTreino tipo) {
        tabelaDeSimbolos.put(nome, new EntradaTabelaDeSimbolos(nome, tipo));
    }

    public boolean existe(String nome) {
        return tabelaDeSimbolos.containsKey(nome);
    }

    public tipoTreino verificar(String nome) {
        return tabelaDeSimbolos.get(nome).tipo;
    }

    public TabelaDeSimbolos getTabelaTreino(String nome) {
        return tabelaDeSimbolos.get(nome).tabelaTreino;
    }

}
