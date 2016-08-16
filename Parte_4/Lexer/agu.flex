package Lexer;
import Parser.*;

/**
 * Lexer for Agu language by Martin Fuenzalida
 * IIC2323 Pontificia Universidad Catolica de Chile
 */

%%
%class Lexer
%unicode
%line
%column
%public
%int
%implements Parser.Lexer

%{
	StringBuffer string = new StringBuffer();

	private Token token(final int type) {
		return new Token(type, yyline, yycolumn);
	}
	private Token token(final int type, Object value) {
		return new Token(type, yyline, yycolumn,value);
	}

	/* se implementa la interfaz requerida por el parser */
  	private Object yylval;

	public Object getLVal () {
		return yylval;
	}

	public void yyerror (Parser.Location loc, String msg) {
	    System.err.printf ("%d:%d: %s\n", loc.begin.line, loc.begin.column, msg);
	}

	public Position getStartPos () {
	    return new Position (yyline, yycolumn);
	}

	public Position getEndPos () {
	    return new Position (yyline, yycolumn+yylength ());
	}

%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace 	   = {LineTerminator} | [ \t\f]
Separator 	   = ;(;|{WhiteSpace})*

Comment = "//" {InputCharacter}* {LineTerminator}?

Identifier = ({Lowercase_letter}|_) ({Letter}|{Digit}|_)*\'*(\!|\?)?
T_Identifier = {Uppercase_letter} ({Letter}|{Digit}|_)*\'*(\!|\?)?

Int_literal = (0|({Posdigit}|{Posdigit}{Digit}|{Posdigit}{Digit}{Digit}))((_)?{Digit}{Digit}{Digit})*
Posdigit = [1-9]
Digit = 0 | {Posdigit}
Letter = {Lowercase_letter}|{Uppercase_letter}
Lowercase_letter = [a-z]|á|é|í|ó|ú|ñ
Uppercase_letter = [A-Z]|Á|É|Í|Ó|Ú|Ñ

Char_literal = ({Nonescaped}|{Escaped})
String_literal = ({Nonescaped}|{Escaped})({Nonescaped}|{Escaped})*
Nonescaped = [^\\\'\"]
Escaped = \\(\"|\'|a|b|n|r|t|v|\\|({Oct}{Oct}{Oct}))
Oct = [0-7]

Bool_literal = (true|false)

Operators = "+"|"-"|"*"|"/"|"("|")"|";"|"{"|"}"|","|"<-"|"$"|"$"|":"|"->"|"=="|"%"|"&&"|"||"|"="|"/="|"<"|">"|"<="|">="

	
%state STRING
%state STRING2
%state CHAR
%state CHAR2
%state INT
%state ERROR

%%

/* Keywords */
<YYINITIAL> "alias"				{ return ALIAS; }
<YYINITIAL> "mut"				{ return MUT; }
<YYINITIAL> "class"				{ return CLASS; }
<YYINITIAL> "set"				{ return SET; }
<YYINITIAL> "val"				{ return VAL; }
<YYINITIAL> "var"				{ return VAR; }
<YYINITIAL> "let"				{ return LET; }
<YYINITIAL> "def"				{ return DEF; }
<YYINITIAL> "if"				{ return IF; }
<YYINITIAL> "then"				{ return THEN; }
<YYINITIAL> "else"				{ return ELSE; }
<YYINITIAL> "when"				{ return WHEN; }
<YYINITIAL> "while"				{ return WHILE; }
<YYINITIAL> "do"				{ return DO; }
<YYINITIAL> "not"				{ return NOT; }
<YYINITIAL> "in"				{ return IN; }

<YYINITIAL> {
	{Bool_literal}				{ yylval = yytext (); return BOOL_LITERAL; }

	/* Identifiers */
	{Identifier}				{ yylval = yytext (); return IDENTIFIER; }
	{T_Identifier}				{ yylval = yytext (); return T_IDENTIFIER; }

	/* Literals */
	{Int_literal}				{ yylval = yytext (); return INT_LITERAL; }
	\"							{ string.setLength(0); yybegin(STRING); }
	\'							{ string.setLength(0); yybegin(CHAR); }

	/* Separators */
	{Separator} 				{ yylval = yytext (); return SEPARATOR; }

	/* Operators*/
	"+"							{ return PLUS; }
	"-"							{ return SUBTRACT; }
	"*"							{ return TIMES; }
	"/"							{ return FSLASH; }
	"("							{ return OP; }
	")"							{ return CP; }
	";"							{ return SEMICOLON; }
	"{"							{ return OCURLYBRACE; }
	"}"							{ return CCURLYBRACE; }
	","							{ return COMMA; }
	"<-"						{ return LARROW; }
	"$"							{ return DOLLAR; }
	":"							{ return COLON; }
	"->"						{ return RARROW; }
	"=="						{ return EQEQ; }
	"%"							{ return PERCENT; }
	"&&"						{ return ANDAND; }
	"||"						{ return OROR; }
	"="							{ return EQ; }
	"/="						{ return NEQ; }
	"<"							{ return LESS; }
	">"							{ return MORE; }
	"<="						{ return LESST; }
	">="						{ return MORET; }

	{Comment}					{}
	{WhiteSpace}				{}

	/*Error handler*/
	.							{ string.setLength(0); string.append(yytext()); yylval = yytext (); yybegin(ERROR); }

	}

<STRING> {
	{String_literal}			{ yybegin(STRING2); string.append(yytext()); }
	\"							{ yylval = string; yybegin(YYINITIAL); return STRING_LITERAL; }
	.							{ yybegin(ERROR); string.append(yytext()); }
}
<STRING2> {
	\"							{ yylval = string; yybegin(YYINITIAL); return STRING_LITERAL; }
	.							{ yybegin(ERROR); string.append(yytext()); }
}

<CHAR> {
	{Char_literal}				{ yybegin(CHAR2); string.append(yytext()); }
	\'							{ yylval = string; yybegin(YYINITIAL); return CHAR_LITERAL; }
	.							{ yybegin(ERROR); string.append(yytext()); }
}
<CHAR2> {
	\'							{ yylval = string; yybegin(YYINITIAL); return CHAR_LITERAL; }
}

<ERROR> {
	{Operators}|{WhiteSpace}	{ yylval = yytext (); yypushback(1); yybegin(YYINITIAL); 																		return ERROR; }
	.							{ string.append(yytext()); }
}

<<EOF>> {
  	return EOF;
}

[^]								{ yylval = yytext (); return ERROR; }