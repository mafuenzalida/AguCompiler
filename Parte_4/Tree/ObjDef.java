package Tree;
import java.util.ArrayList;
import Parser.Position;

public class ObjDef extends ExprTree
{
    private String name;
    private ArrayList<ASTree> expressions;

	public ObjDef (String name, ArrayList<ASTree> expressions, Position pos) {
        this.name = name;
        this.expressions = expressions;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(ASTree e : expressions) {
            e.accept(visitor);
        }
        visitor.exit (this);
    }

    public String getName() {
        return name;
    }

    public ArrayList<ASTree> getExpressions() {
        return expressions;
    }
}