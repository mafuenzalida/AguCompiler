package Tree;
import java.util.ArrayList;
import Parser.Position;

public class FuncCall extends ExprTree
{
    private ASTree expression;
    private ArrayList<ASTree> expressions;

	public FuncCall (ASTree expression, ArrayList<ASTree> expressions, Position pos) {
        this.expression = expression;
        this.expressions = expressions;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        for(ASTree e : expressions) {
            e.accept(visitor);
        }
        visitor.exit (this);
    }

    public ASTree getExpression() {
        return expression;
    }

    public ArrayList<ASTree> getExpressions() {
        return expressions;
    }
}