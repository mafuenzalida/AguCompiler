%output "Parser.java"
%language "Java"
%define public
%define parser_class_name {Parser}
%error-verbose
%define package {Parser}
%locations
%define parse.trace

%define annotations {@SuppressWarnings("unchecked")}

%code imports {
    import java.io.*;
    import java.util.List;
    import java.util.ArrayList;
    import Tree.*;
    import Lexer.Token;
 }

%code {
    /* el método parse retorna un boolean, que indica
       si el input es válido, pero no entrega el árbol.
       Para obtenerlo, se asigna a un atributo, y se recupera
       con un getter: */
    private ASTree tree;
    public ASTree getTree () {
        return tree;
    }
}

%token                  ALIAS
%token                  MUT
%token                  CLASS
%token                  SET
%token                  VAL
%token                  VAR
%token                  LET
%token                  DEF
%token                  IF
%token                  THEN
%token                  ELSE
%token                  WHEN
%token                  WHILE
%token                  DO
%token                  NOT
%token                  IN
%token <String>         IDENTIFIER "ID"
%token <String>         T_IDENTIFIER "TYPE_ID"
%token <String>         INT_LITERAL "INT"
%token <StringBuffer>   CHAR_LITERAL "CHAR"
%token                  PLUS "+"
%token                  SUBTRACT "-"
%token                  TIMES "*"
%token                  FSLASH "/"
%token                  OP "("
%token                  CP ")"
%token                  SEMICOLON ";"
%token                  OCURLYBRACE "{"
%token                  CCURLYBRACE "}"
%token                  COMMA ","
%token                  LARROW "<-"
%token                  DOLLAR "$"
%token                  COLON ":"
%token                  RARROW "->"
%token                  EQEQ "=="
%token                  PERCENT "%"
%token                  ANDAND "&&"
%token                  OROR "||"
%token                  EQ "="
%token                  NEQ "/="
%token                  LESS "<"
%token                  MORE ">"
%token                  LESST "<="
%token                  MORET ">="
%token                  SEPARATOR "SEP"
%token <StringBuffer>   STRING_LITERAL "STRING"
%token                  ERROR
%token <String>         BOOL_LITERAL "BOOL"

%type   <ASTree>                    program do_expression expression block_stmt body let_expr
%type   <ASTree>                    decl block_expression expr1 expr2 expr3 factor conditional_expr operator_expr
%type   <ASTree>                    cond_expr body_expr_while body_expr_then body_expr_else
%type   <ArrayList<ASTree> >        stmts_list expression_comma_list expression_list 
%type   <ArrayList<ASTree> >        let_expr_comma_list block_stmt_list expression_comma_list_empty
%type   <ArrayList<ASTree> >        opt_expression_comma_list
%type   <DeclTree>                  alias_decl var_decl const_decl assignment classdef 
%type   <DeclTree>                  classdef_body_decl
%type   <ArrayList<DeclTree> >      classdef_body_decl_list
%type   <String>                    opt_mut opt_var_or_val
%type   <ArrayList<String> >        ID_list
%type   <ClassBody>                 class_bodies classdef_body class_ctor
%type   <ConstrArgs>                constructor_args opt_constructor_args
%type   <ConstrArgDec>              constr_arg_dec
%type   <ArrayList<ConstrArgDec> >  constr_arg_dec_list opt_constr_arg_dec
%type   <FuncDef>                   func_decl
%type   <FuncArg>                   func_arg
%type   <ArrayList<FuncArg> >       func_arg_list
%type   <StmtWhile>                 while_stmt
%type   <TypeTree>                  type opt_colon_type

%right "->"

%%

program:
stmts_list {
    /* se asigna el árbol al atributo tree */
    tree = new Program ($1, @$.begin);
    return YYACCEPT;
}
        ;;

stmts_list:
%empty {
    $$ = new ArrayList<ASTree> ();

 }
        |
stmts_list decl "SEP" {
    $1.add ($2);
    $$ = $1;
}
        |
stmts_list error "SEP" 
        ;;

decl:
alias_decl {
    $$ = (ASTree) $1;
}
        |
var_decl {
    $$ = (ASTree) $1;
}
        |
const_decl {
    $$ = (ASTree) $1;
}
        |
func_decl {
    $$ = (ASTree) $1;
}
        |
assignment {
    $$ = (ASTree) $1;
}
        |
classdef {
    $$ = (ASTree) $1;
}
        |
while_stmt {
    $$ = (ASTree) $1;
}
        |
expression {
    $$ = (ASTree) $1;
}
        ;;

type:
"TYPE_ID" {
    $$ = new TypeID($1, @$.begin);
}
        |
type "->" type {
    $$ = new TypeRA($1,$3, @$.begin);
}
        |
"(" type ")" {
    $$ = new TypeP($2, @$.begin);
}
        ;;

alias_decl:
ALIAS "TYPE_ID" "=" type {
    $$ = new AliasDecl($2,$4, @$.begin);
}
        ;;

var_decl:
VAR "ID" ":" type {
    $$ = new VarDecl($2,$4, @$.begin);
}
        |
VAR "ID" ":" type "=" expression {
    $$ = new VarDecl($2,$4,$6, @$.begin);
}
        ;;

const_decl:
LET "ID" ":" type "=" expression {
    $$ = new ConstDecl($2,$4,$6, @$.begin);
}
        ;;

assignment:
SET ID_list "=" expression {
    $$ = new Assignment($2,$4,"", @$.begin);
}
        |
SET "$" ID_list "=" expression {
    $$ = new Assignment($3,$5,"$", @$.begin);
}
        |
"$" "ID" "<-" expression {
    $$ = new Assignment($2,$4,"$", @$.begin);
}
        ;;

ID_list:
%empty {
    $$ = new ArrayList<String> ();
}
        |
ID_list "ID" {
    $1.add($2);
    $$ = $1;
}
        ;;

classdef:
opt_mut CLASS "TYPE_ID" opt_constructor_args "=" class_bodies {
    $$ = new ClassDef($1,$3,$4,$6, @$.begin);
}
        ;;

opt_mut:
%empty {
    $$ = "";
}
        |
MUT {
    $$ = "Mut";
}
        ;;

opt_constructor_args:
%empty  {
    $$ = null;
}
        |
constructor_args
        ;;

class_bodies:
classdef_body
        |
class_ctor
        ;;

classdef_body:
"{" classdef_body_decl_list "}" {
    $$ =  new ClassDefBody($2, @$.begin);
}
        ;;

classdef_body_decl_list:
%empty {
    $$ = new ArrayList<DeclTree> ();
}
        |
classdef_body_decl_list classdef_body_decl "SEP" {
    $1.add($2);
    $$ = $1;
}
        ;;

classdef_body_decl:
const_decl
        |
var_decl
        |
func_decl
        ;;

constructor_args:
"(" opt_constr_arg_dec ")" {
    $$ = new ConstrArgs($2, @$.begin);
}
        ;;

opt_constr_arg_dec:
%empty {
    $$ = null;
}
        |
constr_arg_dec_list
        ;;

constr_arg_dec_list:
constr_arg_dec {
    ArrayList<ConstrArgDec> aux = new ArrayList<ConstrArgDec> ();
    aux.add($1);
    $$ = aux;
}
        |
constr_arg_dec_list "," constr_arg_dec {
    $1.add($3);
    $$ = $1;
}
        ;;

constr_arg_dec:
opt_var_or_val "ID" ":" type {
    $$ = new ConstrArgDec($1,$2,$4, @$.begin);
}
        ;;

opt_var_or_val:
%empty {
    $$ = "";
}
        |
VAR {
    $$ = "Var";
}
        |
VAL {
    $$ = "Val";
}
        ;;

class_ctor:
"TYPE_ID" "(" opt_expression_comma_list ")" {
    $$ = new ClassCtor($1,$3, @$.begin);
}
        ;;

func_decl:
LET "ID" func_arg_list opt_colon_type "=" body {
    $$ = new FuncDef($2,$3,$4,$6, @$.begin);
} 
        ;;

body:
expression {
    $$ = new Body($1, @$.begin);
}
        |
block_expression {
    $$ = new Body($1, @$.begin);
}
        ;;

opt_colon_type:
%empty {
    $$ = null;
}
        |
":" type {
    $$ = $2;
}
        ;;

func_arg_list:
func_arg {
    ArrayList<FuncArg> aux = new ArrayList<FuncArg>();
    aux.add($1);
    $$ = aux;
}
        |
func_arg_list func_arg {
    $1.add($2);
    $$ = $1;
}
        ;;

func_arg:
"ID" ":" type {
    $$ = new FuncArg($1,$3, @$.begin);
}
        ;;

while_stmt:
WHILE cond_expr body_expr_while {
    $$ = new StmtWhile($2,$3, @$.begin);
}
        ;;

cond_expr:
expression {
    $$ = new CondExpr($1, @$.begin);
}
        ;;

body_expr_while:
do_expression {
    $$ = new BodyExpr($1, "Do", @$.begin);
}
        ;;


do_expression:
DO expression {
    $$ = $2;
}
        |
DO block_expression {
    $$ = $2;
}
        |
DO assignment {
    $$ = $2;
}
        ;;

opt_expression_comma_list:
%empty {
    $$ = new ArrayList<ASTree>();
}
        |
expression_comma_list
        ;;

expression_comma_list:
expression {
    ArrayList<ASTree> aux = new ArrayList<ASTree>();
    aux.add($1);
    $$ = aux;
}
        |
expression_comma_list "," expression {
    $1.add($3);
    $$ = $1;
}
        ;;

expression_comma_list_empty:
%empty {
    $$ = new ArrayList<ASTree>();
}
        |
expression_comma_list {
    $$ = $1;
}
        ;;


expression:
expression expression_list{
    $$ = new FuncCall($1,$2, @$.begin);
}
        |
operator_expr
        |
conditional_expr
        ;;

conditional_expr:
IF cond_expr THEN body_expr_then ELSE body_expr_else {
    $$ = new DefIf($2,$4,$6, @$.begin);
}
        |
WHEN expression DO expression {
    $$ = new DefWhen($2,$4, @$.begin);
}
        |
WHEN expression DO block_expression {
    $$ = new DefWhen($2,$4, @$.begin);
}
        |
LET let_expr_comma_list IN body {
    $$ = new LetExprDef($2,$4, @$.begin);
}
        ;;

body_expr_then:
body {
    $$ = new BodyExpr($1, "Then", @$.begin);
}
        ;;

body_expr_else:
body {
    $$ = new BodyExpr($1, "Else", @$.begin);
}
        ;;

operator_expr:
operator_expr "&&" expr1 {
    $$ = new BinaryExpr($1,"&&",$3, @$.begin);
}
        |
operator_expr "||" expr1 {
    $$ = new BinaryExpr($1,"||",$3, @$.begin);
}
        |
expr1
        ;;

expr1:
expr1 "<" expr2 {
    $$ = new BinaryExpr($1,"<",$3, @$.begin);
}
        |
expr1 "<=" expr2 {
    $$ = new BinaryExpr($1,"<=",$3, @$.begin);
}
        |
expr1 "==" expr2 {
    $$ = new BinaryExpr($1,"==",$3, @$.begin);
}
        |
expr1 "/=" expr2 {
    $$ = new BinaryExpr($1,"/=",$3, @$.begin);
}
        |
expr1 ">=" expr2 {
    $$ = new BinaryExpr($1,">=",$3, @$.begin);
}
        |
expr1 ">" expr2 {
    $$ = new BinaryExpr($1,">",$3, @$.begin);
}
        |
expr2
        ;;

expr2:
expr2 "+" expr3 {
    $$ = new BinaryExpr($1,"+",$3, @$.begin);
}
        |
expr2 "-" expr3 {
    $$ = new BinaryExpr($1,"-",$3, @$.begin);
}
        |
expr3
        ;;

expr3:
expr3 "*" factor {
    $$ = new BinaryExpr($1,"*",$3, @$.begin);
}
        |
expr3 "/" factor {
    $$ = new BinaryExpr($1,"/",$3, @$.begin);
}
        |
expr3 "%" factor {
    $$ = new BinaryExpr($1,"%",$3, @$.begin);
}
        |
factor
        ;;

factor:
NOT factor {
    $$ = new UnaryExpr("NOT",$2, @$.begin);
}
        |
"+" factor {
    $$ = new UnaryExpr("+",$2, @$.begin);
}
        |
"-" factor {
    $$ = new UnaryExpr("-",$2, @$.begin);
}
        |
"$" "ID" {
    $$ = new DirectAccess($2, @$.begin);
}
        |
"ID" {
    $$ = new IdExpr($1, @$.begin);
}
        |
"INT" {
    $$ = new IntExpr($1, @$.begin);
}
        |
"CHAR" {
    $$ = new CharExpr($1, @$.begin);
}
        |
"STRING" {
    $$ = new StringExpr($1, @$.begin);
}
        |
"BOOL" {
    $$ = new BoolExpr($1, @$.begin);
}
        |
"(" expression ")" {
    $$ = new ParExpr($2, @$.begin);
}
        |
"TYPE_ID" "(" expression_comma_list_empty ")" {
    $$ = new ObjDef($1,$3, @$.begin);
}
        ;;

expression_list:
expression {
    ArrayList<ASTree> aux = new ArrayList<ASTree>();
    aux.add($1);
    $$ = aux;
}
        |
expression_list expression{
    $1.add($2);
    $$ = $1;
}
        ;;

let_expr_comma_list:
let_expr {
    ArrayList<ASTree> aux = new ArrayList<ASTree>();
    aux.add($1);
    $$ = aux;
}
        |
let_expr_comma_list "," let_expr {
    $1.add($3);
    $$ = $1;
}
        ;;

let_expr:
"ID" ":" type "=" expression {
    $$ = new LetExpr($1,$3,$5, @$.begin);
}
    ;;


block_expression:
"{" block_stmt_list "}" {
    $$ = new BlockExpr($2, @$.begin);
}
        ;;

block_stmt_list:
%empty {
    $$ = new ArrayList<ASTree>();
}
        |
block_stmt_list block_stmt "SEP" {
    $1.add($2);
    $$ = $1;
}
        ;;

block_stmt:
var_decl {
    $$ = (ASTree) $1;
}
        |
const_decl {
    $$ = (ASTree) $1;
}
        |
while_stmt {
    $$ = (ASTree) $1;
}
        |
assignment {
    $$ = (ASTree) $1;
}
        |
expression {
    $$ = (ASTree) $1;
}
        ;;
%%