package Tree;

public class VarDecl extends DeclTree
{
    private String name;
    private TypeTree type;
    private ASTree expression = null;

    public VarDecl (String name, TypeTree type) {
        this.name = name;
        this.type = type;
    }

    public VarDecl (String name, TypeTree type, ASTree expression) {
        this.name = name;
        this.type = type;
        this.expression = expression;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        type.accept (visitor);
        if(expression != null) {
            expression.accept (visitor);
        }
        visitor.exit (this);
    }

    public String getName () {
        return name;
    }
}
