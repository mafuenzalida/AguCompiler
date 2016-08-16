package Tree;

public class Body extends ASTree
{
	ASTree expression;

	public Body(ASTree expression) {
		this.expression = expression;
	}
    public void accept (TreeVisitor visitor) {
    	visitor.enter (this);
    	expression.accept (visitor);
    	visitor.exit (this);
    }
}
