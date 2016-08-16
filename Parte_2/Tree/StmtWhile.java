package Tree;
import java.io.*;

public class StmtWhile extends DeclTree
{
    private ASTree expression;
    private ASTree expression_do;

	public StmtWhile (ASTree expression, ASTree expression_do) {
        this.expression = expression;
        this.expression_do = expression_do;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        expression_do.accept(visitor);
        visitor.exit (this);
    }
}