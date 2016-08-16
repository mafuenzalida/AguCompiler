package Tree;
import Parser.Position;

public class StringExpr extends ExprTree
{
    private String name;

	public StringExpr (StringBuffer name, Position pos) {
        this.name = name.toString();
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