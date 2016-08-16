package Tree;

public class BinaryExpr extends ExprTree
{
	private ASTree expression1;
	private String operator;
    private ASTree expression2;

	public BinaryExpr (ASTree expression1, String operator, ASTree expression2) {
		this.expression1 = expression1;
		this.operator = operator;
        this.expression2 = expression2;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression1.accept(visitor);
        expression2.accept(visitor);
        visitor.exit (this);
    }

    public String getOperator() {
    	return operator;
    }
}