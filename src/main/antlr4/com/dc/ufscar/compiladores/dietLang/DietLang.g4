grammar DietLang;

ALGORITMO : 'algoritmo';
DECLARE : 'declare';
LITERAL : 'literal';
INTEIRO : 'inteiro';
REAL : 'real';
LOGICO : 'logico';
LEIA : 'leia';
ESCREVA : 'escreva';
SE : 'se';
FIM_SE : 'fim_se';
ENTAO: 'entao';
CASO : 'caso';
SEJA : 'seja';
SENAO : 'senao';
FIM_CASO : 'fim_caso';
PARA : 'para';
ATE : 'ate';
FACA : 'faca';
FIM_PARA : 'fim_para';
ENQUANTO : 'enquanto';
FIM_ENQUANTO : 'fim_enquanto';
REGISTRO : 'registro';
FIM_REGISTRO : 'fim_registro';
TIPO : 'tipo';
PROCEDIMENTO : 'procedimento';
FIM_PROCEDIMENTO : 'fim_procedimento';
VAR : 'var';
FUNCAO : 'funcao';
RETORNE : 'retorne';
FIM_FUNCAO : 'fim_funcao';
CONSTANTE : 'constante';
VERDADEIRO : 'verdadeiro';
FALSO : 'falso';
FIM_ALGORITMO : 'fim_algoritmo';

NUM_INT : ('0'..'9')+
        ;
NUM_REAL        : ('0'..'9')+ ('.' ('0'..'9')+)?
        ;
OP_LOGICO       : 'e' | 'ou' | 'nao'
        ;
IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* 
         ;  
PONTUACAO       : ',' | '..' | '<-' | '^' | '&' | '.'
        ;
CADEIA  : '"' ( ESC_SEQ | ~('\n'|'\''|'\\'|'"') )* '"'
        ;

fragment
ESC_SEQ : '\\\'';
COMENTARIO
    :   '{' ~('}'|'\n')* '}' {skip();}
    ;
WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {skip();}
    ;
OP_REL  :       '>' | '>=' | '<' | '<=' | '<>' | '='
        ;
OP_ARIT :       '+' | '-' | '*' | '/' | '%'
        ;
DELIM   :       ':'
        ;
ABREPAR :       '('
        ;
FECHAPAR:       ')'
        ;
ABRECHAVE : '['
        ;
FECHACHAVE : ']'
        ;

COMENTARIO_NAO_FECHADO:   '{' ~('}'|'\n')* '\n' 
    ;
CADEIA_NAO_FECHADA: '"' ( ESC_SEQ | ~('\n'|'\''|'\\'|'"') )* '\n'
        ;
ERRO: .;

programa : declaracoes 'algoritmo' corpo 'fim_algoritmo' EOF;
declaracoes : decl_local_global*;
decl_local_global : declaracao_local | declaracao_global;
declaracao_local : 'declare' variavel
                                  |     'constante' const=IDENT ':' tipo_basico '=' valor_constante
                  | 'tipo' type=IDENT ':' tipo;
variavel : identificador (',' identificador)* ':' tipo;
identificador : IDENT ('.' IDENT)* dimensao;
dimensao : ('[' exp_aritmetica ']')*;
tipo : registro | tipo_estendido;
tipo_basico : 'literal' | 'inteiro' | 'real' | 'logico';
tipo_basico_ident : tipo_basico | IDENT;
tipo_estendido : circ='^'? tipo_basico_ident;
valor_constante : CADEIA | NUM_INT | NUM_REAL | 'verdadeiro' | 'falso';
registro : 'registro' variavel* 'fim_registro';
declaracao_global : 'procedimento' IDENT '(' parametros? ')' declaracao_local* cmd*         'fim_procedimento'
                  | 'funcao' IDENT '(' parametros? ')' ':' tipo_estendido declaracao_local* cmd* 'fim_funcao';
parametro : 'var'? identificador (',' identificador)* ':' tipo_estendido;
parametros : parametro (',' parametro)*;
corpo : declaracao_local* cmd*;
cmd : cmdLeia | cmdEscreva | cmdSe | cmdCaso | cmdPara | cmdEnquanto | cmdFaca | cmdAtribuicao | cmdChamada | cmdRetorne;
cmdLeia : 'leia' '(' circ='^'? identificador (',' circ='^'? identificador)* ')';
cmdEscreva : 'escreva' '(' expressao (',' expressao)* ')';
cmdSe : 'se' expressao 'entao' cmd* ('senao' comandosElse*)? 'fim_se';
comandosElse : cmd;
cmdCaso : 'caso' exp_aritmetica 'seja' selecao ('senao' comandosElse*)? 'fim_caso';
cmdPara : 'para' IDENT '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' cmd* 'fim_para';
cmdEnquanto : 'enquanto' expressao 'faca' cmd* 'fim_enquanto';
cmdFaca : 'faca' cmd* 'ate' expressao;
cmdAtribuicao : circ='^'? identificador '<-' expressao;
cmdChamada : IDENT '(' expressao (',' expressao)* ')';
cmdRetorne : 'retorne' expressao;
selecao: item_selecao*;
item_selecao : constantes ':' cmd*;
constantes : numero_intervalo (',' numero_intervalo)*;
numero_intervalo : op_unario? NUM_INT ('..' op_unario? num2=NUM_INT)?;
op_unario : '-';
exp_aritmetica : termo (op1 termo)*;
termo : fator (op2 fator)*;
fator : parcela (op3 parcela)*;
op1 : '+' | '-';
op2 : '*' | '/';
op3 : '%';
parcela : op_unario? parcela_unario | parcela_nao_unario;
parcela_unario : circ='^'? identificador
             | IDENT '(' expressao (',' expressao)* ')'
             | NUM_INT
             | NUM_REAL
             | '(' expressao ')';
parcela_nao_unario : '&' identificador | CADEIA;
exp_relacional : exp_aritmetica (op_relacional exp_aritmetica)?;
op_relacional : '=' | '<>' | '>=' | '<=' | '>' | '<';
expressao : termo_logico (op_logico_1 termo_logico)*;
termo_logico : fator_logico (op_logico_2 fator_logico)*;
fator_logico : 'nao'? parcela_logica;
parcela_logica : ('verdadeiro' | 'falso') | exp_relacional;
op_logico_1 : 'ou';
op_logico_2 : 'e';
