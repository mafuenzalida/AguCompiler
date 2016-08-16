package Tree;
import Parser.Position;

public class DefWhen extends ExprTree
{
    private ASTree w_expression;
    private ASTree t_expression;

	public DefWhen (ASTree w_expression, ASTree t_expression, Position pos) {
        this.w_expression = w_expression;
        this.t_expression = t_expression;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        w_expression.accept(visitor);
        t_expression.accept(visitor);
        visitor.exit (this);
    }

    public ASTree getExpression() {
        return w_expression;
    }
}