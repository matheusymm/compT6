package com.dc.ufscar.compiladores.dietLang;

import java.io.PrintWriter;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Token;

public class ErroListener extends BaseErrorListener {
    PrintWriter pw;

    public ErroListener(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void syntaxError(org.antlr.v4.runtime.Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
            int charPositionInLine, String msg, org.antlr.v4.runtime.RecognitionException e) {

        Token t = (Token) offendingSymbol;

        if (t.getText().equals("<EOF>")) {
            pw.println("Linha " + line + ": erro sintatico proximo a EOF");

        } else {
            pw.println("Linha " + line + ": erro sintatico proximo a " + t.getText());
        }
        pw.println("FIM DA COMPILACAO");

    }
}
