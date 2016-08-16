package Tree;

public class BodyExpr extends ASTree
{
    private String name;
    private ASTree expression;

	public BodyExpr (ASTree expression, String name) {
        this.expression = expression;
        this.name = name;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        visitor.exit (this);
    }

    public String getName() {
        return name;
    }
}