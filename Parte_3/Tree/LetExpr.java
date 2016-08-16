package Tree;
import java.util.ArrayList;
import Parser.Position;

public class LetExpr extends ExprTree
{
    private String name;
    private TypeTree deftype;
    private ASTree expression;

	public LetExpr (String name, TypeTree type, ASTree expression, Position pos) {
        this.name = name;
        this.deftype = type;
        this.expression = expression;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        deftype.accept(visitor);
        expression.accept(visitor);
        visitor.exit (this);
    }

    public String getName() {
        return name;
    }

    public TypeTree getDefType() {
        return deftype;
    }
}