package Tree;
import java.util.ArrayList;
import Parser.Position;

public class DirectAccess extends ExprTree
{
    private String name;

	public DirectAccess (String name, Position pos) {
        this.name = name;
        this.pos = pos;
        this.e = true;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }
}