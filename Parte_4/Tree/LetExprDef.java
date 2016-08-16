package Tree;
import java.util.ArrayList;
import Parser.Position;

public class LetExprDef extends ExprTree
{
    private ArrayList<ASTree> expressions;
    private ASTree body ;

	public LetExprDef (ArrayList<ASTree> expressions, ASTree body, Position pos) {
        this.expressions = expressions;
        this.body = body;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(ASTree e : expressions) {
            e.accept(visitor);
        }
        body.accept(visitor);
        visitor.exit (this);
    }

    public ASTree getBody() {
        return body;
    }
}