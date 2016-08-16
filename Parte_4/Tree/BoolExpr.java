package Tree;
import Parser.Position;

public class BoolExpr extends ExprTree
{
    private String bool;

	public BoolExpr (String bool, Position pos) {
        this.bool = bool;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return bool;
    }
}