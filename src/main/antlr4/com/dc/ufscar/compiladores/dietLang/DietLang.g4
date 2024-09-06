grammar DietLang;

ALTURA: 'Altura';
PESO: 'Peso';
IDADE: 'Idade';
SEXO: 'Sexo';
GEN: 'Masculino' | 'Feminimo';
TREINO: 'Treino';
OBJETIVO: 'Objetivo';
OBJS: 'Emagrecer' | 'Hipertrofia' | 'Manutencao';
TIPOS_EXS: 'Aerobico' | 'Musculacao' | 'Powerlifting' | 'Calistenia';
MUSCULOS: 'Trapezio' | 'Ombro' | 'Biceps' | 
          'Triceps' | 'Antebraco' | 'Peito' | 
          'Costas' | 'Abdominal' | 'Lombar' | 'Gluteo' |
          'Quadriceps' | 'Panturilha' | 'Biceps Femoral';
AEROBICO: 'Esteira' | 'Corrida' | 'Escada' | 'Caminhada' | 'Bicicleta' | 'Eliptico' | 'Natacao' | 'Luta';
UNIDADES: 'min' | 'h' ;

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

treino: TREINO IDENT ABRECHAVE exercicio+ FECHACHAVE ;

exercicio: TIPOS_EXS MUSCULOS | exsUnidade;

exsUnidade: TIPOS_EXS AEROBICO (NUM_INT | NUM_REAL) UNIDADES;
