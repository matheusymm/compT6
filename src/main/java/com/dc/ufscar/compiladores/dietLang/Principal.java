package com.dc.ufscar.compiladores.dietLang;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public class Principal {
    public static void main(String[] args) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        DietLang lexer = new DietLang(cs);
        Token t = null;
        while ((t = lexer.nextToken()).getType() != Token.EOF) {
            System.out.println("<" + DietLang.VOCABULARY.getDisplayName(t.getType())
                    + "," + t.getText() + ">");
        }
    }
}
