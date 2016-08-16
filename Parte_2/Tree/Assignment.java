package Tree;

import java.util.ArrayList;

public class Assignment extends DeclTree
{
    private ASTree expression;
    private ArrayList<String> names;

    public Assignment (String name, ASTree expression) {
        names = new ArrayList<String>();
        names.add(name);
        this.expression = expression;
    }

    public Assignment (ArrayList<String> names, ASTree expression) {
        this.names = names;
        this.expression = expression;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept (visitor);
        visitor.exit (this);
    }

    public ArrayList<String> getNames() {
        return names;
    }
}
