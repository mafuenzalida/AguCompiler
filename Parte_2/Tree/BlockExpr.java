package Tree;
import java.util.ArrayList;

public class BlockExpr extends ExprTree
{
    private ArrayList<ASTree> stmts;

	public BlockExpr (ArrayList<ASTree> stmts) {
        this.stmts = stmts;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(ASTree stmt: stmts) {
            stmt.accept(visitor);
        }
        visitor.exit (this);
    }
}