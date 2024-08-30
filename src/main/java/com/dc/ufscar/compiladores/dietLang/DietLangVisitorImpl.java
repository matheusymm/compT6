package com.dc.ufscar.compiladores.dietLang;

import com.dc.ufscar.compiladores.TabelaDeSimbolos;
import com.dc.ufscar.compiladores.TabelaDeSimbolos.tipoTreino;

public class DietLangVisitorImpl extends DietLangBaseVisitor<Void> {
    // treino nao pode repetir FEITO
    // 3 treinos FEITO
    // exercicio nao pode repetir no mesmo treino FEITO
    // minimo 5 ex/treino FEITO
    // Verificar treinos aerobicos FEITO
    // minimo 2h de aerobico / programa FEITO
    TabelaDeSimbolos TREINOS;
    float QTDAEROBICO = 0; // Em minutos

    @Override
    public Void visitPrograma(DietLangParser.ProgramaContext ctx) {
        // System.out.println("Visiting Programa: " + ctx.getText());
        TREINOS = new TabelaDeSimbolos();
        Void retorno = super.visitPrograma(ctx);

        System.out.println("Tempo: " + QTDAEROBICO);
        // System.out.println("N treinos: " + TREINOS.getHashMapSize());
        // TREINOS.printTreinos();
        if (TREINOS.getHashMapSize() < 3)
            DietLangUtils.adicionarErroSemantico(ctx.getStop(), "Minimo de 3 treinos por Dieta.");
        if (QTDAEROBICO < 120)
            DietLangUtils.adicionarErroSemantico(ctx.getStop(), "Minimo de 2 horas de aerobico por Dieta");
        return retorno;
    }

    @Override
    public Void visitTreino(DietLangParser.TreinoContext ctx) {
        String treino = ctx.IDENT().getText();
        if (TREINOS.existe(treino)) {
            DietLangUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), "treino " + treino + " ja existe");
        } else {
            TREINOS.adicionar(treino, tipoTreino.TREINO);
            int count = 0;
            TabelaDeSimbolos tabTreino = TREINOS.getTabelaTreino(treino);
            for (DietLangParser.ExercicioContext ex : ctx.exercicio()) {
                String tipoTreino;
                String nomeEx;

                if (ex.exsUnidade() != null) {
                    tipoTreino = ex.exsUnidade().TIPOS_EXS().getText();
                    nomeEx = ex.exsUnidade().AEROBICO().getText();
                    String unidade = ex.exsUnidade().UNIDADES().getText();
                    String tempo = ex.exsUnidade().NUM_INT() == null ? ex.exsUnidade().NUM_REAL().getText()
                            : ex.exsUnidade().NUM_INT().getText();
                    QTDAEROBICO += DietLangUtils.convertMin(tempo, unidade);
                } else {
                    tipoTreino = ex.TIPOS_EXS().getText();
                    nomeEx = ex.MUSCULOS().getText();
                }
                if (tabTreino.existe(nomeEx)) {
                    DietLangUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), "Exercicio " + nomeEx + " ja existe");
                } else {
                    tabTreino.adicionar(nomeEx, DietLangUtils.getTipoTreino(tipoTreino));
                    count++;
                }
            }
            if (count < 5)
                DietLangUtils.adicionarErroSemantico(ctx.getStop(), "Minimo de 5 exercicios por Treino");
        }
        return super.visitTreino(ctx);
    }

}
