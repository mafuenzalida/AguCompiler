package Tree;
import java.util.ArrayList;

public class LetExprDef extends ExprTree
{
    private ArrayList<ASTree> expressions;
    private ASTree body ;

	public LetExprDef (ArrayList<ASTree> expressions, ASTree body) {
        this.expressions = expressions;
        this.body = body;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(ASTree e : expressions) {
            e.accept(visitor);
        }
        body.accept(visitor);
        visitor.exit (this);
    }
}