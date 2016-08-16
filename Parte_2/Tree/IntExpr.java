package Tree;

public class IntExpr extends ExprTree
{
    private String num;

	public IntExpr (String num) {
        this.num = num;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return num;
    }
}