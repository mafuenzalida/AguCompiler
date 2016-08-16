package Tree;
import Parser.Position;

public class IntExpr extends ExprTree
{
    private String num;

	public IntExpr (String num, Position pos) {
        this.num = num;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return num;
    }
}