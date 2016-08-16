package Tree;
import Parser.Position;

public class IdExpr extends ExprTree
{
    private String name;

	public IdExpr (String name, Position pos) {
        this.name = name;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }
}