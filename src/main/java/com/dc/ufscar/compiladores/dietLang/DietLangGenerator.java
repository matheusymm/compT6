package com.dc.ufscar.compiladores.dietLang;

public class DietLangGenerator extends DietLangBaseVisitor<Void> {
    StringBuilder saida;
    TabelaDeSimbolos tabela;
    Double altura;
    int idade;
    String sexo;
    Double peso = .0;
    Double gasto = .0;
    String objetivo;

    public DietLangGenerator() {
        saida = new StringBuilder();
        this.tabela = new TabelaDeSimbolos();
    }   

    @Override
    public Void visitPrograma(DietLangParser.ProgramaContext ctx) {
        saida.append("<!DOCTYPE html>\n");
        saida.append("<html>\n");
        saida.append("<head>\n");
        saida.append("<script src=\"https://cdn.plot.ly/plotly-latest.min.js\"></script>");
        saida.append("<title>Dieta</title>\n");
        saida.append("</head>\n");
        saida.append("<body>\n");
        Void retorno = super.visitPrograma(ctx);

        Double tmb;
        if(sexo.equals("Masculino")) {
            tmb = (66 + (13.75*peso) + (5*altura) - (6.8*idade));
        } else {
            tmb = (655 + (9.56*peso) + (1.85*altura) - (4.68*idade));
        }
        saida.append("<h1>Informações</h1>\n");
        saida.append("<p>Taxa Metabólica Basal: " + String.format("%.2f", tmb) + "Kcal" + "</p>\n");
        saida.append("<p>Gasto Total Diário: " + String.format("%.2f", gasto) + "Kcal" + "</p>\n");

        Double carb, prot, gord;
        Double gastoObj = gasto;
        Double a = .0;
        Double b = peso;
        if(objetivo.equals("Hipertrofia")) {
            gastoObj += 0.2*gastoObj;
            a = 0.8*(gastoObj/7700);
        } else if(objetivo.equals("Emagrecer")) {
            gastoObj -= 0.2*gastoObj;
            a = 0.8*((-1)*gastoObj/7700);
        } else {
            a = 1*(gastoObj/7700);
        }
        carb = (gastoObj*0.5)/4;
        prot = (gastoObj*0.3)/4;
        gord = (gastoObj*0.2)/9;
        saida.append("<p>Dieta:</p>\n");
        saida.append("<p>Carboidratos: " + String.format("%.2f", carb) + "g" + "</p>\n");
        saida.append("<p>Proteínas: " + String.format("%.2f", prot) + "g" + "</p>\n");
        saida.append("<p>Gorduras: " + String.format("%.2f", gord) + "g" + "</p>\n");
        saida.append("<div id=\"graphDiv\" style=\"width:600px;height:400px;\"></div>");
        saida.append("<script>function plotFunction(a, b) {let xValues = [];let yValues = [];for (let x = 0; x <= 10; x += 0.1) {const y = a * x + b;if (y >= 0) {xValues.push(x);yValues.push(y);}}const trace = {x: xValues,y: yValues,type: 'scatter',mode: 'lines',line: {color: 'blue'}};Plotly.newPlot('graphDiv', [trace], {title: `Grafico da variacao do peso ( ${a}x + ${b} )`,xaxis: {title: 'Dias', range: [0, 10]},  yaxis: {title: 'Peso', range: [0, Math.max(...yValues)]} });}</script>");
        saida.append("<script>plotFunction(" + String.format("%.2f",a) + "," + b + ");</script>");
        saida.append("</body>\n");
        saida.append("</html>\n");
        return retorno;
    } 

    @Override
    public Void visitInfo(DietLangParser.InfoContext ctx) { 
        if(ctx.ALTURA() != null) {
            altura = Double.parseDouble(ctx.NUM_REAL().getText());
        } else if(ctx.PESO() != null) {
            peso = Double.parseDouble(ctx.NUM_INT().getText());
        } else if(ctx.IDADE() != null) {
            idade = Integer.parseInt(ctx.NUM_INT().getText());
        } else if(ctx.SEXO() != null) {
            sexo = ctx.GEN().getText();
        } else if(ctx.OBJETIVO() != null) {
            objetivo = ctx.OBJS().getText();
        }

        return super.visitInfo(ctx);
    } 

    @Override
    public Void visitTreino(DietLangParser.TreinoContext ctx) { 
        for(DietLangParser.ExercicioContext exercicio : ctx.exercicio()) {
            String nome;
            if(exercicio.exsUnidade() != null) {
                nome = "AEROBICO";
            } else {
                nome = exercicio.TIPOS_EXS().getText();
            }
            String tipo;
            if(nome.equals("AEROBICO")) {
                tipo = exercicio.exsUnidade().AEROBICO().getText();
                Double tempo = exercicio.exsUnidade().NUM_INT() == null ? Double.parseDouble(exercicio.exsUnidade().NUM_REAL().getText()) : Double.parseDouble(exercicio.exsUnidade().NUM_INT().getText());
                gasto += DietLangUtils.getCalTiposAerobico(tipo)*peso*DietLangUtils.convertMin(tempo.toString(), exercicio.exsUnidade().UNIDADES().getText())/60;
            } else {
                tipo = exercicio.TIPOS_EXS().getText();
                gasto += DietLangUtils.getCalExercicio(tipo)*peso*0.25;
            }
        }
        return super.visitTreino(ctx);
    }
}
