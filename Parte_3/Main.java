import java.io.*;

import Lexer.Lexer;
import Parser.Parser;
import Tree.ASTree;
import Semantic.*;
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
                SymTable symtable = new SymTable ();
                ErrorManager errorManager = new ErrorManager ();
                TypeChecker tc = new TypeChecker (symtable, errorManager);
                ASTree root = parser.getTree ();
                TreePrinter printer = new TreePrinter (System.out);

                tc.check (root);
                if(!errorManager.isEmpty())
                    errorManager.printAll (System.out);
                else
                    printer.print (root);
                
                symtable.createFile();
            }
        }
        catch (IOException ioe) {
            System.err.printf ("Error de IO...\n");
        }
    }
}