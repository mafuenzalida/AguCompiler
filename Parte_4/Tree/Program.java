package Tree;
import Parser.Position;

import java.util.List;
import java.io.*;

public class Program extends ASTree
{
    public Program (List<ASTree> declarations, Position pos) {
        this.declarations = declarations;
        this.pos = pos;
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
