package Tree;

public class UnaryExpr extends ExprTree
{
	private String operator;
    private ASTree expression;

	public UnaryExpr (String operator, ASTree expression) {
		this.operator = operator;
        this.expression = expression;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        visitor.exit (this);
    }

    public String getOperator() {
    	return operator;
    }
}