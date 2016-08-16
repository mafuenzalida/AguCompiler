package CodeGen;

import java.util.*;
import Semantic.*;
import Tree.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class GenerateLLVM
{
    public List<String> generate (SymTable symtable, ASTree root) {
        List<String> program = new ArrayList<String> ();
        StringLiterals sl = new StringLiterals ();

        /* Primero se recolectan los strings literales.
         * Esta fase podría juntarse con la siguiente, pero
         * se mantiene separada para simplificar */
        program.addAll (sl.getDefinitions (root));

        LLVMSimple codegen = new LLVMSimple (symtable);
        program.addAll (codegen.generate (root));

        try {
            /* declarar funciones externas */
            BufferedReader in = new BufferedReader(new FileReader("predefined.ll"));
            String line = null;
            while ((line = in.readLine()) != null) {
                program.add(line);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return program;
    }
}
