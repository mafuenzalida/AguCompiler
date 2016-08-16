package Tree;

public class AliasDecl extends DeclTree
{
    private String name;
    private TypeTree type;

    public AliasDecl (String name, TypeTree type) {
        this.name = name;
        this.type = type;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        type.accept (visitor);
        visitor.exit (this);
    }

    public String getName () {
        return name;
    }
}
