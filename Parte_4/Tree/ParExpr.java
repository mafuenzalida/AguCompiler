package Tree;
import Parser.Position;

public class ParExpr extends ExprTree
{
    private ASTree expression;

	public ParExpr (ASTree expression, Position pos) {
        this.expression = expression;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        visitor.exit (this);
    }

    public ASTree getExpression() {
        return expression;
    }
}