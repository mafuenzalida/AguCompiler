package Tree;
import Parser.Position;
import java.util.List;

public class TypeID extends TypeTree
{
    public TypeID (String name, Position pos) {
        this.name = name;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit (this);
    }

    public String getName () {
        return name;
    }

    private String name;
}