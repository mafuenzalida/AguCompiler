package Tree;

public class CondExpr extends ASTree
{
    private ASTree expression;

	public CondExpr (ASTree expression) {
        this.expression = expression;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        visitor.exit (this);
    }
}