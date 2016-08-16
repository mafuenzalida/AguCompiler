package Tree;

public class ConstDecl extends DeclTree
{
    private String name;
    private TypeTree type;
    private ASTree expression;

    public ConstDecl (String name, TypeTree type, ASTree expression) {
        this.name = name;
        this.type = type;
        this.expression = expression;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        type.accept (visitor);
        expression.accept (visitor);
        visitor.exit (this);
    }

    public String getName () {
        return name;
    }
}
