package Tree;
import java.io.*;
import Parser.Position;

public class StmtWhile extends DeclTree
{
    private ASTree expression;
    private ASTree expression_do;

	public StmtWhile (ASTree expression, ASTree expression_do, Position pos) {
        this.expression = expression;
        this.expression_do = expression_do;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept(visitor);
        expression_do.accept(visitor);
        visitor.exit (this);
    }

    public ASTree getExpression () {
        return expression;
    }
}