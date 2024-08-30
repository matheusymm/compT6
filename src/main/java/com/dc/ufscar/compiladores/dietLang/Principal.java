package com.dc.ufscar.compiladores.dietLang;

import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

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

    }
}
