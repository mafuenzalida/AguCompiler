package Tree;
import Parser.Position;

public class BodyExpr extends ASTree
{
    private String name;
    private ASTree expression;

	public BodyExpr (ASTree expression, String name, Position pos) {
        this.expression = expression;
        this.name = name;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        visitor.exit (this);
    }

    public String getName() {
        return name;
    }

    public ASTree getExpression() {
        return expression;
    }
}