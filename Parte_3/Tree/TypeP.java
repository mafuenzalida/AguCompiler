package Tree;
import Parser.Position;

public class TypeP extends TypeTree
{
    private TypeTree deftype;

    public TypeP (TypeTree type, Position pos) {
        this.deftype = type;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        deftype.accept (visitor);
        visitor.exit (this);
    }

    public TypeTree getDefType () {
        return deftype;
    }
}
