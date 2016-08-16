package Tree;
import java.util.ArrayList;

public class LetExpr extends ExprTree
{
    private String name;
    private TypeTree type;
    private ASTree expression;

	public LetExpr (String name, TypeTree type, ASTree expression) {
        this.name = name;
        this.type = type;
        this.expression = expression;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        type.accept(visitor);
        expression.accept(visitor);
        visitor.exit (this);
    }

    public String getName() {
        return name;
    }
}