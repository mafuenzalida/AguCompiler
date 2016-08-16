package Tree;

public class IdExpr extends ExprTree
{
    private String name;

	public IdExpr (String name) {
        this.name = name;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }
}