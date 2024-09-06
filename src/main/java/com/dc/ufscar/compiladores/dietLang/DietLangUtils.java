package com.dc.ufscar.compiladores.dietLang;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import com.dc.ufscar.compiladores.dietLang.TabelaDeSimbolos.TipoDiet;
import com.dc.ufscar.compiladores.dietLang.TabelaDeSimbolos.TiposAerobico;
import com.dc.ufscar.compiladores.dietLang.TabelaDeSimbolos.tipoTreino;

public class DietLangUtils {
    static List<String> erros = new ArrayList<String>();

    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        erros.add(String.format("Linha %d: %s", linha, mensagem));
    }

    public static TipoDiet getTipoDiet(String tipo) {
        switch (tipo.toUpperCase()) {
            case "TRAPEZIO":
                return TipoDiet.TRAPEZIO;
            case "OMBRO":
                return TipoDiet.OMBRO;
            case "BICEPS":
                return TipoDiet.BICEPS;
            case "TRICEPS":
                return TipoDiet.TRICEPS;
            case "ANTEBRACO":
                return TipoDiet.ANTEBRACO;
            case "PEITO":
                return TipoDiet.PEITO;
            case "COSTAS":
                return TipoDiet.COSTAS;
            case "ABDOMINAL":
                return TipoDiet.ABDOMINAL;
            case "LOMBAR":
                return TipoDiet.LOMBAR;
            case "GLUTEO":
                return TipoDiet.GLUTEO;
            case "QUADRICEPS":
                return TipoDiet.QUADRICEPS;
            case "PANTURILHA":
                return TipoDiet.PANTURILHA;
            case "BICEPS_FEMORAL":
                return TipoDiet.BICEPS_FEMORAL;
            case "ESTEIRA":
                return TipoDiet.ESTEIRA;
            case "CORRIDA":
                return TipoDiet.CORRIDA;
            case "ESCADA":
                return TipoDiet.ESCADA;
            case "CAMINHADA":
                return TipoDiet.CAMINHADA;
            case "BICICLETA":
                return TipoDiet.BICICLETA;
            case "ELIPTICO":
                return TipoDiet.ELIPTICO;
            case "NATACAO":
                return TipoDiet.NATACAO;
            case "LUTA":
                return TipoDiet.LUTA;
            default:
                return null;
        }
    }

    public static tipoTreino getTipoTreino(String tipo) {
        switch (tipo.toUpperCase()) {
            case "AEROBICO":
                return tipoTreino.AEROBICO;
            case "CALISTENIA":
                return tipoTreino.CALISTENIA;
            case "MUSCULACAO":
                return tipoTreino.MUSCULACAO;
            case "POWERLIFTING":
                return tipoTreino.POWERLIFTING;
            case "TREINO":
                return tipoTreino.TREINO;
            default:
                return null;
        }
    }

    public static TiposAerobico getTiposAerobico(String nome) {
        switch (nome.toUpperCase()) {
            case "ESTEIRA":
                return TiposAerobico.ESTEIRA;
            case "CORRIDA":
                return TiposAerobico.CORRIDA;
            case "ESCADA":
                return TiposAerobico.ESCADA;
            case "CAMINHADA":
                return TiposAerobico.CAMINHADA;
            case "BICICLETA":
                return TiposAerobico.BICICLETA;
            case "ELIPTICO":
                return TiposAerobico.ELIPTICO;
            case "NATACAO":
                return TiposAerobico.NATACAO;
            case "LUTA":
                return TiposAerobico.LUTA;
            default:
                return null;
        }
    }

    public static double getCalExercicio(String nome) {
        switch(nome.toUpperCase()) {
            case "CALISTENIA":
                return 5;
            case "MUSCULACAO":
                return 4.5;
            case "POWERLIFTING":
                return 6;
            default:
                return 0;
        }
    }

    public static double getCalTiposAerobico(String nome) {
        switch (nome.toUpperCase()) {
            case "ESTEIRA":
                return 6.6;
            case "CORRIDA":
                return 11.5;
            case "ESCADA":
                return 8.5;
            case "CAMINHADA":
                return 3.8;
            case "BICICLETA":
                return 6;
            case "ELIPTICO":
                return 6.5;
            case "NATACAO":
                return 8.5;
            case "LUTA":
                return 12;
            default:
                return 0;
        }
    }

    @Deprecated
    public static boolean verificarTiposCompativeis(String nome, tipoTreino tipTreino) {
        TiposAerobico tipo = DietLangUtils.getTiposAerobico(nome);
        if (tipo != null && !(tipTreino == tipoTreino.AEROBICO)) {
            return false;
        }
        if (tipo == null && !(tipTreino != tipoTreino.AEROBICO))
            return false;
        return true;
    }

    public static Float convertMin(String tempo, String unidade) {
        Float sTime = Float.valueOf(tempo);
        if (unidade.equals("h")) {
            return sTime * 60;
        }
        return sTime;
    }
}
