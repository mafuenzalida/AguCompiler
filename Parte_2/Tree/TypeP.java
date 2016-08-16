package Tree;

public class TypeP extends TypeTree
{
    private TypeTree type;

    public TypeP (TypeTree type) {
        this.type = type;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        type.accept (visitor);
        visitor.exit (this);
    }
}
