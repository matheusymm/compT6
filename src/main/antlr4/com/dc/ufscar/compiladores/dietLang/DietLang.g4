grammar DietLang;

ALTURA: 'Altura';
PESO: 'Peso';
IDADE: 'Idade';
SEXO: 'Sexo';
GEN: 'Masculino' | 'Feminimo';
TREINO: 'Treino';
OBJETIVO: 'Objetivo';
OBJS: 'Emagrecer' | 'Hipertrofia' | 'Manutencao';
TIPOS_EXS: 'Aerobico' | 'Musculacao' | 'Powerlifting' | 'Calistenia' | 'Atletismo';
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

programa: listaInfo fichaTreinos EOF;

listaInfo: info listaInfo | info;

info: (ALTURA | PESO | IDADE | SEXO | OBJETIVO) DELIM (NUM_INT | NUM_REAL | GEN | OBJS);

fichaTreinos: treino fichaTreinos | treino;

treino: TREINO IDENT ABRECHAVE listaExs FECHACHAVE ;

listaExs: listaExs exercicio | exercicio;

exercicio: TIPOS_EXS IDENT | exsUnidade;

exsUnidade: TIPOS_EXS IDENT (NUM_INT | NUM_REAL) UNIDADES;
