package Tree;
import Parser.Position;

import java.util.ArrayList;

public class Assignment extends DeclTree
{
    private String parameter = "";
    private ASTree expression;
    private ArrayList<String> names;

    public Assignment (String name, ASTree expression, String parameter, Position pos) {
        names = new ArrayList<String>();
        names.add(name);
        this.expression = expression;
        this.pos = pos;
        this.parameter = parameter;
    }

    public Assignment (ArrayList<String> names, ASTree expression, String parameter, Position pos) {
        this.names = names;
        this.expression = expression;
        this.pos = pos;
        this.parameter = parameter;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        expression.accept (visitor);
        visitor.exit (this);
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public String getParameter () {
        return parameter;
    }

    public ASTree getExpression () {
        return expression;
    }
}
