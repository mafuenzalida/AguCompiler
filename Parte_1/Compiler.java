import java.io.*;
import Lexer.*;

public class Compiler
{
    public static void main (String[] args) {
        /* Proyect 1/4 Lexer 28-04-16*/
        run_lexer(args[0]);
    }

    /* This method use the Lexer class created by jflex to read and display
        the tokens that got from an input file (first argument given when executed)*/
    public static void run_lexer(String input) {
        Reader r;
        Lexer  lex;

        try {
            r = new FileReader (input);
            lex = new Lexer (r);

            /* token holds the type of the actual token being read */
            Token token;

            /* yylex() defined in Lexer class gives next token */
            do {
                token = lex.yylex ();

                /* Display of token type and any other important info */
                switch (token.type) {
                    case sym.ALIAS:
                        System.out.printf ("ALIAS\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.MUT:
                        System.out.printf ("MUT\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.CLASS:
                        System.out.printf ("CLASS\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.SET:
                        System.out.printf ("SET\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.VAL:
                        System.out.printf ("VAL\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.VAR:
                        System.out.printf ("VAR\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.LET:
                        System.out.printf ("LET\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.DEF:
                        System.out.printf ("DEF\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.IF:
                        System.out.printf ("IF\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.THEN:
                        System.out.printf ("THEN\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.ELSE:
                        System.out.printf ("ELSE\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.WHEN:
                        System.out.printf ("WHEN\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.WHILE:
                        System.out.printf ("WHILE\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.DO:
                        System.out.printf ("DO\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.NOT:
                        System.out.printf ("NOT\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.IDENTIFIER:
                        System.out.printf ("IDENTIFIER " + token.value + "\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.T_IDENTIFIER:
                        System.out.printf ("TYPE IDENTIFIER " + token.value + "\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.INT_LITERAL:
                        System.out.printf ("INTEGER " + token.value + "\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.CHAR_LITERAL:
                        System.out.printf ("CHAR " + token.value + "\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.PLUS:
                        System.out.printf ("+\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.SUBTRACT:
                        System.out.printf ("-\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.TIMES:
                        System.out.printf ("*\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.FSLASH:
                        System.out.printf ("/\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.OP:
                        System.out.printf ("(\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.CP:
                        System.out.printf (")\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.SEMICOLON:
                        System.out.printf (";\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.OCURLYBRACE:
                        System.out.printf ("{\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.CCURLYBRACE:
                        System.out.printf ("}\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.COMMA:
                        System.out.printf (",\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.LARROW:
                        System.out.printf ("<-\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.DOLLAR:
                        System.out.printf ("$\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.COLON:
                        System.out.printf (":\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.RARROW:
                        System.out.printf ("->\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.EQEQ:
                        System.out.printf ("==\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.PERCENT:
                        System.out.printf ("%%\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.ANDAND:
                        System.out.printf ("&&\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.OROR:
                        System.out.printf ("||\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.EQ:
                        System.out.printf ("=\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.NEQ:
                        System.out.printf ("/=\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.LESS:
                        System.out.printf ("<\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.MORE:
                        System.out.printf (">\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.LESST:
                        System.out.printf ("<=\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.MORET:
                        System.out.printf (">=\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.SEPARATOR:
                        System.out.printf ("SEPARATOR "  + "\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.STRING_LITERAL:
                        System.out.printf ("STRING " + token.value + "\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.BOOL_LITERAL:
                        System.out.printf ("BOOL " + token.value + "\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                    case sym.ERROR:
                        System.out.printf("INVALID " + token.value + "\tcol: " + token.column + " row: " + token.row + "\n");
                    break;
                }
            } while (token.type != sym.EOF);
        }
        catch (IOException ioe) {
            System.err.println ("Error de IO");
        }
    }
}
