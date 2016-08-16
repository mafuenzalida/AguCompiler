package Tree;
import Parser.Position;

public class UnaryExpr extends ExprTree
{
	private String operator;
    private ASTree expression;

	public UnaryExpr (String operator, ASTree expression, Position pos) {
		this.operator = operator;
        this.expression = expression;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        visitor.exit (this);
    }

    public String getOperator() {
    	return operator;
    }

    public ASTree getExpression() {
        return expression;
    }
}