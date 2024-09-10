package com.dc.ufscar.compiladores.dietLang;

import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.dc.ufscar.compiladores.GenerateFiles.Alimentos;
import com.dc.ufscar.compiladores.GenerateFiles.ReadJson;
import com.dc.ufscar.compiladores.dietLang.DietLangParser.ProgramaContext;

public class Principal {
    public static void main(String[] args) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        DietLangLexer lexer = new DietLangLexer(cs);

        PrintWriter pw = new PrintWriter(args[1]);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        DietLangParser parser = new DietLangParser(tokens);

        ErroListener erroListener = new ErroListener(pw);
        parser.addErrorListener(erroListener);
        ProgramaContext arvore = parser.programa();
        DietLangVisitorImpl as = new DietLangVisitorImpl();
        as.visitPrograma(arvore);
        DietLangUtils.erros.forEach( s -> {
            pw.println(s);
        });
        pw.close();
        if(DietLangUtils.erros.isEmpty()) {
            DietLangGenerator dg = new DietLangGenerator();
            dg.visitPrograma(arvore);
            PrintWriter pw2 = new PrintWriter(args[1]);
            pw2.print(dg.saida.toString());
            pw2.close();
        }

        if(args.length > 2 && args[2] != null){
            Alimentos listaAlimentos = ReadJson.readAlimentosFromJson();
            StringBuilder listaAlimentosStr = new StringBuilder();
            listaAlimentosStr.append("Carboidratos: ");
            listaAlimentosStr.append(listaAlimentos.returnCarb(5).toString());
            listaAlimentosStr.append("\nProteínas: ");
            listaAlimentosStr.append(listaAlimentos.returnProt(3).toString());
            listaAlimentosStr.append("\nGorduras: ");
            listaAlimentosStr.append(listaAlimentos.returnGord(1).toString());
            PrintWriter alimentos = new PrintWriter(args[2]);
            alimentos.print(listaAlimentosStr.toString());
            alimentos.close();
        }
    }
}
