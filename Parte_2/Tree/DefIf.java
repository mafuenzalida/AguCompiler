package Tree;

public class DefIf extends ExprTree
{
    private ASTree expression;
    private ASTree t_body;
    private ASTree e_body;

	public DefIf (ASTree expression, ASTree t_body, ASTree e_body) {
        this.expression = expression;
        this.t_body = t_body;
        this.e_body = e_body;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        t_body.accept(visitor);
        e_body.accept(visitor);
        visitor.exit (this);
    }
}