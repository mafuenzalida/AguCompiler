package Lexer;

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
%type Token

%{
	StringBuffer string = new StringBuffer();

	private Token token(final int type) {
		return new Token(type, yyline, yycolumn);
	}
	private Token token(final int type, Object value) {
		return new Token(type, yyline, yycolumn,value);
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
<YYINITIAL> "alias"				{ return token(sym.ALIAS); }
<YYINITIAL> "mut"				{ return token(sym.MUT); }
<YYINITIAL> "class"				{ return token(sym.CLASS); }
<YYINITIAL> "set"				{ return token(sym.SET); }
<YYINITIAL> "val"				{ return token(sym.VAL); }
<YYINITIAL> "var"				{ return token(sym.VAR); }
<YYINITIAL> "let"				{ return token(sym.LET); }
<YYINITIAL> "def"				{ return token(sym.DEF); }
<YYINITIAL> "if"				{ return token(sym.IF); }
<YYINITIAL> "then"				{ return token(sym.THEN); }
<YYINITIAL> "else"				{ return token(sym.ELSE); }
<YYINITIAL> "when"				{ return token(sym.WHEN); }
<YYINITIAL> "while"				{ return token(sym.WHILE); }
<YYINITIAL> "do"				{ return token(sym.DO); }
<YYINITIAL> "not"				{ return token(sym.NOT); }

<YYINITIAL> {
	{Bool_literal}				{ string.setLength(0); string.append(yytext()); 															return token(sym.BOOL_LITERAL, string.toString()); }

	/* Identifiers */
	{Identifier}				{ string.setLength(0); string.append(yytext());
									return token(sym.IDENTIFIER, string.toString()); }
	{T_Identifier}				{ string.setLength(0); string.append(yytext()); 															return token(sym.T_IDENTIFIER, string.toString()); }

	/* Literals */
	{Int_literal}				{ string.setLength(0); string.append(yytext()); 															return token(sym.INT_LITERAL, string.toString()); }
	\"							{ string.setLength(0); yybegin(STRING); }
	\'							{ string.setLength(0); yybegin(CHAR); }

	/* Separators */
	{Separator} 				{ string.setLength(0); string.append(yytext()); 															return token(sym.SEPARATOR, string.toString()); }

	/* Operators*/
	"+"							{ return token(sym.PLUS); }
	"-"							{ return token(sym.SUBTRACT); }
	"*"							{ return token(sym.TIMES); }
	"/"							{ return token(sym.FSLASH); }
	"("							{ return token(sym.OP); }
	")"							{ return token(sym.CP); }
	";"							{ return token(sym.SEMICOLON); }
	"{"							{ return token(sym.OCURLYBRACE); }
	"}"							{ return token(sym.CCURLYBRACE); }
	","							{ return token(sym.COMMA); }
	"<-"						{ return token(sym.LARROW); }
	"$"							{ return token(sym.DOLLAR); }
	":"							{ return token(sym.COLON); }
	"->"						{ return token(sym.RARROW); }
	"=="						{ return token(sym.EQEQ); }
	"%"							{ return token(sym.PERCENT); }
	"&&"						{ return token(sym.ANDAND); }
	"||"						{ return token(sym.OROR); }
	"="							{ return token(sym.EQ); }
	"/="						{ return token(sym.NEQ); }
	"<"							{ return token(sym.LESS); }
	">"							{ return token(sym.MORE); }
	"<="						{ return token(sym.LESST); }
	">="						{ return token(sym.MORET); }

	{Comment}					{}
	{WhiteSpace}				{}

	/*Error handler*/
	.							{ string.setLength(0); string.append(yytext()); yybegin(ERROR); }

	}

<STRING> {
	{String_literal}			{ yybegin(STRING2); string.append(yytext()); }
	\"							{ yybegin(YYINITIAL); return token(sym.STRING_LITERAL, string.toString()); }
	.							{ yybegin(ERROR); string.append(yytext()); }
}
<STRING2> {
	\"							{ yybegin(YYINITIAL); return token(sym.STRING_LITERAL, string.toString()); }
	.							{ yybegin(ERROR); string.append(yytext()); }
}

<CHAR> {
	{Char_literal}				{ yybegin(CHAR2); string.append(yytext()); }
	\'							{ yybegin(YYINITIAL); return token(sym.CHAR_LITERAL, string.toString()); }
	.							{ yybegin(ERROR); string.append(yytext()); }
}
<CHAR2> {
	\'							{ yybegin(YYINITIAL); return token(sym.CHAR_LITERAL, string.toString()); }
}

<ERROR> {
	{Operators}|{WhiteSpace}	{ yypushback(1); yybegin(YYINITIAL); 																		return token(sym.ERROR, string.toString()); }
	.							{ string.append(yytext()); }
}

<<EOF>> {
  	return token(sym.EOF);
}

[^]								{ string.setLength(0); string.append(yytext()); 																return token(sym.ERROR,string.toString()); }