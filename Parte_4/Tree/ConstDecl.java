package Tree;
import Parser.Position;

public class ConstDecl extends DeclTree
{
    private String name;
    private TypeTree deftype;
    private ASTree expression;

    public ConstDecl (String name, TypeTree type, ASTree expression, Position pos) {
        this.name = name;
        this.deftype = type;
        this.expression = expression;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        deftype.accept (visitor);
        expression.accept (visitor);
        visitor.exit (this);
    }

    public String getName () {
        return name;
    }

    public TypeTree getDefType() {
        return deftype;
    }

    public ASTree getExpression() {
        return expression;
    }
}
