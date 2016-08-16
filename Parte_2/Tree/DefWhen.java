package Tree;

public class DefWhen extends ExprTree
{
    private ASTree w_expression;
    private ASTree t_expression;

	public DefWhen (ASTree w_expression, ASTree t_expression) {
        this.w_expression = w_expression;
        this.t_expression = t_expression;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        w_expression.accept(visitor);
        t_expression.accept(visitor);
        visitor.exit (this);
    }
}