package Tree;
import Parser.Position;

public class AliasDecl extends DeclTree
{
    private String name;
    private TypeTree deftype;

    public AliasDecl (String name, TypeTree type, Position pos) {
        this.name = name;
        this.deftype = type;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        deftype.accept (visitor);
        visitor.exit (this);
    }

    public String getName () {
        return name;
    }

    public TypeTree getDefType () {
        return deftype;
    }
}
