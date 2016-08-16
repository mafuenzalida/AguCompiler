package Tree;
import java.util.ArrayList;

public class ConstrArgDec extends DeclTree
{
    private String parameter = "";
	private String name;
	private TypeTree type;

	public ConstrArgDec (String parameter, String name, TypeTree type) {
        this.parameter = parameter;
        this.name = name;
        this.type = type;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        type.accept (visitor);
        visitor.exit (this);
    }

    public String getParameter() {
        return parameter;
    }

    public String getName() {
        return name;
    }
}