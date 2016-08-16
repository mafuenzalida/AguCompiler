package Tree;
import java.util.ArrayList;
import Parser.Position;

public class BlockExpr extends ExprTree
{
    private ArrayList<ASTree> stmts;

	public BlockExpr (ArrayList<ASTree> stmts, Position pos) {
        this.stmts = stmts;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(ASTree stmt: stmts) {
            stmt.accept(visitor);
        }
        visitor.exit (this);
    }

    public ArrayList<ASTree> getStmts() {
        return stmts;
    }
}