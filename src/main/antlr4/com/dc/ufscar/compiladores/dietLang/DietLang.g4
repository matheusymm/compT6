lexer grammar DietLang;

ALTURA: 'Altura';
PESO: 'Peso';
IDADE: 'Idade';
SEXO: 'Masculino';
TREINO: 'Treino';
OBJETIVO: 'Objetivo';
AEROBICO: 'Aerobico';
MUSCULACO: 'Musculacao';
POWERLIFTING: 'Powerlifting';
CALISTENIA: 'Calistenia';
ATLETISMO: 'Atletismo';
UNIDADES: 'min' | 's' ;

NUM_INT : ('0'..'9')+
        ;
NUM_REAL        : ('0'..'9')+ ('.' ('0'..'9')+)?
        ;

IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* 
         ;  
PONTUACAO       : ',' | '..' | '<-' | '^' | '&' | '.'
        ;

fragment
ESC_SEQ : '\\\'';
COMENTARIO
    :   '#' ~[\r\n]*  {skip();}
    ;
WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {skip();}
    ;

DELIM   :       ':'
        ;
ABRECHAVE : '{'
        ;
FECHACHAVE : '}'
        ;

ERRO: .;
