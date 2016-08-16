package Tree;

import java.util.List;
import java.io.*;

public class Program extends ASTree
{
    public Program (List<ASTree> declarations) {
        this.declarations = declarations;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for (ASTree decl : declarations) {
            decl.accept (visitor);
        }
        visitor.exit (this);
    }

    private List<ASTree> declarations;
}
