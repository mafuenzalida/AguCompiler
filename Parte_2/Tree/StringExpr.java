package Tree;

public class StringExpr extends ExprTree
{
    private String name;

	public StringExpr (StringBuffer name) {
        this.name = name.toString();
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }
}