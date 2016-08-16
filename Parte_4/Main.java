import java.io.*;
import java.util.*;

import Lexer.Lexer;
import Parser.Parser;
import Tree.ASTree;
import Semantic.*;
import CodeGen.*;
import Printer.*;

public class Main
{
    public static void main (String[] args) {
        Reader reader;
        Lexer lexer;
        Parser parser;
        PrintWriter writer;

        if (args.length < 2) {
            System.err.printf ("Argumentos:\n\n 1: Archivo input\n 2: Archivo output\n");
            return;
        }

        try {
            reader = new FileReader (args [0]);
            lexer = new Lexer (reader);
            parser = new Parser (lexer);

            writer = new PrintWriter (new FileWriter (args [1]));

            if (parser.parse ()) {
                SymTable symtable = new SymTable ();
                ErrorManager errorManager = new ErrorManager ();
                TypeChecker tc = new TypeChecker (symtable, errorManager);
                ASTree root = parser.getTree ();

                TreePrinter printer = new TreePrinter (System.out);

                printer.print (root);

                System.out.println ("----");

                tc.check (root);

                errorManager.printAll (System.out);

                if (errorManager.isEmpty ()) {
                    GenerateLLVM codegen = new GenerateLLVM ();

                    List<String> program = codegen.generate (symtable, root);

                    System.out.println ("----");
                    for (String line : program) {
                        System.out.println (line);
                        writer.println (line);
                    }
                }
            }
            writer.flush ();
            writer.close ();
        }
        catch (IOException ioe) {
            System.err.printf ("Error de IO...\n");
        }
    }
}
