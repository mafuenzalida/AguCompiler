package Tree;
import Parser.Position;

public class DefIf extends ExprTree
{
    private ASTree expression;
    private ASTree t_body;
    private ASTree e_body;

	public DefIf (ASTree expression, ASTree t_body, ASTree e_body, Position pos) {
        this.expression = expression;
        this.t_body = t_body;
        this.e_body = e_body;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        t_body.accept(visitor);
        e_body.accept(visitor);
        visitor.exit (this);
    }

    public ASTree getExpression() {
        return expression;
    }

    public ASTree getTBody() {
        return t_body;
    }

    public ASTree getEBody() {
        return e_body;
    }
}