package Tree;

import java.util.List;

public class TypeID extends TypeTree
{
    public TypeID (String name) {
        this.name = name;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit (this);
    }

    public String getName () {
        return name;
    }

    private String name;
}