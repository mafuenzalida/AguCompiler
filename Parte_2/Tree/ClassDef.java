package Tree;
import java.io.*;
import java.util.ArrayList;

public class ClassDef extends DeclTree
{
    private String parameter = "";
    private String name;
    private ConstrArgs arguments = null;
    private ClassBody body;

	public ClassDef (String parameter, String name, ConstrArgs arguments
        , ClassBody body) {
        this.parameter = parameter;
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        if(arguments != null) {
            arguments.accept (visitor);
        }
        body.accept(visitor);
        visitor.exit (this);
    }

    public String getParameter() {
        return parameter;
    }

    public String getName() {
        return name;
    }
}