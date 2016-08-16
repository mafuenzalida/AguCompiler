package Tree;
import java.util.ArrayList;

public class DirectAccess extends ExprTree
{
    private String name;

	public DirectAccess (String name) {
        this.name = name;
    }

    public void accept (TreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }
}