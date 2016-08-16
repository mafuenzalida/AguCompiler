package Tree;
import java.util.ArrayList;

public class FuncCall extends ExprTree
{
    private ASTree expression;
    private ArrayList<ASTree> expressions;

	public FuncCall (ASTree expression, ArrayList<ASTree> expressions) {
        this.expression = expression;
        this.expressions = expressions;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        for(ASTree e : expressions) {
            e.accept(visitor);
        }
        visitor.exit (this);
    }
}