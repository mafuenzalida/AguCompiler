package Tree;
import Parser.Position;

public class Body extends ASTree
{
	ASTree expression;

	public Body(ASTree expression, Position pos) {
		this.expression = expression;
		this.pos = pos;
	}
    public void accept (TreeVisitor visitor) {
    	visitor.enter (this);
    	expression.accept (visitor);
    	visitor.exit (this);
    }

    public ASTree getExpression() {
    	return expression;
    }
}
