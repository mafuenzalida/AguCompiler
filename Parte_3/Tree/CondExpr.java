package Tree;
import Parser.Position;

public class CondExpr extends ASTree
{
    private ASTree expression;

	public CondExpr (ASTree expression, Position pos) {
        this.expression = expression;
        this.pos = pos;
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