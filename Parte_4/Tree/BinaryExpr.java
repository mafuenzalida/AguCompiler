package Tree;
import Parser.Position;

public class BinaryExpr extends ExprTree
{
	private ASTree expression1;
	private String operator;
    private ASTree expression2;

	public BinaryExpr (ASTree expression1, String operator, ASTree expression2, Position pos) {
		this.expression1 = expression1;
		this.operator = operator;
        this.expression2 = expression2;
        this.pos = pos;
        this.e = true;
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

    public ASTree getLeftExpression() {
        return expression1;
    }

    public ASTree getRightExpression() {
        return expression2;
    }
}