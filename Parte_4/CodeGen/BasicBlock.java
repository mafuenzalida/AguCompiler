package CodeGen;

import java.util.ArrayList;
import java.util.List;
import Tree.*;

/* Representación de un bloque básico. Contiene una
 * lista de instrucciones, y un nombre */

public class BasicBlock implements BlocksDecl
{
    public BasicBlock (String label, List<ASTree> decls) {
        this.label = label;
        this.decls = decls;
    }

    public BasicBlock (String lbl) {
        this.label = lbl;
        this.decls = new ArrayList<ASTree> ();
    }

    public String getLabel () {
        return label;
    }

    public void addDeclaration (ASTree node) {
        decls.add (node);
    }

    public void accept (BlocksVisitor visitor) {
        visitor.enter (this);
        for (ASTree d : decls) {
            d.accept (visitor);
        }
        visitor.exit (this);
    }

    private String       label;
    private List<ASTree> decls;
}
