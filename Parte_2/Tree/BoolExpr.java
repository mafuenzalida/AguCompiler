package Tree;

public class BoolExpr extends ExprTree
{
    private String bool;

	public BoolExpr (String bool) {
        this.bool = bool;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return bool;
    }
}