import java.io.*;

import Lexer.Lexer;
import Parser.Parser;
import Tree.ASTree;
import Printer.TreePrinter;

public class Main
{
    public static void main (String[] args) {
        Reader reader;
        Lexer lexer;
        Parser parser;

        if (args.length != 1) {
            System.err.printf ("Argumentos:\n\n 1: Nombre de archivo\n");
            return;
        }

        try {
            reader = new FileReader (args [0]);
            lexer = new Lexer (reader);
            parser = new Parser (lexer);

            if (parser.parse ()) {
                TreePrinter printer = new TreePrinter (System.out);
                ASTree tree = parser.getTree ();
                printer.print (tree);
            }
        }
        catch (IOException ioe) {
            System.err.printf ("Error de IO...\n");
        }
    }
}
